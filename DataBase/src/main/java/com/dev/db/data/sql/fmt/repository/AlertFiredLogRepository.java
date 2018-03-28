package com.dev.db.data.sql.fmt.repository;
import com.dev.db.data.sql.fmt.bean.alert.AlertFireLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by N5608296 on 13/09/17.
 */
public interface AlertFiredLogRepository extends CrudRepository<AlertFireLog, Long> {
    List<AlertFireLog> findByAlertTypeAndTriggerTimeBetweenOrderByLogIdDesc(String alertType, Date from, Date to);

    int countByAlertTypeAndTriggerTimeBetweenOrderByLogIdDesc(String alertType, Date from, Date to);

    List<AlertFireLog> findByAlertTypeAndTriggerTimeBetweenOrderByLogIdDesc(String alertType, Date from, Date to, Pageable pageable);

    List<AlertFireLog> findTop100ByAlertTypeAndTriggerTimeBetweenOrderByLogIdDesc(String alertType, Date from, Date to);

    List<AlertFireLog> findAllByTriggerTimeGreaterThanOrderByAlertTypeDesc(Date date);

    List<AlertFireLog> findAllByClntNric(String nric);

}
