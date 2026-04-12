package com.firat.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStudentIU {

    @NotEmpty(message = "FirstName boş bırakılmaz! ")
    @Min(value = 3)
    @Max(value = 10)
    private String firstName;

    @Size(min = 3, max = 30)
    private String lastName;


    private String birthOfDate;

}
