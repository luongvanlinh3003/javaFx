package controller;

import dao.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Student;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;

public class InsertController implements Initializable {
    public TextField insertId;
    public TextField insertName;
    public TextField insertAge;
    public TextField insertDiaChi;
    public TextField insertNickName;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public InsertController() {
        connection = JDBC.getJdbcConnection();
    }
    public int save(Student student){
        int st =0;
        String sql = "INSERT INTO `studentdb`.`student` (`id`, `name`, `age`, `diachi`, `nickname`)" +
                " VALUES (?,?,?,?,?)";
        try {
          preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setInt(1,student.getId());
          preparedStatement.setString(2,student.getName());
          preparedStatement.setString(3,student.getAge());
          preparedStatement.setString(4,student.getDiachi());
          preparedStatement.setString(5,student.getNickname());
          st = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void InsertEvent(ActionEvent actionEvent) {
        int id = Integer.parseInt(insertId.getText());
        String name = insertName.getText();
        String age = insertAge.getText();
        String diachi = insertDiaChi.getText();
        String nickname = insertNickName.getText();

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setDiachi(diachi);
        student.setNickname(nickname);

        int status = save(student);
        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Student");
            alert.setHeaderText("Information");
            alert.setContentText("Ban muon chac chan them 1 student");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Student");
            alert.setHeaderText("Information");
            alert.setContentText("Thong tin khong chinh xac");
            alert.showAndWait();
        }
    }

    public void ViderEvent(ActionEvent actionEvent) {
        insertId.clear();
        insertName.clear();
        insertAge.clear();
        insertDiaChi.clear();
        insertNickName.clear();
    }
}
