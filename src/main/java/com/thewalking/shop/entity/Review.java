package com.thewalking.shop.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @OneToOne(cascade = CascadeType.DETACH)
    private User user;
    private String title;
    private String message;
    private double rating;
    @ManyToOne(cascade = CascadeType.DETACH)
    ProductDescription productDescription;
    @OneToOne(cascade = CascadeType.DETACH)
    Review inReplyTo;
}
