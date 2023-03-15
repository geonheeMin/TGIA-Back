package capstone.market.repository;

import capstone.market.domain.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
}
