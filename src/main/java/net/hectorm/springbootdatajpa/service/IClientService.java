package net.hectorm.springbootdatajpa.service;

import net.hectorm.springbootdatajpa.domain.Client;
import net.hectorm.springbootdatajpa.domain.Invoice;
import net.hectorm.springbootdatajpa.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IClientService {

    Set<Client> findAll();

    Client findById(Long id);

    Page<Client> findAll(Pageable pageable);

    Set<Client> findByLastName(String lastName);

    Set<Client> findByFirstName(String firstName);

    Client save(Client client);

    void delete(Client client);

    void deleteById(Long id);

    Set<Product> findByNameLikeIgnoreCase(String criteria);

    Set<Product> findByName(String criteria);

    Invoice saveInvoice(Invoice invoice);

    Product findProductById(Long id);

    Invoice findInvoiceById(Long id);

    void deleteInvoice(Invoice invoice);

    void deleteInvoiceById(Long id);
}
