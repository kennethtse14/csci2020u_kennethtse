package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField messageField;

    @FXML
    public void sendMessage(){
        String username = usernameField.getText();
        String message = messageField.getText();

        Socket clientSocket = null;
        PrintWriter output = null;

        try {
            clientSocket = new Socket("localhost", 8080);
            output = new PrintWriter(new BufferedOutputStream(clientSocket.getOutputStream()));
            output.println(username + ": " + message);
            output.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(username);
        //System.out.println(message);
    }

    @FXML
    public void exit(){
        System.exit(0);
        //System.out.println("Clicked on exit button");
    }

}
