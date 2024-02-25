package bar.back.repositories;

import bar.back.entities.Lounge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoungeJpaRepository extends JpaRepository<Lounge, Long> {
}
