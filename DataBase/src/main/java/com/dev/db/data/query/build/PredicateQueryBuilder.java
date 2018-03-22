package com.dev.db.data.query.build;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.Function;

public class PredicateQueryBuilder {

    private BooleanExpression predicate;

    public PredicateQueryBuilder(BooleanExpression predicate) {
        this.predicate = predicate;
    }

    public <T> PredicateQueryBuilder notNullAnd(Function<T, BooleanExpression> expressionFunction, T value) {
        if (value != null) {
            return new PredicateQueryBuilder(predicate.and(expressionFunction.apply(value)));
        }
        return this;
    }

    public PredicateQueryBuilder notEmptyAnd(Function<String, BooleanExpression> expressionFunction, String value) {
        if (!StringUtils.isEmpty(value)) {
            return new PredicateQueryBuilder(predicate.and(expressionFunction.apply(value)));
        }
        return this;
    }

    public PredicateQueryBuilder notNullAnd(Function<Long, BooleanExpression> expressionFunction, Long value) {
        if (value != null) {
            return new PredicateQueryBuilder(predicate.and(expressionFunction.apply(value)));
        }
        return this;
    }

    public PredicateQueryBuilder notNullOr(Function<Long, BooleanExpression> expressionFunction, Long value) {
        if (value != null) {
            return new PredicateQueryBuilder(predicate.or(expressionFunction.apply(value)));
        }
        return this;
    }

    public <T> PredicateQueryBuilder notEmptyAnd(Function<Collection<T>, BooleanExpression> expressionFunction, Collection<T> collection) {
        if (!CollectionUtils.isEmpty(collection)) {
            return new PredicateQueryBuilder(predicate.and(expressionFunction.apply(collection)));
        }
        return this;
    }


    public BooleanExpression build() {
        return predicate;
    }
}