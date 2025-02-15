//package com.swarnava.ghostrider.entity.key;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.Embeddable;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotEmpty;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Data
//@Embeddable
//@NoArgsConstructor
//public class RiderId implements Serializable {
//
//    private String id;
//
//    @NotEmpty
//    @NonNull
//    private String phoneNumber;
//
//    public RiderId(String phoneNumber) {
//        id = "692e0b74-1467-468f-805b-958f73457343";//UUID.randomUUID().toString();
//        this.phoneNumber = phoneNumber;
//    }
//}
