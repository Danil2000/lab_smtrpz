package app.Feedback;

import app.CustomException;
import app.booking.BookingClient;
import app.train.Train;
import app.train.TrainClient;
import app.user.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/feedback")
public class FeedbackController {
    @Autowired
    FeedbackClient client;

    @Autowired
    TrainClient trainClient;

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    Feedback add(@RequestBody Feedback feedback, @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isClient(token);
        Integer trainId = bookingClient.getById(feedback.getBookingId()).getTrain();
        Train train = trainClient.getById(trainId);
        train.setRating((train.getRating() + feedback.getRate())/2);
        trainClient.update(trainId, train);
        return client.add(feedback);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getAll() throws CustomException {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Feedback getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path = "/booking/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByOrderId(@PathVariable Integer id) {
        return client.getByOrderId(id);
    }

    @RequestMapping(path = "/train/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Feedback> getByEquipmentId(@PathVariable Integer id) throws CustomException {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.delete(id);
    }
}
