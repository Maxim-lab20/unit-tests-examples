package max.demo.unittests.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Car {

    private String brand;
    private LocalDate manufactureYear;
    private String model;
    private Integer numberOfSeats;
    private Float engineCapacity;

}
