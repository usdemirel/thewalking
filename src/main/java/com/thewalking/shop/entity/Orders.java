package com.thewalking.shop.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;
    private String status;
    private double itemsSubTotal;
    private double shippingHandling;
    private double totalBeforeTax;
    private double estimatedTaxToBeCollected;
    private double grandTotal;

}
