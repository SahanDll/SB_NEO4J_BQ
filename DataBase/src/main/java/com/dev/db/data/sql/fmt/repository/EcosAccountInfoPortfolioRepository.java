package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.ecos.EcosAccountInfoPortfolio;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EcosAccountInfoPortfolioRepository extends CrudRepository<EcosAccountInfoPortfolio, Long>, QuerydslPredicateExecutor<EcosAccountInfoPortfolio> {
}
