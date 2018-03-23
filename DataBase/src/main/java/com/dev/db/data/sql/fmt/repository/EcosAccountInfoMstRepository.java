package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.ecos.EcosAccountInfoMst;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EcosAccountInfoMstRepository extends CrudRepository<EcosAccountInfoMst, Long>, QuerydslPredicateExecutor<EcosAccountInfoMst> {
}
