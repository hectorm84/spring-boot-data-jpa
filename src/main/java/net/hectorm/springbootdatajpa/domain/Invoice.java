package net.hectorm.springbootdatajpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -226151845850326671L;

    @NotEmpty
    private String description;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Set<InvoiceLine> invoiceLines = new HashSet<>();

    @PrePersist
    public void prePersist(){
        setCreatedAt(new Date());
    }

    public Double invoiceTotalPrice(){
        Double total = 0.0;
        for (InvoiceLine line:invoiceLines) {
            total+=line.linePrice();
        }
        return total;
    }
    public void addInvoiceLine(InvoiceLine invoiceLine){
        invoiceLines.add(invoiceLine);
    }
}
