package com.example.Normad_stay_real_backend.Health;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {


    @GetMapping("/health")

    public String getHealth(){
        return "all ok";
    }
}
