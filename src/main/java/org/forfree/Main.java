package org.forfree;

public class Main {

    public static void main(String[] args)  throws Exception {

        Thread t1 = new Thread();

        String[] names = {"LGH A","LGH B","BL A","BL B","BD A","BD B","DD A","DD B","DS A","DS B","DM A","DM B","GD A","GD B","JG A","JG B","JM A","JM B","KK A","KK B","NK A","NK B","NS A","NS B","NZ A","NZ B","SMN A","SMN B","SD A","SD B","YK A","YK B","AD A","AD B","AWS A","AWS B","OA A","OA B","MW A","MW B","AB A","AB B"};
        String[] ips = {"10.0.1.3","10.0.1.4","10.0.1.1","10.0.1.2","10.0.1.51","10.0.1.52","10.0.1.35","10.0.1.36","10.0.1.39","10.0.1.40","10.0.1.49","10.0.1.50","10.0.1.55","10.0.1.56","10.0.1.37","10.0.1.38","10.0.1.41","10.0.1.42","10.0.1.7","10.0.1.8","10.0.1.53","10.0.1.54","10.0.1.15","10.0.1.16","10.0.1.33","10.0.1.34","10.0.1.43","10.0.1.44","10.0.1.47","10.0.1.48","10.0.1.11","10.0.1.12","10.0.1.13","10.0.1.14","10.0.1.45","10.0.1.46","10.0.1.5","10.0.1.6","10.0.1.9","10.0.1.10","10.0.1.59","10.0.1.60"};
        for(int k=0;k<ips.length;k++) {
            Ithread tt = new Ithread(ips[k],names[k]);
            tt.start();
            Thread.sleep(2000);
        }
    }
}