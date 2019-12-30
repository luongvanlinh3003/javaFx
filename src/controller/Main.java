package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private AnchorPane anchorPane;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.stage.setTitle("Login");
        showViewLogin();
    }
    private void showViewLogin()throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Login.fxml"));
        anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
