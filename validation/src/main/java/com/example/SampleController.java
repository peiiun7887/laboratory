package com.example;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class SampleController {

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody @Valid SampleDTO sampleDTO) {
        System.out.println("host: " + sampleDTO.getHost());
        return ResponseEntity.ok("successful");
    }
}
