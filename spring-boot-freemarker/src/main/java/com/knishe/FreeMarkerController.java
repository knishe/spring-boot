package com.knishe;

import com.knishe.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreeMarkerController {
    @GetMapping("/employee")
    public Employee employee() {
        return Employee.builder()
                .name("SampleName")
                .build();
    }
}
