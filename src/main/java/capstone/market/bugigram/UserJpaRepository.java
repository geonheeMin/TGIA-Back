package capstone.market.bugigram;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String email, String password);
}
