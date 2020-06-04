package app.Feedback;

public class Feedback {
    private Integer id;
    private Integer bookingId;
    private Integer trainId;
    private int rate;
    private String text;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setEquipmentId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
