package com.example.exercisejparelationill.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddressDTO_In {


    private Integer id;
    @NotEmpty(message = "Area must not be blank")
    @Size(min = 2, max = 100, message = "Area must be between 2 and 100 characters")
    private String area;

    @NotEmpty(message = "Street must not be blank")
    @Size(min = 2, max = 100, message = "Street must be between 2 and 100 characters")
    private String street;

    @NotNull(message = "Building number is required")
    @Min(value = 1, message = "Building number must be at least 1")
    private Integer buildingNumber;

    private Integer teacher_id;
}
