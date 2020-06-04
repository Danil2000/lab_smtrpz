package app.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "eureka-client")
public interface UserClient {

    @GetMapping("/")
    String getInstanceId();

    // read
    @GetMapping("/students")
    String getStudentById(@RequestParam(value = "id", required = false) Integer id);

    // create
    @PostMapping("/students")
    public Boolean addStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "email", required = true) String email);

    // update
    @PutMapping("/students")
    public Boolean updateStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id,
                                 @RequestParam(value = "name", required = true) String name,
                                 @RequestParam(value = "email", required = true) String email);

    // delete
    @DeleteMapping("/students")
    public Boolean deleteStudent(@RequestBody
                                 @RequestParam(value = "id", required = true) Integer id);
}
