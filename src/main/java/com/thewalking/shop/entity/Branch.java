package com.thewalking.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("dateCreated") //for serializing and deserializing -sd
    private LocalDateTime dateCreated;
    @NotNull
    @Column(unique=true)
    private String branchName;
    private boolean isActive;

    @OneToOne (cascade = CascadeType.PERSIST)
    private Address address;
}
