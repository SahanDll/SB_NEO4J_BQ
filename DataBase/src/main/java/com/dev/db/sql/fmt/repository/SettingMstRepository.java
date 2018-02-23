package com.dev.db.sql.fmt.repository;

import com.dev.db.sql.fmt.bean.SettingMst;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by N5608296 on 13/09/17.
 */
public interface SettingMstRepository extends CrudRepository<SettingMst, Long> {

    Iterable<SettingMst> findByMobileUuidNot(String mobileUuid);
    Iterable<SettingMst> findByMobileUuid(String mobileUuid);
    List<SettingMst> findByLastModifiedBetweenOrderByLastModifiedDesc(Date from, Date to);
    List<SettingMst> findByMobileUuidNotAndLastModifiedBetweenOrderByLastModifiedDesc(String mobileUuid, Date from, Date to);
    List<SettingMst> findByMobileUuidAndLastModifiedBetweenOrderByLastModifiedDesc(String mobileUuid, Date from, Date to);


}
