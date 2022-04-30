package app.reflection;

public class Person {

    public String userName = "Ivan";
    public String email = "ivan@mail.ru";
    private String password = "secretPassword";

    public Person() {
    }

    public Person(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
