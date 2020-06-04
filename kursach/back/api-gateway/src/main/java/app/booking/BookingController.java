package app.booking;

import app.CustomException;
import app.train.TrainClient;
import app.user.UserClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4203")
@RequestMapping(path = "/booking")
public class BookingController {
    @Autowired
    BookingClient client;
    @Autowired
    UserClient userClient;
    @Autowired
    TrainClient trainClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking,
                                     @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return client.add(booking);
    }
    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll(@RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        Iterable<Booking> bookings = client.getAll();
        for (Booking b : bookings) {
            b.setTrain(trainClient.getById(b.getTrain()));
        }
        return bookings;
    }
    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException {
        Booking booking = client.getById(id);
        booking.setTrain(trainClient.getById(booking.getTrain()));
        return client.getById(id);
    }
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id,
                                                          @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isClient(token);
        Iterable<Booking> bookings = client.getUsersOrders(id);
        for(Booking b: bookings) {
            b.setTrain(trainClient.getById(b.getTrain()));
        }
        return bookings;
    }
    @RequestMapping(path = "/train/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getTrainOrders(@PathVariable Integer id,
                                                              @RequestHeader(value = "Authorization") String token) {
        userClient.isClient(token);
        return client.getTrainsOrders(id);
    }
    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.delete(id);
    }
}