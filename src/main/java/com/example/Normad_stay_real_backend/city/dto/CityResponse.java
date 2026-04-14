package com.example.Normad_stay_real_backend.city.dto;
import com.example.Normad_stay_real_backend.city.entity.Cities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private List<Cities> citiesList;
}
