package model;

public class Student{
    private int id;
    private String name;
    private String age;
    private String diachi;
    private String nickname;

    public Student() {
    }

    public Student(int id, String name, String age, String diachi, String nickname) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diachi = diachi;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
