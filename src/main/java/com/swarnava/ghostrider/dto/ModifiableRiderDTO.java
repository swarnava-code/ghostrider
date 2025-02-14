package com.swarnava.ghostrider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.enume.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifiableRiderDTO {
    private String name;
    private String email;
    private Gender gender;

    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime dob;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String emergencyContact;
    private String permanentAddress;

    public Rider getEntity(Rider entity, ModifiableRiderDTO dto) {
        if (dto.dob != null) entity.setDob(dto.dob);
        if (dto.email != null) entity.setEmail(dto.email);
        if (dto.emergencyContact != null) entity.setEmergencyContact(dto.emergencyContact);
        if (dto.gender != null) entity.setGender(dto.gender);
        if (dto.name != null) entity.setName(dto.name);
        if (dto.permanentAddress != null) entity.setPermanentAddress(dto.permanentAddress);
        return entity;
    }
}
