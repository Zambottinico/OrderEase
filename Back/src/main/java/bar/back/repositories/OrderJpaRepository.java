package bar.back.repositories;
import bar.back.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity getOrderByAvailableTrueAndAndIdTableAndIdLounge(Long idTable, Long idLounge);

    Optional<OrderEntity> findByAvailableTrueAndIdLoungeAndIdTable(Long idLounge, Long idTable);
}
