package capstone.market.repository;

import capstone.market.domain.Post;
import capstone.market.domain.QPost;
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

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
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


        List<PostDetailDto> SearchPosts = posts.stream().map(p -> new PostDetailDto(p))
                .collect(Collectors.toList());

        return SearchPosts;
    }
}
