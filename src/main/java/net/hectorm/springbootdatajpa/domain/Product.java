package net.hectorm.springbootdatajpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7323133707497180095L;

    private String name;
    private Double price;

    @PrePersist
    public void prePersist(){
        setCreatedAt(new Date());
    }
}
