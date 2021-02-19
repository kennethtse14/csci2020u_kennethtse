package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Text actionTarget;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;


    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Full Name: " + fullnameField.getText());
        System.out.println("E-Mail: " + emailField.getText());
        System.out.println("Phone #: " + phoneField.getText());

        actionTarget.setText("Registration Successful!");
    }
}
