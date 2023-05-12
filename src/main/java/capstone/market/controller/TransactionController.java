package capstone.market.controller;

import capstone.market.service.TransactionService;
import capstone.market.transaction_dto.AdminStatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;


    @GetMapping("/transaction")
    public AdminStatisticsDTO getTransactionCounts(){
        return transactionService.getTransactionCounts();
    }



    @GetMapping("/transaction2")
    public Map<String, Map<String, Long>> getMonthlyTransactionCountsByCategory(){
        return transactionService.getMonthlyPostCountsByCategory(2023);
    }
}
