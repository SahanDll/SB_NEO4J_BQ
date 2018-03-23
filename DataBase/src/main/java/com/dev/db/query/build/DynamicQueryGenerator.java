package com.dev.db.query.build;

import com.dev.db.data.sql.fmt.bean.alert.QSettingMst;
import com.dev.db.data.sql.fmt.bean.ecos.QEcosAccountInfoPortfolio;
import com.dev.db.data.sql.fmt.bean.ecos.QEcosAccountInfoWatchlist;
import com.dev.db.data.sql.fmt.bean.smf.QSmfAccountInfoPortfolio;
import com.dev.db.data.sql.fmt.bean.smf.QSmfAccountInfoWatchlist;
import com.dev.db.query.filter.AccountInfoPortfolioFilter;
import com.dev.db.query.filter.AccountInfoWatchlistFilter;
import com.dev.db.query.filter.SettingMstFilter;

import com.querydsl.core.types.Predicate;

public class DynamicQueryGenerator {
    private static final QSettingMst SM = QSettingMst.settingMst;
    private static final QSmfAccountInfoPortfolio SAIP = QSmfAccountInfoPortfolio.smfAccountInfoPortfolio;
    private static final QEcosAccountInfoPortfolio EAIP = QEcosAccountInfoPortfolio.ecosAccountInfoPortfolio;
    private static final QSmfAccountInfoWatchlist SAIW = QSmfAccountInfoWatchlist.smfAccountInfoWatchlist;
    private static final QEcosAccountInfoWatchlist EAIW = QEcosAccountInfoWatchlist.ecosAccountInfoWatchlist;

    public static Predicate settingMstQuery(SettingMstFilter filter) {
        return new PredicateQueryBuilder(SM.isNotNull())
                .notNullAnd(SM.indexId::eq, filter.getIndexId())
                .notNullAnd(SM.indexId::gt, filter.getIndexIdGraterThan())
                .notNullOr(SM.indexId::eq, filter.getIndexIdGraterThan())
                .notNullAnd(SM.indexId::lt, filter.getIndexIdLessThan())
                .notNullOr(SM.indexId::eq, filter.getIndexIdLessThan())
                .notEmptyAnd(SM.clntNric::eq, filter.getClntNric())
                //.notEmptyAnd(SM.clntNric::containsIgnoreCase, filter.getClntNric()) //like
                .build();
    }

    public static Predicate smfAccountInfoPortfolioQuery(AccountInfoPortfolioFilter filter) {
        return new PredicateQueryBuilder(SAIP.isNotNull())
                .notNullAnd(SAIP.indexId::eq, filter.getIndexId())
                .notNullAnd(SAIP.indexId::gt, filter.getIndexIdGraterThan())
                .notNullOr(SAIP.indexId::eq, filter.getIndexIdGraterThan())
                .notNullAnd(SAIP.indexId::lt, filter.getIndexIdLessThan())
                .notNullOr(SAIP.indexId::eq, filter.getIndexIdLessThan())
                .notEmptyAnd(SAIP.clntCode::eq, filter.getClntCode())
                .notEmptyAnd(SAIP.stkCode::eq, filter.getStkCode())
                .notEmptyAnd(SAIP.stkName::eq, filter.getStkName())
                .build();
    }

    public static Predicate ecosAccountInfoPortfolioQuery(AccountInfoPortfolioFilter filter) {
        return new PredicateQueryBuilder(EAIP.isNotNull())
                .notNullAnd(EAIP.indexId::eq, filter.getIndexId())
                .notNullAnd(EAIP.indexId::gt, filter.getIndexIdGraterThan())
                .notNullOr(EAIP.indexId::eq, filter.getIndexIdGraterThan())
                .notNullAnd(EAIP.indexId::lt, filter.getIndexIdLessThan())
                .notNullOr(EAIP.indexId::eq, filter.getIndexIdLessThan())
                .notEmptyAnd(EAIP.clntCode::eq, filter.getClntCode())
                .notEmptyAnd(EAIP.stkCode::eq, filter.getStkCode())
                .notEmptyAnd(EAIP.stkName::eq, filter.getStkName())
                .build();
    }

    public static Predicate smfAccountInfoWatchlistQuery(AccountInfoWatchlistFilter filter) {
        return new PredicateQueryBuilder(SAIW.isNotNull())
                .notNullAnd(SAIW.indexId::eq, filter.getIndexId())
                .notNullAnd(SAIW.indexId::gt, filter.getIndexIdGraterThan())
                .notNullOr(SAIW.indexId::eq, filter.getIndexIdGraterThan())
                .notNullAnd(SAIW.indexId::lt, filter.getIndexIdLessThan())
                .notNullOr(SAIW.indexId::eq, filter.getIndexIdLessThan())
                .notEmptyAnd(SAIW.clntNICNo::eq, filter.getClntNric())
                .notEmptyAnd(SAIW.stkCode::eq, filter.getStkCode())
                .notEmptyAnd(SAIW.stkName::eq, filter.getStkName())
                .build();
    }

    public static Predicate ecosAccountInfoWatchlistQuery(AccountInfoWatchlistFilter filter) {
        return new PredicateQueryBuilder(EAIW.isNotNull())
                .notNullAnd(EAIW.indexId::eq, filter.getIndexId())
                .notNullAnd(EAIW.indexId::gt, filter.getIndexIdGraterThan())
                .notNullOr(EAIW.indexId::eq, filter.getIndexIdGraterThan())
                .notNullAnd(EAIW.indexId::lt, filter.getIndexIdLessThan())
                .notNullOr(EAIW.indexId::eq, filter.getIndexIdLessThan())
                .notEmptyAnd(EAIW.clntNICNo::eq, filter.getClntNric())
                .notEmptyAnd(EAIW.stkCode::eq, filter.getStkCode())
                .notEmptyAnd(EAIW.stkName::eq, filter.getStkName())
                .build();
    }
}
