package app.Feedback;

import app.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@FeignClient(value="feedback-service")
public interface FeedbackClient {
    @RequestMapping(path="/feedback", method = POST)
    public @ResponseBody Feedback add(@RequestBody Feedback feedback);

    @RequestMapping(path="/feedback", method = GET)
    public @ResponseBody Iterable<Feedback> getAll();

    @RequestMapping(path="/feedback/{id}", method = GET)
    public @ResponseBody Feedback getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path = "/feedback/booking/{id}", method = GET)
    public @ResponseBody Iterable<Feedback> getByOrderId(@PathVariable Integer id);

    @RequestMapping(path="/feedback/{id}", method = DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;

    @RequestMapping(path="/feedback/train/{id}", method = DELETE)
    public @ResponseBody String deleteByTraintId(@PathVariable Integer id) throws CustomException;
}
