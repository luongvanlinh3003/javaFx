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
import java.util.ResourceBundle;

public class UpdateDeleteControler implements Initializable {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    public  UpdateDeleteControler(){
        connection = JDBC.getJdbcConnection();
    }

    public TextField idStudent;
    public TextField idF;
    public TextField idNameStudent;
    public TextField idAgeStudent;
    public TextField idDiaChiStudent;
    public TextField idNickNameStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public int updateStudent(Student student){
        int st = 0 ;
        String sql = "UPDATE `studentdb`.`student` SET `id` = ? , `name` = ? , `age` = ? , `diachi` = ? , `nickname` = ? WHERE (`id` = ? )";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getAge());
            preparedStatement.setString(4,student.getDiachi());
            preparedStatement.setString(5,student.getNickname());
            preparedStatement.setInt(6,student.getId());
            st = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public int delete(int id){
        int st = 0 ;
        String sql = "DELETE FROM `studentdb`.`student` WHERE (`id` = ? )";
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            st=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public Student getStudent(int id ){
        Student student = new Student();
        String sql = "SELECT * FROM studentdb.student where id= ? ";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                student.setId(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getString(3));
                student.setDiachi(resultSet.getString(4));
                student.setNickname(resultSet.getString(5));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    public void getStudent(ActionEvent actionEvent) {
        String idf = idF.getText();
        int id = Integer.parseInt(idf);
        Student student = getStudent(id);
        idStudent.setText(idf);
        idNameStudent.setText(student.getName());
        idAgeStudent.setText(student.getAge());
        idDiaChiStudent.setText(student.getDiachi());
        idNickNameStudent.setText(student.getNickname());
    }

    public void updateStudent(ActionEvent actionEvent) {
        String idf = idF.getText();
        int id = Integer.parseInt(idf);
        int txtId = Integer.parseInt(idStudent.getText());
        String txtName = idNameStudent.getText();
        String txtAge = idAgeStudent.getText();
        String txtDiaChi = idAgeStudent.getText();
        String txtNickName = idNickNameStudent.getText();
        Student student = new Student();
        student.setId(txtId);
        student.setName(txtName);
        student.setAge(txtAge);
        student.setDiachi(txtDiaChi);
        student.setNickname(txtNickName);
        int status = updateStudent(student);
        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Student");
            alert.setHeaderText("Information");
            alert.setContentText("Student da cap nhat lai ");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Student");
            alert.setHeaderText("Information");
            alert.setContentText("Student khong phu hop de cap nhat ");
            alert.showAndWait();
        }
    }

    public void DeleteStudent(ActionEvent actionEvent) {
            String idf = idF.getText();
            int id = Integer.parseInt(idf);
            int status = delete(id);
            if (status>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Student");
                alert.setHeaderText("Information");
                alert.setContentText("Student da bi remote");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Student");
                alert.setHeaderText("Information");
                alert.setContentText("Student ko bi remote");
                alert.showAndWait();
            }
    }
}
