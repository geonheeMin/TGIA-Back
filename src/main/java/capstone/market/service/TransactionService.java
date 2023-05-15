package capstone.market.service;

import capstone.market.domain.CategoryType;
import capstone.market.repository.TransactionRepository;
import capstone.market.transaction_dto.AdminStatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public AdminStatisticsDTO getTransactionCounts(){

        /**
         * 1. DTO 생성
         */
        AdminStatisticsDTO adminStatisticsDTO = new AdminStatisticsDTO();


        /**
         * 2. 총 게시물 갯수 + 유저 수
         */

        Long totalPosts = transactionRepository.getTotalPosts();
        adminStatisticsDTO.setTotalPosts(totalPosts);

        Long totalUsers = transactionRepository.getTotalUsers();
        adminStatisticsDTO.setTotalUsers(totalUsers);

        /**
         * 3. 총 거래량
         */

        Long totalTransactions = transactionRepository.getTotalTransactions();
        adminStatisticsDTO.setTotalTransactions(totalTransactions);

        /**
         * 4. 총 포스트 갯수(카테고리별)  도서, 필기구, 생활가전, 의류, 전자기기, 부기굿즈, 뷰티미용
         */

       adminStatisticsDTO.setTotalPost_도서(transactionRepository.getTotalPosts_도서());
        adminStatisticsDTO.setTotalPost_필기구(transactionRepository.getTotalPosts_필기구());
        adminStatisticsDTO.setTotalPost_생활가전(transactionRepository.getTotalPosts_생활가전());
        adminStatisticsDTO.setTotalPost_의류(transactionRepository.getTotalPosts_의류());
        adminStatisticsDTO.setTotalPost_전자기기(transactionRepository.getTotalPosts_전자기기());
        adminStatisticsDTO.setTotalPost_부기굿즈(transactionRepository.getTotalPosts_부기굿즈());
        adminStatisticsDTO.setTotalPost_뷰티미용(transactionRepository.getTotalPosts_뷰티미용());

        /**
         * 5. 총 거래 갯수(카테고리별)  도서, 필기구, 생활가전, 의류, 전자기기, 부기굿즈, 뷰티미용
         */

        adminStatisticsDTO.setTotalTransactions_도서(transactionRepository.getTotalTransactions_도서());
        adminStatisticsDTO.setTotalTransactions_필기구(transactionRepository.getTotalTransactions_필기구());
        adminStatisticsDTO.setTotalTransactions_생활가전(transactionRepository.getTotalTransactions_생활가전());
        adminStatisticsDTO.setTotalTransactions_의류(transactionRepository.getTotalTransactions_의류());
        adminStatisticsDTO.setTotalTransactions_전자기기(transactionRepository.getTotalTransactions_전자기기());
        adminStatisticsDTO.setTotalTransactions_부기굿즈(transactionRepository.getTotalTransactions_부기굿즈());
        adminStatisticsDTO.setTotalTransactions_뷰티미용(transactionRepository.getTotalTransactions_뷰티미용());

        /**
         * 6.  기간별 거래량 ( 일 , 주 , 월 , 년)
         */

        adminStatisticsDTO.setDailyTransactions(transactionRepository.getTransactionCountByPeriod("DAILY"));
        adminStatisticsDTO.setWeeklyTransactions(transactionRepository.getTransactionCountByPeriod("WEEKLY"));
        adminStatisticsDTO.setMonthlyTransactions(transactionRepository.getTransactionCountByPeriod("MONTHLY"));
        adminStatisticsDTO.setYearlyTransactions(transactionRepository.getTransactionCountByPeriod("YEARLY"));

        /**
         * 7.  각 월 별 거래량(1~12월)
         */
       adminStatisticsDTO.setMonthlyPostCountsByCategory(transactionRepository.getMonthlyPostCountsByCategory(2023));
       adminStatisticsDTO.setMonthlyTransactionCountsByCategory(transactionRepository.getMonthlyTransactionCountsByCategory(2023));

        /**
         * 9. 거래 총 금액 +  카테고리 별 거래 총 금액
         */

        adminStatisticsDTO.setTotalTransactionsPrice(transactionRepository.getTotalTransactionsPrice());
        adminStatisticsDTO.setTotalTransactionsPrice_도서(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.도서));
        adminStatisticsDTO.setTotalTransactionsPrice_필기구(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.필기구));
        adminStatisticsDTO.setTotalTransactionsPrice_생활가전(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.생활가전));
        adminStatisticsDTO.setTotalTransactionsPrice_의류(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.의류));
        adminStatisticsDTO.setTotalTransactionsPrice_전자기기(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.전자기기));
        adminStatisticsDTO.setTotalTransactionsPrice_부기굿즈(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.부기굿즈));
        adminStatisticsDTO.setTotalTransactionsPrice_뷰티미용(transactionRepository.getTotalTransactionsPriceByCategory(CategoryType.뷰티미용));



        return adminStatisticsDTO;
    }



    /**
     * 8. 월별 카테고리별 거래 횟수 조회 (테스트용) (사실상 위의 로직에서 끝남 이건 테스트 용)
     */
    public Map<String, Map<String, Long>> getMonthlyPostCountsByCategory(int year) {
        return transactionRepository.getMonthlyPostCountsByCategory(year);

    }






}
