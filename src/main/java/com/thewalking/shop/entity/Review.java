package com.thewalking.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(updatable = false, nullable = false)
    private Long id;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @OneToOne(cascade = CascadeType.DETACH)
    private User user;
    private String title;
    private String message;
    private double rating;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    ProductDescription productDescription;
    @OneToOne(cascade = CascadeType.DETACH)
    Review inReplyTo;

}
