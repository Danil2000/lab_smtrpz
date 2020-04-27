package com.eurekaclient.Student;

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
    private Integer instanceId;

    @GetMapping("/instance")
    public Integer getInstanceId() {
        return instanceId;
    }

    @GetMapping("/Students")
    public String getUserById(@RequestParam(value = "id", required = false) Integer id) {

        List<Student> resultList = new ArrayList<>();
        if (id == null) {
            Iterable<Student> result = StudentRepository.findAll();
            result.forEach(resultList::add);
        }
        else {
            Optional<Student> Student = StudentRepository.findById(id);
            if (Student.isPresent()) {
                resultList.add(Student.get());
            }
        }
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(resultList);
        return prettyJson;
    }

    @PostMapping("/Students")
    public Boolean addStudent(@RequestBody
                               @RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "name", required = true) String name,
                               @RequestParam(value = "url", required = true) String url) {
        Optional<Student> test = StudentRepository.findById(id);
        if (test.isPresent()) {
            return false;
        }
        Student Student = new Student();
        Student.setId(id);
        Student.setName(name);
        Student.setUrl(url);
        StudentRepository.save(Student);

        return true;
    }

    @PutMapping("/Students")
    public Boolean updateStudent(@RequestBody
                               @RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "name", required = true) String name,
                               @RequestParam(value = "url", required = true) String url) {
        Optional<Student> test = StudentRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        Student Student = new Student();
        Student.setId(id);
        Student.setName(name);
        Student.setUrl(url);
        StudentRepository.save(Student);

        return true;
    }

    @DeleteMapping("/Students")
    public Boolean deleteStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id) {
        Optional<Student> test = StudentRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        StudentRepository.deleteById(id);
        return true;
    }
}