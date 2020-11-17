package net.hectorm.springbootdatajpa.service;

import net.hectorm.springbootdatajpa.domain.Client;
import net.hectorm.springbootdatajpa.domain.Invoice;
import net.hectorm.springbootdatajpa.domain.Product;
import net.hectorm.springbootdatajpa.repositories.IClientRepositoryJpa;
import net.hectorm.springbootdatajpa.repositories.IInvoiceRepositoryJpa;
import net.hectorm.springbootdatajpa.repositories.IProductRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("repositoryDataJpa")
public class ClientServiceJpaImpl implements IClientService{


    private final IClientRepositoryJpa clientRepository;
    private final IProductRepositoryJpa productRepository;
    private final IInvoiceRepositoryJpa invoiceRepositoryJpa;

    @Autowired
    public ClientServiceJpaImpl(IClientRepositoryJpa repositoryJpa, IProductRepositoryJpa productRepository, IInvoiceRepositoryJpa invoiceRepositoryJpa) {
        this.clientRepository = repositoryJpa;
        this.productRepository = productRepository;
        this.invoiceRepositoryJpa = invoiceRepositoryJpa;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Client> findAll() {
        return new HashSet<>(clientRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        Optional<Client> op = clientRepository.findById(id);
        if(op.isPresent())
            return op.get();
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Client> findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Client> findByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Set<Product> findByNameLikeIgnoreCase(String criteria) {
        return productRepository.findByNameLikeIgnoreCase ("%"+ criteria + "%");
    }

    @Override
    public Set<Product> findByName(String criteria) {
        return productRepository.findByName(criteria);
    }

    @Override
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepositoryJpa.save(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Invoice findInvoiceById(Long id) {
        return invoiceRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        invoiceRepositoryJpa.delete(invoice);
    }

    @Override
    public void deleteInvoiceById(Long id) {
        invoiceRepositoryJpa.deleteById(id);
    }
}
