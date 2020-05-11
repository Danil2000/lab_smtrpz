package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RefreshScope
@EnableRetry
@EnableCircuitBreaker
public class FeignStudentApplication {
    @Autowired
    ProxyService studentClient;
//    UserClient userClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignStudentApplication.class, args);
    }

    @Autowired
    ConfigStudentAppConfiguration configStudentAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("firsttest", configStudentAppConfiguration.getFirsttest());
        configmap.put("secondtest", configStudentAppConfiguration.getSecondtest());
        configmap.put("thirdtest", configStudentAppConfiguration.getThirdtest());
        configmap.put("fourhtest", configStudentAppConfiguration.getFourthtest());
        configmap.putAll(studentClient.getConfig());
        return configmap;
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
    public ResponseEntity<String> addStudent(@RequestBody
                           @RequestParam(value = "id", required = true) Integer id,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "email", required = true) String email) {
        return studentClient.addStudent(id, name, email);
    }

    // update
    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "email", required = true) String email) {
        return studentClient.updateStudent(id, name, email);
    }

    // delete
    @DeleteMapping("/students")
    public ResponseEntity<String> deleteStudent(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id) {
        return studentClient.deleteStudent(id);
    }

}
