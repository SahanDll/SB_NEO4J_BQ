package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.smf.SmfAccountInfoWatchlist;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SmfAccountInfoWatchlistRepository extends CrudRepository<SmfAccountInfoWatchlist, Long>, QuerydslPredicateExecutor<SmfAccountInfoWatchlist> {
}
