package capstone.market.controller;


import capstone.market.domain.SearchKeyword;
import capstone.market.searchKeyword_dto.SearchKeywordDTO;
import capstone.market.service.PostService;
import capstone.market.service.SearchKeywordService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SearchKeywordController {


  private final SearchKeywordService searchKeywordService;


  @GetMapping("/getTop10keywords")
    public List<SearchKeywordDTO> findTop10ByOrderBySearchCountDesc(){

       return searchKeywordService.findTop10ByOrderBySearchCountDesc();

    };





}
