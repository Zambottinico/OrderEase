package bar.back.repositories;

import bar.back.entities.Lounge;
import bar.back.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}
