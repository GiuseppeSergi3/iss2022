package it.unibo.radarSystem22.main;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import radarPojo.radarSupport;

public class RadarUsageMain {
    public void doJob() {
    		try {
				ServerSocket sSocket=new ServerSocket(12345);
				Socket cSocket = sSocket.accept();
			
            System.out.println("start");
            radarSupport.setUpRadarGui();
            int i=0;
            while (true)
            {

            	InputStream inputStream = cSocket.getInputStream();
            	DataInputStream dataInputStream= new DataInputStream(inputStream);
            	Double message = dataInputStream.readDouble();
            	System.out.println("RICEVUTO: "+message);
            	i++;
            	radarSupport.update( ""+message, "0");
            }
            //radarSupport.update( "40", "60");
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    public static void main(String[] args) {
            new RadarUsageMain().doJob();
    }
}