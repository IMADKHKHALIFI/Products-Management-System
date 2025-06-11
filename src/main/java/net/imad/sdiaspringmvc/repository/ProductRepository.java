package net.imad.sdiaspringmvc.repository;

import net.imad.sdiaspringmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
