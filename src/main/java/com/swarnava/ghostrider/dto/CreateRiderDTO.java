package com.swarnava.ghostrider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swarnava.ghostrider.enume.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRiderDTO implements Serializable {

    @NotEmpty
    @NotNull
    @NotBlank
    @Length(min = 2)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotEmpty
    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotBlank
    @NotNull
    @Length(min = 8, max = 13)
    @Pattern(regexp = "\\d+", message = "Phone number must contain only numbers")
    private String emergencyContact;

    @NotEmpty
    @NotBlank
    @NotNull
    @Length(min = 8)
    private String permanentAddress;

    @NotEmpty
    @NotBlank
    @NotNull
    @Length(min = 8, max = 13)
    @Pattern(regexp = "\\d+", message = "Phone number must contain only numbers")
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dob;


}
