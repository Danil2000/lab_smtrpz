package app.Student;

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
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

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
    public Boolean addStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "email", required = true) String email) {
        Optional<Student> test = studentRepository.findById(id);
        if (test.isPresent()) {
            return false;
        }
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        studentRepository.save(student);

        return true;
    }

    // update
    @PutMapping("/students")
    public Boolean updatestudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "email", required = true) String email) {
        Optional<Student> test = studentRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        studentRepository.save(student);

        return true;
    }

    // delete
    @DeleteMapping("/students")
    public Boolean deleteStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id) {
        Optional<Student> test = studentRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
}