import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.201.19.38", 12345);
        System.out.println("Connected!");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        System.out.println("Sending string to the ServerSocket");

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	while(true){
        dataOutputStream.writeDouble(Double.parseDouble(br.readLine()));
        dataOutputStream.flush(); 
       // dataOutputStream.close();
	}
       // System.out.println("Closing socket and terminating program.");
       // socket.close();
    }
}
