package app.train;

import app.CustomException;
import app.booking.BookingClient;
import app.user.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/equipment")
public class TrainController {
    @Autowired
    TrainClient client;

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserClient userClient;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody Train add(@RequestBody Train train,
                  @RequestHeader(value = "Authorization") String token) {
        userClient.isAdmin(token);
        return client.add(train);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Train> getAll() {
        return client.getAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Train getById(@PathVariable int id) throws CustomException {
        return client.getById(id);
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Train getIdByName(@PathVariable String name) {
        return client.getIdByName(name);
    }

    @RequestMapping(path="/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Train> getByFilter(@PathVariable String destination) {
        return client.getByFilter(destination);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody Train update(@PathVariable Integer id,
                                          @RequestBody Train train,
                                          @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        return client.update(id, train);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id,
                                       @RequestHeader(value = "Authorization") String token) throws CustomException {
        userClient.isAdmin(token);
        bookingClient.deleteByTrain(id);
        return client.delete(id);
    }
}
