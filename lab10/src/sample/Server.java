package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Application{

    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
        //new ConnectionListener().run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        outputArea = new TextArea();
        outputArea.setPrefWidth(275);
        outputArea.setPrefHeight(150);
        outputArea.setEditable(false);

        Button exitBtn = new Button("Exit");

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        grid.add(outputArea, 1, 0);
        grid.add(exitBtn, 1, 1);

        root.getChildren().add(grid);
        primaryStage.setTitle("Lab 10 Server");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        //new ConnectionListener().run();
        Thread thread = new Thread(new ConnectionListener());
        thread.start();
    }


    public class outputMessage implements Runnable{
        Socket clientSocket = null;

        public outputMessage (Socket clientSocket){

            this.clientSocket = clientSocket;
        }

        public void run(){

            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientMessage;
                while((clientMessage = br.readLine()) != null ){
                    outputArea.appendText(clientMessage + "\n");
                    //System.out.println(clientMessage);
                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }


    public class ConnectionListener implements Runnable{

        private ServerSocket serverSocket;

        public ConnectionListener() {
            try{
                serverSocket = new ServerSocket(8080);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        public void run(){
            try{
                while (true) {
                    Socket acceptedSocket = serverSocket.accept();
                    //Scanner inputStream = new Scanner(acceptedSocket.getInputStream());
                    //System.out.println(inputStream.next());
                    Thread thread = new Thread(new outputMessage(acceptedSocket));
                    thread.start();
                }
            }catch (Exception e){
                e.printStackTrace();

            }
        }
    }

}

/*
class ConnectionListener {
    private ServerSocket serverSocket;

    public ConnectionListener() {
        try{
            serverSocket = new ServerSocket(8080);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                Scanner inputStream = new Scanner(acceptedSocket.getInputStream());
                System.out.println(inputStream.next());
                //outputField.appendText(inputStream.next() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
 */


