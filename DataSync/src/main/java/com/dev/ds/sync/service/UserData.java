package com.dev.ds.sync.service;

import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.sql.fmt.bean.SettingMst;
import com.dev.db.query.filter.SettingMstFilter;
import com.dev.db.query.build.DynamicQueryGenerator;
import com.dev.db.data.sql.fmt.repository.SettingMstRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class UserData {
    private static final Logger logger = LoggerFactory.getLogger(UserData.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    SettingMstRepository settingMstRepository;

    public boolean syncSettingMasterData() {
        boolean status;
        try {
            for (SettingMst sm : settingMstRepository.findAll()) {
                User us = userRepository.findTop1ByUserId(String.valueOf(sm.getIndexId()));
                if (null != us) {
                    us.setNric(sm.getClntNric());
                    userRepository.save(us);
                }
            }
            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

    public SettingMst getSettingMasterByIdAndNric(Long id, String nric) {
        SettingMst sMst = null;
        SettingMstFilter smf = new SettingMstFilter();
        smf.setIndexId(id);
        smf.setClntNric(nric);
        //smf.setIndexIdLessThan((long) 5);
        //smf.setIndexIdGraterThan((long) 2);

        for(SettingMst ms: settingMstRepository.findAll(DynamicQueryGenerator.settingMstQuery(smf))){
            sMst = ms;
            //System.out.println(ms);
        }
        return sMst;
    }

}
