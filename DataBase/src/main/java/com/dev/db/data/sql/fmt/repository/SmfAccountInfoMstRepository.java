package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.smf.SmfAccountInfoMst;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SmfAccountInfoMstRepository  extends CrudRepository<SmfAccountInfoMst, Long>, QuerydslPredicateExecutor<SmfAccountInfoMst> {
}
