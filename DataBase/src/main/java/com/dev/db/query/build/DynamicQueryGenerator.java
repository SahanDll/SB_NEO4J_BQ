package com.dev.db.query.build;

import com.dev.db.query.filter.SettingMstFilter;
import com.dev.db.data.sql.fmt.bean.QSettingMst;
import com.querydsl.core.types.Predicate;

public class DynamicQueryGenerator {
    private static final QSettingMst SM = QSettingMst.settingMst;

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
}
