package app;

import app.User.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableAutoConfiguration
public class FeignUserApplication {
    @Autowired
    UserClient userClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignUserApplication.class, args);
    }

    @GetMapping("/")
    public String getInstanceId() {
        return userClient.getInstanceId();
    }

    // read
    @GetMapping("/students")
    public String getStudentById(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("Response from service: " + userClient.getInstanceId());
        return userClient.getStudentById(id);
    }

    // create
    @PostMapping("/students")
    public Boolean addStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "email", required = true) String email) {
        return userClient.addStudent(id, name, email);
    }

    // update
    @PutMapping("/students")
    public Boolean updateStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "email", required = true) String email) {
        return userClient.updateStudent(id, name, email);
    }

    // delete
    @DeleteMapping("/students")
    public Boolean deleteStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id) {
        return userClient.deleteStudent(id);
    }

}
