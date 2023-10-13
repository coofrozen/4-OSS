package org.forfree;

import com.jcraft.jsch.*;

public class SFTPTransfer {

    private String remoteHost = "10.180.8.133";
    private String username = "mdb";
    private String password = "U_tywg_2008";

    private ChannelSftp setupJsch() throws JSchException {

        JSch jsch = new JSch();
//        jsch.setKnownHosts("/Users/cofree/.ssh/known_hosts");
        Session jschSession = jsch.getSession(username, remoteHost);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public void whenUploadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();

        String localFile = "D:\\ZTE_COMMANDS.xlsx";
        String remoteDir = "im/";

        channelSftp.put(localFile, remoteDir + "ZTE_COMMANDS.xlsx");
        
        channelSftp.exit();
    }
}
