package com.thewalking.shop.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @JsonProperty("dateCreated") //for serializing and deserializing -sd
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @NotNull
    @Column(unique=true)
    private String branchName;
    private boolean isActive;

    @OneToOne (cascade = CascadeType.PERSIST)
    private Address address;
}
