package com.dev.db.data.sql.fmt.repository;

import com.dev.db.data.sql.fmt.bean.alert.SettingMst;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by N5608296 on 13/09/17.
 */
public interface SettingMstRepository extends CrudRepository<SettingMst, Long>  , QuerydslPredicateExecutor<SettingMst> {

    SettingMst findTop1ByIndexId(Long indexId);
    Iterable<SettingMst> findByMobileUuidNot(String mobileUuid);
    Iterable<SettingMst> findByMobileUuid(String mobileUuid);
    List<SettingMst> findByLastModifiedBetweenOrderByLastModifiedDesc(Date from, Date to);
    List<SettingMst> findByMobileUuidNotAndLastModifiedBetweenOrderByLastModifiedDesc(String mobileUuid, Date from, Date to);
    List<SettingMst> findByMobileUuidAndLastModifiedBetweenOrderByLastModifiedDesc(String mobileUuid, Date from, Date to);

}
