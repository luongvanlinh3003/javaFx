package controller;

import dao.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label labelId;
    public Button loginId;
    public PasswordField passwordId;
    public TextField userId;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public LoginController() {
        connection = JDBC.getJdbcConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void loginAction(ActionEvent actionEvent) {
        String username = userId.getText();
        String password = passwordId.getText();
        String sql = "SELECT *FROM studentdb.login WHERE username = ? AND password = ?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/detail.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(scene);
                stage.setTitle("StudentManager");
                stage.show();
            }else {
                labelId.setText("Fail");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
