package org.example.models;

import java.time.LocalDate;

public class User {
    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String login;
    private String password;
    private Boolean isBlocked;

    public User(Integer id, String name, String surname, LocalDate birthDate, String login, String password,  Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.isBlocked = isBlocked;

    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
    public Boolean getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", birth_date=" + birthDate + ", login=" + login + ", password=" + password + ", is_blocked=" + isBlocked + "]";
    }
}
