package net.hectorm.springbootdatajpa.repositories;

import net.hectorm.springbootdatajpa.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepositoryJpa extends JpaRepository<Invoice, Long> {
}
