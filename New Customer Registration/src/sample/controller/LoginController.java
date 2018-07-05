package sample.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;
import sample.scene.ChangeScene;

public class LoginController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button loginSubmitButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField loginUsernameField;
    DatabaseHandler databaseHandler;
    @FXML
    void initialize() {
        loginSubmitButton.setOnAction(ev -> {
            loginUser();
        });
        loginSignUpButton.setOnAction(e-> {
         showScreen("signup");
        });
    }

    public void loginUser() {

        databaseHandler = new DatabaseHandler();
        String loginText = loginUsernameField.getText().trim();
        String loginPwd = loginPasswordField.getText().trim();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPwd);

        ResultSet userRow = databaseHandler.getUser(user);
        int counter = 0;

        try {
            while (userRow.next()) {
                counter++;
                String name = userRow.getString("firstname");
                System.out.println("Login successful " + name);
            }
            if (counter > 0) {
                System.out.println("Login successful");

            } else {
                System.out.println("Failed to log in");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void showScreen(String viewName){
        rootPane.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/"+viewName+".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


}


