package com.app.authentification.User;

import com.app.authentification.Roles;

public class User {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Integer bank_id;
    private String password;
    private Roles role;

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
    public String getsurname()  {
        return surname;
    }
    public void setSurname(String surname) { this.surname = this.surname; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getBank_id()  {
        return bank_id;
    }
    public void setBank_id(Integer bank_id) {
        this.bank_id = bank_id;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("Tourist: id=%d name=%s email=%s role=%s",
                id, name, email, role);
    }
}
