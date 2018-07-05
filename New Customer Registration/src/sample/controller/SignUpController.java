package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpTelNumber;

    @FXML
    private TextField signUpPersonalId;

    @FXML
    private TextArea signUpInfo;

    @FXML
    private TextField signUpZipCode;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {

        signUpButton.setOnAction(ev->{
            createUser();
        });


    }
    private void createUser(){
        String name = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String telNumber =signUpTelNumber.getText().trim().replace("-","");
        String zipCode = signUpZipCode.getText().trim().replace("-","");
        String info = signUpInfo.getText();
        String personalId = signUpPersonalId.getText().trim();
        String username;
        String password;

//        If we treat name, lastName, telNumber, personalId as a must, before we push them to our DB, we need to check if they are not empty,
//        telNumber is 9 numbers length, and personal Id have 9 numbers

        if(!name.equals("") && !lastName.equals("") && !telNumber.equals("")
            && StringUtils.isStrictlyNumeric(telNumber) && telNumber.length()==9
                && !personalId.equals("") && personalId.length()==11 && StringUtils.isStrictlyNumeric(personalId)) {

            username=name.substring(0,3)+ lastName.substring(0,3)+Math.floor(Math.random()*500);
            password = ""+Math.floor(Math.random()*900);

            User user = new User(name, lastName, telNumber, personalId, zipCode, info, username,password);
            DatabaseHandler databaseHandler = new DatabaseHandler();

            databaseHandler.SignUpUser(user);
        }
    }
}


