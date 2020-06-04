package app.train;

import app.CustomException;
import app.booking.Booking;
import app.booking.BookingClient;
import app.user.UserClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@FeignClient(value="train-service")
public interface TrainClient {
    @RequestMapping(path="/train", method = RequestMethod.POST)
    public @ResponseBody Train add(@RequestBody Train train);

    @RequestMapping(path="/train", method = RequestMethod.GET)
    public @ResponseBody Iterable<Train> getAll();

    @RequestMapping(path="/train/{id}", method = RequestMethod.GET)
    public @ResponseBody Train getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path="/train/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Train getIdByName(@PathVariable String name);

    @RequestMapping(path="/train/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Train> getByFilter(@PathVariable String type);

    @RequestMapping(path="/train/{id}", method = RequestMethod.PUT)
    public @ResponseBody Train update(@PathVariable Integer id,
                                          @RequestBody Train train) throws CustomException;

    @RequestMapping(path="/train/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;
}