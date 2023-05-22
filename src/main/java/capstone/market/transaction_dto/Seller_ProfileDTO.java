package capstone.market.transaction_dto;

import capstone.market.profile_dto.ProfileListDto;
import lombok.Data;

import java.util.List;

@Data
public class Seller_ProfileDTO {
    private ProfileListDto profileListDto;

    private List<PurchaseReviewDTO> latestPurchaseReviews;

    public Seller_ProfileDTO(ProfileListDto profileListDto, List<PurchaseReviewDTO> latestPurchaseReviews) {
        this.profileListDto = profileListDto;
        this.latestPurchaseReviews = latestPurchaseReviews;
    }
}
