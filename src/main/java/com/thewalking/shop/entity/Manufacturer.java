package com.thewalking.shop.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private String contactPerson;
    private String email;
    private String phone;
    private String ext;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
}
