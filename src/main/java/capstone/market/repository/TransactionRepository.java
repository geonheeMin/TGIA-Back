package capstone.market.repository;


import capstone.market.domain.CategoryType;
import capstone.market.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

@Repository
@Transactional
@RequiredArgsConstructor
public class TransactionRepository {

    private final EntityManager em;

    /**
     * 1. 총 게시물 갯수
     */


    public Long getTotalPosts(){
        String jpql = "SELECT COUNT(p) FROM Post p";
        return em.createQuery(jpql, Long.class)
                .getSingleResult();
    }

    /**
     * 2. 총 거래 갯수
     */
    public Long getTotalTransactions(){
        String jpql = "SELECT COUNT(p) FROM Purchased p";
        return em.createQuery(jpql, Long.class)
                .getSingleResult();
    }

    /**
     * 3. 총 포스트 갯수(카테고리별)  도서, 필기구, 생활가전, 의류, 전자기기, 부기굿즈, 뷰티미용
     */
    public Long getTotalPosts_도서(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.도서)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_필기구(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.필기구)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_생활가전(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.생활가전)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_의류(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.의류)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_전자기기(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.전자기기)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_부기굿즈(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.부기굿즈)
                .getSingleResult();
        return count;

    }
    public Long getTotalPosts_뷰티미용(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.뷰티미용)
                .getSingleResult();
        return count;

    }



    /**
     * 4. 총 거래 갯수(카테고리별) 도서, 필기구, 생활가전, 의류, 전자기기, 부기굿즈, 뷰티미용
     */

    public Long getTotalTransactions_도서(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.도서)
                .getSingleResult();
         return count;

    }

    public Long getTotalTransactions_필기구(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.필기구)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_생활가전(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.생활가전)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_의류(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.의류)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_전자기기(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.전자기기)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_부기굿즈(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.부기굿즈)
                .getSingleResult();
        return count;

    }
    public Long getTotalTransactions_뷰티미용(){
        String jpql = "SELECT COUNT(p) FROM Post p WHERE p.category.category_type = :category and p.purchased is not null";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("category", CategoryType.뷰티미용)
                .getSingleResult();
        return count;

    }


    /**
     * 5.  기간별 거래량 ( 일 , 주 , 월)
     */


    public Long getTransactionCountByPeriod(String period) {
        LocalDateTime startDate;
        LocalDateTime endDate;

        switch (period) {
            case "DAILY":
                startDate = LocalDateTime.now().with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(LocalTime.MAX);
                break;
            case "WEEKLY":
                startDate = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).with(LocalTime.MAX);
                break;
            case "MONTHLY":
                startDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
                break;
            case "YEARLY":
                startDate = LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
                endDate = LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
                break;
            default:
                throw new IllegalArgumentException("Invalid period");
        }

        String jpql = "SELECT COUNT(p) FROM Purchased p WHERE p.createdDate BETWEEN :startDate AND :endDate";
        return em.createQuery(jpql, Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getSingleResult();
    }



}





