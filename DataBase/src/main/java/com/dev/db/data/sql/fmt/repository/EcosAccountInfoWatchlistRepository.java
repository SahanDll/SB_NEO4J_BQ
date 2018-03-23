package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.ecos.EcosAccountInfoWatchlist;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EcosAccountInfoWatchlistRepository extends CrudRepository<EcosAccountInfoWatchlist, Long>, QuerydslPredicateExecutor<EcosAccountInfoWatchlist> {
}
