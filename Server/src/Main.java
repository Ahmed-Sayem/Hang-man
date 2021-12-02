import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket ser = new ServerSocket(2024);
            Socket sc = ser.accept();

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            BufferedReader bfr = new BufferedReader(isr);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}