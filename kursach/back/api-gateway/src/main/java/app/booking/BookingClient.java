package app.booking;

import app.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "booking-service")
public interface BookingClient {
    @RequestMapping(path = "/booking", method = RequestMethod.POST)
    public @ResponseBody Booking add(@RequestBody Booking booking);
    @RequestMapping(path = "/booking", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getAll();
    @RequestMapping(path = "/booking/{id}", method = RequestMethod.POST)
    public @ResponseBody Booking getById(@PathVariable int id) throws CustomException;
    @RequestMapping(path = "/booking/user/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getUsersOrders(@PathVariable Integer id);
    @RequestMapping(path = "/booking/train/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Booking> getTrainsOrders(@PathVariable Integer id);
    @RequestMapping(path = "/booking/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;
    @RequestMapping(path = "/booking/train/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteByTrain(@PathVariable Integer id) throws CustomException;
}
