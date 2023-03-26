package capstone.market.repository;

import capstone.market.controller.PostController;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.profile_dto.ProfileListDto;
import capstone.market.profile_dto.SearchFilterDto;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostDetailDto> searchFilter(SearchFilterDto searchFilterDto);
}
