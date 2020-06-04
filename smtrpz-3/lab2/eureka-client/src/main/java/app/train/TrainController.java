package app.train;

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
public class TrainController {
    @Autowired
    TrainRepository trainRepository;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @GetMapping("/train/id")
    public String getInstanceId() {
        return instanceId;
    }

    // read
    @GetMapping("/trains")
    public String getTrainById(@RequestParam(value = "id", required = false) Integer id) {

        List<Train> resultList = new ArrayList<>();
        if (id == null) {
            Iterable<Train> result = trainRepository.findAll();
            result.forEach(resultList::add);
        }
        else {
            Optional<Train> user = trainRepository.findById(id);
            if (user.isPresent()) {
                resultList.add(user.get());
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(resultList);
        return prettyJson;
    }

    // create
    @PostMapping("/trains")
    public Boolean addTrain(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "number", required = true) Integer number,
                              @RequestParam(value = "destination", required = true) String destination) {
        Optional<Train> test = trainRepository.findById(id);
        if (test.isPresent()) {
            return false;
        }
        Train train = new Train();
        train.setId(id);
        train.setNumber(number);
        train.setDestination(destination);
        trainRepository.save(train);

        return true;
    }

    // update
    @PutMapping("/trains")
    public Boolean updateTrain(@RequestBody
                                   @RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "number", required = true) Integer number,
                               @RequestParam(value = "destination", required = true) String destination) {
        Optional<Train> test = trainRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        Train train = new Train();
        train.setId(id);
        train.setNumber(number);
        train.setDestination(destination);
        trainRepository.save(train);

        return true;
    }

    // delete
    @DeleteMapping("/trains")
    public Boolean deleteTrain(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id) {
        Optional<Train> test = trainRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        trainRepository.deleteById(id);
        return true;
    }
}