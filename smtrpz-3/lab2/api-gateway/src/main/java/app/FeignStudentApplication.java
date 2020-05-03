package app;

import app.Student.StudentClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableAutoConfiguration
public class FeignStudentApplication {
    @Autowired
    StudentClient studentClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignStudentApplication.class, args);
    }

    @GetMapping("/")
    public String getInstanceId() {
        return studentClient.getInstanceId();
    }

    // read
    @GetMapping("/students")
    public String getStudentById(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("Response from service: " + studentClient.getInstanceId());
        return studentClient.getStudentById(id);
    }

    // create
    @PostMapping("/students")
    public Boolean addStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "email", required = true) String email) {
        return studentClient.addStudent(id, name, email);
    }

    // update
    @PutMapping("/students")
    public Boolean updateStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "email", required = true) String email) {
        return studentClient.updateStudent(id, name, email);
    }

    // delete
    @DeleteMapping("/students")
    public Boolean deleteStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id) {
        return studentClient.deleteStudent(id);
    }

}
