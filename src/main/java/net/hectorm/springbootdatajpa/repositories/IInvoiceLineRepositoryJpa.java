package net.hectorm.springbootdatajpa.repositories;

import net.hectorm.springbootdatajpa.domain.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceLineRepositoryJpa extends JpaRepository<InvoiceLine, Long> {
}
