package app.booking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @GetMapping("/booking/id")
    public String getInstanceId() {
        return instanceId;
    }

    // read
    @GetMapping("/bookings")
    public String getBookingById(@RequestParam(value = "id", required = false) Integer id) {

        List<Booking> resultList = new ArrayList<>();
        if (id == null) {
            Iterable<Booking> result = bookingRepository.findAll();
            result.forEach(resultList::add);
        }
        else {
            Optional<Booking> user = bookingRepository.findById(id);
            if (user.isPresent()) {
                resultList.add(user.get());
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(resultList);
        return prettyJson;
    }

    // create
    @PostMapping("/bookings")
    public Boolean addBooking(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "customer", required = true) String customer,
                              @RequestParam(value = "train", required = true) Integer train,
                              @RequestParam(value = "price", required = true) Integer price) {
        Optional<Booking> test = bookingRepository.findById(id);
        if (test.isPresent()) {
            return false;
        }
        Booking booking = new Booking();
        booking.setId(id);
        booking.setCustomer(customer);
        booking.setTrain(train);
        booking.setPrice(price);
        bookingRepository.save(booking);
        return true;
    }

    // update
    @PutMapping("/bookings")
    public Boolean updateBooking(@RequestBody
                                   @RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "customer", required = true) String customer,
                               @RequestParam(value = "train", required = true) Integer train,
                               @RequestParam(value = "price", required = true) Integer price) {
        Optional<Booking> test = bookingRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        Booking booking = new Booking();
        booking.setId(id);
        booking.setCustomer(customer);
        booking.setTrain(train);
        booking.setPrice(price);
        bookingRepository.save(booking);

        return true;
    }

    // delete
    @DeleteMapping("/bookings")
    public Boolean deleteBooking(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id) {
        Optional<Booking> test = bookingRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }
}