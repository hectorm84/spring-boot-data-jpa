package net.hectorm.springbootdatajpa.repositories;

import net.hectorm.springbootdatajpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IProductRepositoryJpa extends JpaRepository<Product, Long> {

    public Set<Product> findByNameLikeIgnoreCase(String criteria);

    @Query("select P from Product P where P.name like %?1%")
    public Set<Product> findByName(String criteria);
}
