package net.hectorm.springbootdatajpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice_lines")
public class InvoiceLine extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 628521486981499205L;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void prePersist(){
        setCreatedAt(new Date());
    }

    public Double linePrice(){
        return quantity.doubleValue() * product.getPrice();
    }
}
