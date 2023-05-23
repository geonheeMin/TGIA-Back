package capstone.market.service;

import capstone.market.domain.Manner;
import capstone.market.domain.Member;
import capstone.market.manner_dto.MannerCheckListDTO;
import capstone.market.repository.MannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MannerService {
    private final MemberService memberService;
    private final MannerRepository mannerRepository;
    public void setMannerScore(MannerCheckListDTO mannerData) {
        Long sellerId = mannerData.getSellerId();
        Member member = memberService.findMemberByPK(sellerId);
        Integer checkedCnt = 0;
        Manner manner = member.getManner();
        if (manner == null) {
            manner = new Manner();
            if (mannerData.getGoodPrice()) {
                checkedCnt += 1;
                manner.setGoodPrice(1L);
            }

            if (mannerData.getGoodTime()) {
                checkedCnt += 1;
                manner.setGoodTime(1L);
            }

            if (mannerData.getFastResponse()) {
                checkedCnt += 1;
                manner.setFastResponse(1L);
            }

            if (mannerData.getBadQuality()) {
                checkedCnt -= 1;
                manner.setBadQuality(1L);
            }

            if (mannerData.getNoResponse()) {
                checkedCnt -= 1;
                manner.setNoResponse(1L);
            }
        } else {
            Long cnt = 0L;
            if (mannerData.getGoodPrice()) {
                cnt = manner.getGoodPrice() + 1;
                checkedCnt += 1;
                manner.setGoodPrice(cnt);
            }

            if (mannerData.getGoodTime()) {
                cnt = manner.getGoodTime() + 1;
                checkedCnt += 1;
                manner.setGoodTime(cnt);
            }

            if (mannerData.getFastResponse()) {
                cnt = manner.getFastResponse() + 1;
                checkedCnt += 1;
                manner.setFastResponse(cnt);
            }

            if (mannerData.getNoResponse()) {
                cnt = manner.getNoResponse() + 1;
                checkedCnt -= 1;
                manner.setNoResponse(cnt);
            }

            if (mannerData.getBadQuality()) {
                cnt = manner.getBadQuality() + 1;
                checkedCnt -= 1;
                manner.setBadQuality(cnt);
            }
        }
        System.out.println("fadfasfds " + checkedCnt);
        Integer totalMannerScore = checkedCnt * 15;
        member.setMannerscore(mannerData.getMannerScore() + totalMannerScore + member.getMannerscore());
        mannerRepository.save(manner);
        member.setManner(manner);
    }
    public List<Map<String, Long>> topThree(Long userId) {
        Member member = memberService.findMemberByPK(userId);
        Manner manner = member.getManner();

        // Assuming the coefficients are stored in a map with labels
        Map<String, Long> coefficientMap = new HashMap<>();
        coefficientMap.put("좋은 상품을 저렴하게 판매해요", manner.getGoodPrice());
        coefficientMap.put("시간 약속을 잘 지켜요", manner.getGoodTime());
        coefficientMap.put("응답 속도가 빨라요", manner.getFastResponse());
        coefficientMap.put("상품에 하자가 있어요", manner.getBadQuality());
        coefficientMap.put("연락이 잘 안 돼요", manner.getNoResponse());

        // Sort the coefficients in descending order
        List<Map.Entry<String, Long>> sortedCoefficients = new ArrayList<>(coefficientMap.entrySet());
        Collections.sort(sortedCoefficients, (e1, e2) -> Long.compare(e2.getValue(), e1.getValue()));

// Get the top 3 coefficients with labels
        List<Map.Entry<String, Long>> top3Coefficients = sortedCoefficients.subList(0, 3);

// Output the top 3 coefficients with labels
        for (Map.Entry<String, Long> entry : top3Coefficients) {
            String label = entry.getKey();
            Long coefficient = entry.getValue();
            System.out.println(label + ": " + coefficient);
        }

        List<Map<String, Long>> mapList = new ArrayList<>();

        for (Map.Entry<String, Long> entry : top3Coefficients) {
            Map<String, Long> map = new HashMap<>();
            map.put(entry.getKey(), entry.getValue());
            mapList.add(map);
        }

        return mapList;
    }
}
