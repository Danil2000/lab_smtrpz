package app.Student;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "eureka-client")
public interface StudentClient {

    @GetMapping("/config")
    @ResponseBody
    Map<String, String> getConfig();

    @GetMapping("/")
    String getInstanceId();

    // read
    @GetMapping("/students")
    String getStudentById(@RequestParam(value = "id", required = false) Integer id);

    // create
    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody
                           @RequestParam(value = "id", required = true) Integer id,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "email", required = true) String email);

    // update
    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "email", required = true) String email);

    // delete
    @DeleteMapping("/students")
    public ResponseEntity<String> deleteStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id);
}
