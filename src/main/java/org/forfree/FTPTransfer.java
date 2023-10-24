package org.forfree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class FTPTransfer {
    public void sendIt(){
        String server = "10.180.8.133";//10.180.8.133
        int port = 21;
        String user = "ftprw";
        String pass = "Efoy@1921";

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteDirPath = "";

            String[] dirs = {"BGP","INTERFACE_DATA","INTERFACE_DESC","L2VPN","STATIC","VBUI","VRF","VRF_DETAIL"};

            for(int l=0;l<dirs.length;l++) {
                String localParentDir = "D:\\IFOROSS\\"+dirs[l]+"\\";
                String remoteParentDir = "var/efoyoss/IFOROSS/"+dirs[l];
                uploadDirectory(ftpClient,remoteDirPath,localParentDir,remoteParentDir);
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                    System.out.println("disconnected");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String uploadDirectory(FTPClient ftpClient, String remoteDirPath, String localParentDir, String remoteParentDir)
            throws IOException {

        System.out.println("LISTING directory: " + localParentDir);

        File localDir = new File(localParentDir);
        File[] subFiles = localDir.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (File item : subFiles) {
                String remoteFilePath = remoteDirPath + "/" + remoteParentDir + "/" + item.getName();
                if (remoteParentDir.equals("")) {
                    remoteFilePath = remoteDirPath + "/" + item.getName();
                }

                if (item.isFile()) {
                    // upload the file
                    String localFilePath = item.getAbsolutePath();
                    System.out.println("About to upload the file: " + localFilePath);
                    boolean uploaded = uploadSingleFile(ftpClient, localFilePath, remoteFilePath);
                    if (uploaded) {
                        System.out.println("UPLOADED a file to: " + remoteFilePath);
                    } else {
                        System.out.println("COULD NOT upload the file: "+ localFilePath);
                    }
                } else {
                    // create directory on the server
                    boolean created = ftpClient.makeDirectory(remoteFilePath);
                    if (created) {
                        System.out.println("CREATED the directory: "+ remoteFilePath);
                    } else {
                        System.out.println("COULD NOT create the directory: "+ remoteFilePath);
                    }

                    // upload the sub directory
                    String parent = remoteParentDir + "/" + item.getName();
                    if (remoteParentDir.equals("")) {
                        parent = item.getName();
                    }

                    localParentDir = item.getAbsolutePath();
                    uploadDirectory(ftpClient, remoteDirPath, localParentDir,parent);
                }
            }
        }
        return "done "+localParentDir;
    }

    public static boolean uploadSingleFile(FTPClient ftpClient,
                                           String localFilePath, String remoteFilePath) throws IOException {
        File localFile = new File(localFilePath);

        InputStream inputStream = new FileInputStream(localFile);
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(remoteFilePath, inputStream);
        } finally {
            inputStream.close();
        }
    }


}
