package app.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Integer bank_id;

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

}
