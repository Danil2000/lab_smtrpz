package app.Student;

import com.google.gson.Gson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RefreshScope
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @Value("${test.fifthtest}")
    private String fifthteste;

    @Value("${test.sixthtest}")
    private String sixteste;

    @GetMapping("/config")
    public @ResponseBody
    HashMap<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("fifth", fifthteste);
        configmap.put("sixth", sixteste);
        return configmap;
    }

    @GetMapping("/")
    public String getInstanceId() {
        return instanceId;
    }

    // read
    @GetMapping("/students")
    public String getStudentById(@RequestParam(value = "id", required = false) Integer id) {

        List<Student> resultList = new ArrayList<>();
        if (id == null) {
            Iterable<Student> result = studentRepository.findAll();
            result.forEach(resultList::add);
        }
        else {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                resultList.add(student.get());
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(resultList);
        return prettyJson;
    }

    // create
    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody
                               @RequestParam(value = "id", required = true) Integer id,
                                  @RequestParam(value = "name", required = true) String name,
                                  @RequestParam(value = "email", required = true) String email) {
        Optional<Student> test = studentRepository.findById(id);
        if (test.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("This id is already busy.");
        }
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        studentRepository.save(student);
        return ResponseEntity.ok("OK");
    }

    // update
    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody
                                      @RequestParam(value = "id", required = true) Integer id,
                                  @RequestParam(value = "name", required = true) String name,
                                  @RequestParam(value = "email", required = true) String email) {
        Optional<Student> test = studentRepository.findById(id);
        if (!test.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Can`t find this user.");
        }
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        studentRepository.save(student);
        return ResponseEntity.ok("OK");
    }

    // delete
    @DeleteMapping("/students")
    public ResponseEntity<String> deleteStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id) {
        Optional<Student> test = studentRepository.findById(id);
        if (!test.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Can`t find this user.");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok("OK");
    }
}