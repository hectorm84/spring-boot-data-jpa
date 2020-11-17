package net.hectorm.springbootdatajpa.service;

import net.hectorm.springbootdatajpa.domain.Client;
import net.hectorm.springbootdatajpa.domain.Invoice;
import net.hectorm.springbootdatajpa.domain.Product;
import net.hectorm.springbootdatajpa.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("repositoryJpa")
public class ClientServiceImpl implements IClientService{

    private final IClientRepository iClientRepository;

    @Autowired
    public ClientServiceImpl(IClientRepository iClientRepository) {
        this.iClientRepository = iClientRepository;
    }

    @Override
    public Set<Client> findAll() {
        return iClientRepository.findAll();
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return iClientRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Client> findByLastName(String lastName) {
        return iClientRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Client> findByFirstName(String firstName) {
        return iClientRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return iClientRepository.save(client);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        iClientRepository.delete(client);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iClientRepository.deleteById(id);
    }

    @Override
    public Set<Product> findByNameLikeIgnoreCase(String criteria) {
        return null;
    }

    @Override
    public Set<Product> findByName(String criteria) {
        return null;
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Product findProductById(Long id) {
        return null;
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        return null;
    }

    @Override
    public void deleteInvoice(Invoice invoice) {

    }

    @Override
    public void deleteInvoiceById(Long id) {

    }
}
