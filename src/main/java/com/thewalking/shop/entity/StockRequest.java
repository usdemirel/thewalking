package com.thewalking.shop.entity;

import com.thewalking.shop.model.Auditable;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class StockRequest extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Stock stock;
    private int quantityRequested;
    @OneToOne
    Employee manager; //requester
    private Boolean status;
    @OneToOne
    Employee owner; // approver

}
