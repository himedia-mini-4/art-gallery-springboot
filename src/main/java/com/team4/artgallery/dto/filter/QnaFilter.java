package com.team4.artgallery.dto.filter;

import com.team4.artgallery.dto.filter.annotation.FilterField;
import com.team4.artgallery.entity.QnaEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QnaFilter implements IFilter {

    /**
     * 검색어
     */
    @FilterField
    private String keyword;

    /**
     * 문의글 답변 여부
     */
    @FilterField
    private Character replyyn;

    public String getKeyword() {
        return this.keyword;
    }

    public QnaFilter setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Character getReplyyn() {
        return this.replyyn;
    }

    public QnaFilter setReplyyn(Character replyyn) {
        this.replyyn = replyyn;
        return this;
    }

    public Specification<QnaEntity> toSpec() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (replyyn != null) {
                predicates.add(cb.equal(root.get("reply"), replyyn == 'Y'));
            }

            if (keyword != null && !keyword.isBlank()) {
                String pattern = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(root.get("title"), pattern),
                        cb.like(root.get("content"), pattern)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
