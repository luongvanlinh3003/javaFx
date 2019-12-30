package controller;

import dao.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DetailController implements Initializable {
    public ToggleGroup noOne;
    public TextField idTextFind;
    Connection connection = null;
    ResultSet resultSet = null;
    public TableView <Student>idAllStudent;
    public TableColumn<Student, Integer> idStudent;
    public TableColumn<Student,String> idNickName;
    public TableColumn<Student,String> idDiaChi;
    public TableColumn<Student,String> idTuoi;
    public TableColumn<Student,String> idName;

    public DetailController() {
        connection = JDBC.getJdbcConnection();
    }
    ObservableList<Student> listStudent = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            idStudent.setCellValueFactory(new PropertyValueFactory<>("id"));
            idTuoi.setCellValueFactory(new PropertyValueFactory<>("age"));
            idDiaChi.setCellValueFactory(new PropertyValueFactory<>("diachi"));
            idName.setCellValueFactory(new PropertyValueFactory<>("name"));
            idNickName.setCellValueFactory(new PropertyValueFactory<>("nickname"));

            try {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM studentdb.student ");
                while (resultSet.next()){
                    listStudent.add(new Student(resultSet.getInt("id"),resultSet.getString("name"),
                            resultSet.getString("age"),resultSet.getString("diachi"),
                            resultSet.getString("nickname")));
                }
                idAllStudent.setItems(listStudent);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void NewStudent(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/insert.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("Create Student");
        stage.show();
    }

    public void UpdateAndDelete(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/updateDelete.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("UpdateAndDelete");
        stage.show();
    }
}
