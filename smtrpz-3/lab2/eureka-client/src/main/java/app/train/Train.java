package app.train;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "train")
@Data
public class Train {
    @Id
    private Integer id;
    private Integer number;
    private String destination;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDestination()  {
        return destination;
    }
    public void setDestination(String destination) { this.destination = destination;}

}
