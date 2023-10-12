package org.forfree.pulse;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class VRFDetail {
    public void executeIt(String me,String reg) {
        String host = "ethiopsmssh.ethio.local";
        String user = "ethio15374@ipngnlogonrw@"+me;
        String password = "aJFTdA9bAKGhWLL";
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
            ChannelShell channel = (ChannelShell) session.openChannel("shell");
            LocalDate Lt = LocalDate.now();
            String ipath = "E:\\IFOROSS\\VRF_DETAIL\\";
//            create a directory to put the files into
            File my_dir = new File(ipath+Lt);
            // tries to create a new directory
            boolean value = my_dir.mkdir();
//            block for the execution file
            String filename = "VRF_DETAIL_"+reg+".txt";
            File file = new File(ipath+Lt+"\\"+filename);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            System.setOut(printStream);
            channel.setOutputStream(printStream);
            channel.setPtySize(1024, 20, 1024, 20);
            //Create a Shell Scrip
            File shellScript = createShellScript();
            //Convert the shell script to byte stream
            FileInputStream fin = new FileInputStream(shellScript);
            byte fileContent[] = new byte[(int) shellScript.length()];
//            System.out.println("aka"+(int)shellScript.length());
            fin.read(fileContent);
            InputStream in = new ByteArrayInputStream(fileContent);
            //wait for the pe to load the session
            TimeUnit.SECONDS.sleep(45);
            channel.connect();
            TimeUnit.SECONDS.sleep(45);
            //Set the shell script to the channel as input stream
            channel.setInputStream(in);
            //set the channel to start right away
            channel.start();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File createShellScript() {
        String filename = "vrf_detail.txt";
        File fstream = new File(filename);

        try{
            // Create file
            PrintStream out = new PrintStream(new FileOutputStream(fstream));
            out.println("terminal length 0");
            out.println("sho ip vrf detail");
            out.println("exit");

            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return fstream;
    }
    
}