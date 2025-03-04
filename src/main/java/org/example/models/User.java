package org.example.models;

import java.time.LocalDate;

public class User {
    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String login;
    private String password;
    private Boolean is_blocked;

    public User(int id, String name, String surname, LocalDate birthdate, String login, String password, boolean is_blocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.login = login;
        this.password = password;
        this.is_blocked = is_blocked;

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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isIs_blocked() {
        return is_blocked;
    }
    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }
}
