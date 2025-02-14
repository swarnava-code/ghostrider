package com.swarnava.ghostrider.dto;

import com.swarnava.ghostrider.entity.Rider;
import com.swarnava.ghostrider.enume.Gender;
import com.swarnava.ghostrider.enume.RiderAvailability;
import com.swarnava.ghostrider.exception.MandatoryDataMissingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRiderDTO implements Serializable {
    private String name;
    private Gender gender;
    private String email;
    private String emergencyContact;
    private String permanentAddress;
    private LocalDateTime dob;

    public Rider getEntity(Rider entity) {
        if (name == null || gender == null || email == null || emergencyContact == null || permanentAddress == null
                || name.trim().equals("") || email.trim().equals("") || emergencyContact.trim().equals("")
                || permanentAddress.trim().equals("") || dob == null
        ) {
            throw new MandatoryDataMissingException("Fields are mandatory while you creating new rider",
                    this.toString());
        }
         entity.setEmail(email);
        if (emergencyContact != null) entity.setEmergencyContact(emergencyContact);
        if (gender != null) entity.setGender(gender);
        if (name != null) entity.setName(name);
        if (permanentAddress != null) entity.setPermanentAddress(permanentAddress);
        if (dob != null) entity.setDob(dob);
        entity.setRiderAvailability(RiderAvailability.BREAK);
        return entity;
    }
}
