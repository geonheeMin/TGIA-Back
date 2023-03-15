package capstone.market.repository;

import capstone.market.domain.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
}
