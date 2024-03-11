package bar.back.repositories;

import bar.back.entities.DetailOrder;
import bar.back.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailJpaRepository  extends JpaRepository<DetailOrder, Long> {
}
