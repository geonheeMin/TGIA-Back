package capstone.market.repository;

import capstone.market.domain.*;
import capstone.market.profile_dto.PostDetailDto;
import capstone.market.profile_dto.SearchFilterDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@Transactional
public class PostRepositoryCustomImpl implements PostRepositoryCustom{
    
    private final JPAQueryFactory queryFactory;


    @Override
    public List<PostDetailDto> searchFilter(SearchFilterDto searchFilterDto) {
        QPost post = QPost.post;
        BooleanBuilder whereBuilder = new BooleanBuilder();

        if (searchFilterDto.getCategories() != null && !searchFilterDto.getCategories().isEmpty()) {
            BooleanExpression[] categoryExpressions = searchFilterDto.getCategories().stream()
                    .map(post.category.category_type::eq)
                    .toArray(BooleanExpression[]::new);
            whereBuilder.andAnyOf(categoryExpressions);
        }

        if (searchFilterDto.getViews() != null) {
            whereBuilder.and(post.views.goe(searchFilterDto.getViews()));
        }
        if (searchFilterDto.getLikes() != null) {
            whereBuilder.and(post.likes.goe(searchFilterDto.getLikes()));
        }
        if (searchFilterDto.getKeyword() != null && !searchFilterDto.getKeyword().isEmpty()) {
            whereBuilder.and(post.post_title.containsIgnoreCase(searchFilterDto.getKeyword())
                    .or(post.post_text.containsIgnoreCase(searchFilterDto.getKeyword())));
        }
      /*  if (searchFilterDto.getLocation().toString() != null && !searchFilterDto.getLocation().toString().isEmpty()) {
            whereBuilder.and(post.locationType.stringValue().containsIgnoreCase(searchFilterDto.getLocation().toString()));
        }
        if (searchFilterDto.getDepartment().toString() != null && !searchFilterDto.getDepartment().toString().isEmpty()) {
            whereBuilder.and(post.department.departmentType.stringValue().containsIgnoreCase(searchFilterDto.getDepartment().toString()));
        }*/
        if (searchFilterDto.getLocations() != null && !searchFilterDto.getLocations().isEmpty()) {
            BooleanExpression[] categoryExpressions2 = searchFilterDto.getLocations().stream()
                    .map(post.locationType::eq)
                    .toArray(BooleanExpression[]::new);
            whereBuilder.andAnyOf(categoryExpressions2);
        }
        if (searchFilterDto.getDepartments() != null && !searchFilterDto.getDepartments().isEmpty()) {
            BooleanExpression[] categoryExpressions3 = searchFilterDto.getDepartments().stream()
                    .map(post.department.departmentType::eq)
                    .toArray(BooleanExpression[]::new);
            whereBuilder.andAnyOf(categoryExpressions3);
        }



        Predicate where = whereBuilder.getValue();
        //BooleanExpression where = (BooleanExpression)whereBuilder.getValue();

        OrderSpecifier<?> order;
        if (searchFilterDto.getSort() != null && searchFilterDto.getSort().equalsIgnoreCase("views")) {
            order = post.views.desc();
        } else if (searchFilterDto.getSort() != null && searchFilterDto.getSort().equalsIgnoreCase("likes")) {
            order = post.likes.desc();
        } else {
            order = post.createdDate.desc();
        }
        List<Post> posts = queryFactory
                .selectFrom(post)
                .where(where)
                .orderBy(order)
                .fetch();

        List<Post> SellPosts = new ArrayList<>();
        for (Post post1 : posts) {

            if(post1.getPurchased()==null){
                SellPosts.add(post1);
            }

        }



        List<PostDetailDto> SearchPosts = SellPosts.stream().map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());

        return SearchPosts;
    }
}
