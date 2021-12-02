package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {

    @FXML
    Button reg, login;
    @FXML
    Label lekha;
    @FXML
    TextField nam;
    @FXML
    PasswordField password;


    @FXML
    public void btn1(ActionEvent actionEvent) {
        int flag=0;
        try {
            ServerSocket ser = new ServerSocket(2024);
            Socket sc = ser.accept();
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            BufferedReader bfr = new BufferedReader(isr);
            String s = bfr.readLine();
            ArrayList<Logs> loglist = new ArrayList<>();
            try{
                Scanner in = new Scanner(new File("F:\\TRIMESTER-5\\AOOP\\HANGMAN\\src\\sample\\Data.txt"));

                while(in.hasNext()){
                    Logs l = new Logs(in.next(),in.next());
                    loglist.add(l);
                }
                String name=null ;
                String pas=null;
                try {
                    String[] x = s.split(" ");
                     name = x[0];
                     pas = x[1];
                }
                catch (IndexOutOfBoundsException e)
                {
                    e.getStackTrace();
                }

                for(Logs b: loglist){

                   if(b.name.contains(name) && b.passcode.contains(pas))
                   {
                       lekha.setText("LOGED IN!!");
                       flag=1;
                       break;
                   }

                }
                if(flag==0)
                    lekha.setText("UNSUCCESSFULL ATTEMPT!!");
                in.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }


            bfr.close();
            sc.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    @FXML
    public void btn2(ActionEvent actionEvent) {
        ArrayList<Logs> loglist = new ArrayList<>();
        String filepath = "F:\\TRIMESTER-5\\AOOP\\HANGMAN\\src\\sample\\Data.txt";
        String n = nam.getText();
        String p = password.getText();
        String s = n + " " + p + "/n";
        BufferedWriter bw = null;
        appendUsingFileWriter("F:\\TRIMESTER-5\\AOOP\\HANGMAN\\src\\sample\\Data.txt", "\n" + s);
        try {

            FileWriter fw = new FileWriter("F:\\TRIMESTER-5\\AOOP\\HANGMAN\\src\\sample\\Data.txt");
            bw = new BufferedWriter(fw);
            bw.write(s);
            Logs l = new Logs(n, p);
            loglist.add(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write(text);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Logs {
        String name;
        String passcode;

        public Logs(String name, String passcode) {
            this.name = name;
            this.passcode = passcode;
        }
    }
}