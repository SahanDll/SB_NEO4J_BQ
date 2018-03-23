package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.smf.SmfAccountInfoPortfolio;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SmfAccountInfoPortfolioRepository extends CrudRepository<SmfAccountInfoPortfolio, Long>, QuerydslPredicateExecutor<SmfAccountInfoPortfolio> {
}
