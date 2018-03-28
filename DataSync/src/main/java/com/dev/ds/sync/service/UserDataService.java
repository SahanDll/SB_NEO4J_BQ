package com.dev.ds.sync.service;

import com.dev.db.data.graph.bean.edge.HavingPf;
import com.dev.db.data.graph.bean.edge.HavingWl;
import com.dev.db.data.graph.bean.node.Portfolio;
import com.dev.db.data.graph.bean.node.User;
import com.dev.db.data.graph.bean.node.Watchlist;
import com.dev.db.data.graph.repository.edge.HavingPfRepository;
import com.dev.db.data.graph.repository.edge.HavingWlRepository;
import com.dev.db.data.graph.repository.node.PortfolioRepository;
import com.dev.db.data.graph.repository.node.UserRepository;
import com.dev.db.data.graph.repository.node.WatchlistRepository;
import com.dev.db.data.sql.fmt.bean.alert.AlertFireLog;
import com.dev.db.data.sql.fmt.bean.alert.SettingMst;
import com.dev.db.data.sql.fmt.bean.ecos.EcosAccountInfoMst;
import com.dev.db.data.sql.fmt.bean.ecos.EcosAccountInfoPortfolio;
import com.dev.db.data.sql.fmt.bean.ecos.EcosAccountInfoWatchlist;
import com.dev.db.data.sql.fmt.bean.smf.SmfAccountInfoMst;
import com.dev.db.data.sql.fmt.bean.smf.SmfAccountInfoPortfolio;
import com.dev.db.data.sql.fmt.bean.smf.SmfAccountInfoWatchlist;
import com.dev.db.data.sql.fmt.repository.*;
import com.dev.db.query.filter.AccountInfoPortfolioFilter;
import com.dev.db.query.filter.AccountInfoWatchlistFilter;
import com.dev.db.query.filter.SettingMstFilter;
import com.dev.db.query.build.DynamicQueryGenerator;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by N5608296 on 14/09/17.
 */
@Service
public class UserDataService {
    private static final Logger logger = LoggerFactory.getLogger(UserDataService.class);

    private Map<String, String> smfNricMap;
    private Map<String, String> ecosNricMap;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    WatchlistRepository watchlistRepository;
    @Autowired
    HavingPfRepository havingPfRepository;
    @Autowired
    HavingWlRepository havingWlRepository;

    @Autowired
    SettingMstRepository settingMstRepository;
    @Autowired
    AlertFiredLogRepository alertFiredLogRepository;
    @Autowired
    SmfAccountInfoMstRepository smfAccountInfoMstRepository;
    @Autowired
    SmfAccountInfoPortfolioRepository smfAccountInfoPortfolioRepository;
    @Autowired
    SmfAccountInfoWatchlistRepository smfAccountInfoWatchlistRepository;
    @Autowired
    EcosAccountInfoMstRepository ecosAccountInfoMstRepository;
    @Autowired
    EcosAccountInfoPortfolioRepository ecosAccountInfoPortfolioRepository;
    @Autowired
    EcosAccountInfoWatchlistRepository ecosAccountInfoWatchlistRepository;

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
/*            for (User us : userRepository.findAll()) {
                SettingMst sm = settingMstRepository.findTop1ByIndexId(Long.valueOf(us.getUserId()));
                if (null != sm) {
                    System.out.println(sm.toString());
                    us.setNric(sm.getClntNric());
                    userRepository.save(us);
                }
            }*/
            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

    public boolean syncSmfPortfolioData() {
        boolean status;
        String schema = "SMF";
        try {
            Gson gson = new Gson();
            smfNricMap = new HashMap<>();
            for (SmfAccountInfoMst saim : smfAccountInfoMstRepository.findAll()) {
                smfNricMap.put(saim.getClntCode(), saim.getClntNICNo());
            }

            smfNricMap.forEach((k, v) -> {
                List<Object> list = new ArrayList<>();
                AccountInfoPortfolioFilter aipf = new AccountInfoPortfolioFilter();
                aipf.setClntCode(k);
                for (SmfAccountInfoPortfolio saip : smfAccountInfoPortfolioRepository.findAll(DynamicQueryGenerator.smfAccountInfoPortfolioQuery(aipf))) {
                    //System.out.println(saip);
                    list.add(gson.toJson(saip));
                }

                if (list.size() > 0) {
                    User us = userRepository.findTop1ByNric(v);
                    if (null == us) {
                        return;
                    }
                    Portfolio pf = portfolioRepository.findTop1ByClntCodeAndClntNricAndSchema(k, v, schema);
                    if (null == pf) {
                        pf = new Portfolio();
                        pf.setClntCode(k);
                        pf.setClntNric(v);
                        pf.setSchema(schema);
                        pf.setUserId(us.getUserId());
                        pf.setPortfolio(list);
                    } else {
                        pf.setPortfolio(list);
                    }
                    pf = portfolioRepository.save(pf);
                    HavingPf hpf = havingPfRepository.findTop1ByClntCodeAndClntNricAndSchema(k, v, schema);
                    if (null == hpf) {
                        hpf = new HavingPf(us, pf);
                        hpf.setClntCode(k);
                        hpf.setClntNric(v);
                        hpf.setSchema(schema);
                        hpf.setUserId(us.getUserId());
                        hpf.setCreateDate(new Date());
                    } else {
                        hpf.setPortfolio(pf);
                    }
                    havingPfRepository.save(hpf);
                }
            });

            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

    public boolean syncSmfWatchlistData() {
        boolean status;
        String schema = "SMF";
        try {
            Gson gson = new Gson();
            for (User us : userRepository.findAll()) {
                if (StringUtils.isBlank(us.getNric())) {
                    continue;
                }
                List<Object> list = new ArrayList<>();
                AccountInfoWatchlistFilter aiwf = new AccountInfoWatchlistFilter();
                aiwf.setClntNric(us.getNric());
                for (SmfAccountInfoWatchlist saiw : smfAccountInfoWatchlistRepository.findAll(DynamicQueryGenerator.smfAccountInfoWatchlistQuery(aiwf))) {
                    //System.out.println(saip);
                    list.add(gson.toJson(saiw));
                }

                if (list.size() > 0) {
                    Watchlist wl = watchlistRepository.findTop1ByClntNricAndSchema(us.getNric(), schema);
                    if (null == wl) {
                        wl = new Watchlist();
                        wl.setClntNric(us.getNric());
                        wl.setSchema(schema);
                        wl.setUserId(us.getUserId());
                        wl.setWatchlist(list);
                    } else {
                        wl.setWatchlist(list);
                    }
                    wl = watchlistRepository.save(wl);
                    HavingWl hwl = havingWlRepository.findTop1ByClntNricAndSchema(us.getNric(), schema);
                    if (null == hwl) {
                        hwl = new HavingWl(us, wl);
                        hwl.setClntNric(us.getNric());
                        hwl.setSchema(schema);
                        hwl.setUserId(us.getUserId());
                        hwl.setCreateDate(new Date());
                    } else {
                        hwl.setWatchlist(wl);
                    }
                    havingWlRepository.save(hwl);
                }
            }

            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

    public boolean syncEcosPortfolioData() {
        boolean status;
        String schema = "ECOS";
        try {
            Gson gson = new Gson();
            ecosNricMap = new HashMap<>();
            for (EcosAccountInfoMst eaim : ecosAccountInfoMstRepository.findAll()) {
                ecosNricMap.put(eaim.getClntCode(), eaim.getClntNICNo());
            }

            ecosNricMap.forEach((k, v) -> {
                List<Object> list = new ArrayList<>();
                AccountInfoPortfolioFilter aipf = new AccountInfoPortfolioFilter();
                aipf.setClntCode(k);
                for (EcosAccountInfoPortfolio eaip : ecosAccountInfoPortfolioRepository.findAll(DynamicQueryGenerator.ecosAccountInfoPortfolioQuery(aipf))) {
                    //System.out.println(eaip);
                    list.add(gson.toJson(eaip));
                }

                if (list.size() > 0) {
                    User us = userRepository.findTop1ByNric(v);
                    if (null == us) {
                        return;
                    }
                    Portfolio pf = portfolioRepository.findTop1ByClntCodeAndClntNricAndSchema(k, v, schema);
                    if (null == pf) {
                        pf = new Portfolio();
                        pf.setClntCode(k);
                        pf.setClntNric(v);
                        pf.setSchema(schema);
                        pf.setUserId(us.getUserId());
                        pf.setPortfolio(list);
                    } else {
                        pf.setPortfolio(list);
                    }
                    pf = portfolioRepository.save(pf);
                    HavingPf hpf = havingPfRepository.findTop1ByClntCodeAndClntNricAndSchema(k, v, schema);
                    if (null == hpf) {
                        hpf = new HavingPf(us, pf);
                        hpf.setClntCode(k);
                        hpf.setClntNric(v);
                        hpf.setSchema(schema);
                        hpf.setUserId(us.getUserId());
                        hpf.setCreateDate(new Date());
                    } else {
                        hpf.setPortfolio(pf);
                    }
                    havingPfRepository.save(hpf);
                }

            });
            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

    public boolean syncEcosWatchlistData() {
        boolean status;
        String schema = "ECOS";
        try {
            Gson gson = new Gson();
            for (User us : userRepository.findAll()) {
                if (StringUtils.isBlank(us.getNric())) {
                    continue;
                }
                List<Object> list = new ArrayList<>();
                AccountInfoWatchlistFilter aiwf = new AccountInfoWatchlistFilter();
                aiwf.setClntNric(us.getNric());
                for (EcosAccountInfoWatchlist eaiw : ecosAccountInfoWatchlistRepository.findAll(DynamicQueryGenerator.ecosAccountInfoWatchlistQuery(aiwf))) {
                    //System.out.println(saip);
                    list.add(gson.toJson(eaiw));
                }

                if (list.size() > 0) {
                    Watchlist wl = watchlistRepository.findTop1ByClntNricAndSchema(us.getNric(), schema);
                    if (null == wl) {
                        wl = new Watchlist();
                        wl.setClntNric(us.getNric());
                        wl.setSchema(schema);
                        wl.setUserId(us.getUserId());
                        wl.setWatchlist(list);
                    } else {
                        wl.setWatchlist(list);
                    }
                    wl = watchlistRepository.save(wl);
                    HavingWl hwl = havingWlRepository.findTop1ByClntNricAndSchema(us.getNric(), schema);
                    if (null == hwl) {
                        hwl = new HavingWl(us, wl);
                        hwl.setClntNric(us.getNric());
                        hwl.setSchema(schema);
                        hwl.setUserId(us.getUserId());
                        hwl.setCreateDate(new Date());
                    } else {
                        hwl.setWatchlist(wl);
                    }
                    havingWlRepository.save(hwl);
                }
            }

            status = true;
        } catch (Exception e) {
            status = false;
            logger.error("Service Error : ", e);
        }

        return status;
    }

    public boolean syncAlertData() {
        boolean status;
        try {
            for (User us : userRepository.findAll()) {
                List<Long> alertList = new ArrayList<>();
                for (AlertFireLog afl : alertFiredLogRepository.findAllByClntNric(us.getNric())) {
                    alertList.add(afl.getLogId());
                }
                us.setAlerts(alertList);
                userRepository.save(us);
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

        for (SettingMst ms : settingMstRepository.findAll(DynamicQueryGenerator.settingMstQuery(smf))) {
            sMst = ms;
            //System.out.println(ms);
        }
        return sMst;
    }

}
