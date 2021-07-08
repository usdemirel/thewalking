package com.thewalking.shop.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class StockRequest implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    LocalDateTime timestamp;
    @UpdateTimestamp
    private LocalDateTime updatedDateTime;
    @OneToOne
    private Stock stock;
    private int quantityRequested;
    @OneToOne
    Manager manager; //requester
    private String status;
    @OneToOne
    Owner owner; // approver


}
