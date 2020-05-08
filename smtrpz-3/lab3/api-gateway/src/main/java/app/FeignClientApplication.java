package app;

import app.Student.StudentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RefreshScope
public class FeignClientApplication {
    @Autowired
    StudentClient studentClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("test1", configClientAppConfiguration.getFirsttest());
        configmap.put("test2", configClientAppConfiguration.getSecondtest());
        configmap.put("test3", configClientAppConfiguration.getThirdtest());
        configmap.put("test4", configClientAppConfiguration.getFourthtest());
        configmap.putAll(studentClient.getConfig());
        return configmap;
    }

    @GetMapping("/")
    public String getInstanceId() {
        return studentClient.getInstanceId();
    }

    // read
    @GetMapping("/students")
    public String getUserById(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("Response from service: " + studentClient.getInstanceId());
        return studentClient.getUserById(id);
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
