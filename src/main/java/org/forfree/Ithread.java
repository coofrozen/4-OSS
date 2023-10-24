package org.forfree;

import org.forfree.pulse.*;

public class Ithread extends Thread {

    String pe_ip;
    String pe_name;
    InterfaceData id = new InterfaceData();
    InterfaceDesc id2 = new InterfaceDesc();
    L2VPN l2 = new L2VPN();
    StaticDat st = new StaticDat();
    Vbui vbui = new Vbui();
    VRF vrf = new VRF();
    VRFDetail vrfd = new VRFDetail();

    BGP bgp = new BGP();

    FTPTransfer ftp = new FTPTransfer();




    public Ithread(String pe_ip, String pe_name) {
        // TODO Auto-generated constructor stub
        this.pe_ip = pe_ip;
        this.pe_name = pe_name;
    }


    @Override
    public void run() {
//        id.executeIt(pe_ip, pe_name);
//        id2.executeIt(pe_ip, pe_name);
//        l2.executeIt(pe_ip, pe_name);
//        st.executeIt(pe_ip, pe_name);
//        vbui.executeIt(pe_ip, pe_name);
//        vrf.executeIt(pe_ip, pe_name);
//        vrfd.executeIt(pe_ip, pe_name);
//        bgp.executeIt(pe_ip, pe_name);

    }
}