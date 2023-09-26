package com.example.Swiggato.model;

import com.example.Swiggato.Enum.Gender;
import com.example.Swiggato.Enum.PartnerStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_partner")
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    String name;

    Gender gender;
    @Email
    @Column(unique = true)
    String email;

    @Size(min = 10, max = 10, message = "{Invalid Mobile Number}")
    @Column(unique = true, nullable = false)
    String mobileNo;

    PartnerStatus partnerStatus;

    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
