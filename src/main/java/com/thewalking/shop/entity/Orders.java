package com.thewalking.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;
    private String status;
    private double itemsSubTotal;
    private double shippingHandling;
    private double totalBeforeTax;
    private double estimatedTaxToBeCollected;
    private double grandTotal;
    @ManyToOne(cascade = CascadeType.DETACH)
    Customer customer;

}
