package app.booking;

import app.train.Train;

//import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Table;

public class Booking {
    @Id
    private Integer id;
    private String  customer;
    private Train train;
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTrain() {
        return train.getId();
    }
    public void setTrain(Train train) {
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
