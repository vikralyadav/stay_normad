package com.example.Normad_stay_real_backend.Stay.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;



@Data
public class GetStayRequest {


    @NotBlank(message = "Please Enter City")
    private String city;
}
