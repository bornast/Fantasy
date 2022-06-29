package hr.bornast.fantasy.infrastructure.persistence.repository;

import hr.bornast.fantasy.infrastructure.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryImpl extends JpaRepository<Product, Integer> {

}