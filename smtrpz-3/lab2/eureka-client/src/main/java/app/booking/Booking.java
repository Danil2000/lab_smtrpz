package app.booking;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
@Data
public class Booking {
    @Id
    private Integer id;
    private String  customer;
    private Integer train;
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrain() {
        return train;
    }
    public void setTrain(Integer train) {
        this.train = train;
    }

    public String getCustomer()  {
        return customer;
    }
    public void setCustomer(String customer) { this.customer = customer;}
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
}
