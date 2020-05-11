package app;

import app.Student.StudentClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
public class ProxyService {
    private static final String BACKEND_A = "eureka-client";

    @Autowired
    private StudentClient studentClient;

    @ResponseBody
    Map<String, String> getConfig() {
        return studentClient.getConfig();
    }

    String getInstanceId() {
        return studentClient.getInstanceId();
    }

    // read
    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    String getStudentById(@RequestParam(value = "id", required = false) Integer id) {
        return studentClient.getStudentById(id);
    }

    public String fallback(Throwable e) {
        return new String("Fallback");
    }

    // create
    @Retry(name = BACKEND_A)
    public ResponseEntity<String> addStudent(@RequestBody
                                          @RequestParam(value = "id", required = true) Integer id,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "email", required = true) String email) {
        return studentClient.addStudent(id,name,email);
    }

    // update
    @Retry(name = BACKEND_A)
    public ResponseEntity<String> updateStudent(@RequestBody
                                             @RequestParam(value = "id", required = true) Integer id,
                                             @RequestParam(value = "name", required = true) String name,
                                             @RequestParam(value = "email", required = true) String email) {
        return studentClient.updateStudent(id,name, email);
    }

    // delete
    public ResponseEntity<String> deleteStudent(@RequestBody
                                             @RequestParam(value = "id", required = true) Integer id) {
        return studentClient.deleteStudent(id);
    }
}