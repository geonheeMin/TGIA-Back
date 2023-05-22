package capstone.market.service;

import capstone.market.domain.Image;
import capstone.market.domain.Member;
import capstone.market.domain.PurchaseReview;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.repository.MemberRepository;
import capstone.market.repository.PurchaseReviewJpaRepository;
import capstone.market.repository.PurchaseReviewRepository;
import capstone.market.repository.PurchasedRepository;
import capstone.market.transaction_dto.PurchaseReviewDTO;
import capstone.market.transaction_dto.Seller_ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseReviewService {
    private final PurchaseReviewJpaRepository purchaseReviewJpaRepository;

    private final MemberRepository memberRepository;

    private final PurchaseReviewRepository purchaseReviewRepository;



    public void savePurchaseReview(PurchaseReviewDTO purchaseReviewDTO){

        PurchaseReview purchaseReview = new PurchaseReview();
        Long buyerId = purchaseReviewDTO.getBuyer_id();
        Long sellerId = purchaseReviewDTO.getSeller_id();
        Member seller = memberRepository.findOne(sellerId);
        String imageFilename = seller.getImage().getImageFilename();
        String review = purchaseReviewDTO.getReview();

        purchaseReview.setSeller_id(sellerId);
        purchaseReview.setBuyer_id(buyerId);
        purchaseReview.setBuyer_username(seller.getUsername());
        purchaseReview.setUser_type("구매자");
        purchaseReview.setReview(review);
        purchaseReview.setImageFilename(imageFilename);

        purchaseReviewJpaRepository.save(purchaseReview);

    }

    public Seller_ProfileDTO getSellerProfile(Long userId){

        Member one = memberRepository.findOne(userId);
        ProfileListDto profileListDto = new ProfileListDto(one);
        List<PurchaseReviewDTO> latestPurchaseReviews = purchaseReviewRepository.findLatestPurchaseReviews(userId);

      return new Seller_ProfileDTO(profileListDto,latestPurchaseReviews);

    }

    public List<PurchaseReviewDTO> getAllPurchaseReview(Long userId){

        return purchaseReviewRepository.getAllPurchaseReview(userId);

    }



}
