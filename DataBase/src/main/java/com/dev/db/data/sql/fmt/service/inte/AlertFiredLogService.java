package com.dev.db.data.sql.fmt.service.inte;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Pageable;

/**
 * Created by N5608296 on 14/09/17.
 */
public interface AlertFiredLogService {
    public JSONObject getAlertFired(String type, Pageable pageable);

    public JSONObject getAlertFiredCount();
}
