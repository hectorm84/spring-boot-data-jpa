package net.hectorm.springbootdatajpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "clients")
@NamedQueries(
        @NamedQuery(name = "Client.findByFirstName", query = "SELECT C FROM Client C WHERE C.firstName = :fname")
)
public class Client extends BaseEntity implements Serializable {

    private static String FIND_BY_FIRST_NAME = "Client.findByFirstName";

    private static final long serialVersionUID = 2860485148506832290L;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    private String image;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    /*Methods*/

    public Client() {
        this.invoices = new HashSet<>();
    }

    public void addInvoice(Invoice invoice){
        invoices.add(invoice);
    }

    @Override
    public String toString() {
        return "Client: " + firstName + ' '+ lastName ;
    }
}
