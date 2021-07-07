package com.thewalking.shop.entity;

import com.thewalking.shop.model.Auditable;
import jdk.jfr.Category;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProductDescription extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;
    private boolean isActive;
    private String keyWords;
    private String brand;
    private String categories;
    @OneToMany(mappedBy = "productDescription")
    private List<Review> reviews;
    @OneToOne(cascade = CascadeType.DETACH)
    private Manufacturer manufacturer;



}
