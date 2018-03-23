package com.dev.db.data.sql.fmt.bean.alert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSettingMst is a Querydsl query type for SettingMst
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettingMst extends EntityPathBase<SettingMst> {

    private static final long serialVersionUID = 1278197683L;

    public static final QSettingMst settingMst = new QSettingMst("settingMst");

    public final NumberPath<Integer> caAnnouncementEnabled = createNumber("caAnnouncementEnabled", Integer.class);

    public final StringPath clntNric = createString("clntNric");

    public final StringPath gcmToken = createString("gcmToken");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public final NumberPath<Integer> marketingEnabled = createNumber("marketingEnabled", Integer.class);

    public final NumberPath<Integer> mkeInsightsEnabled = createNumber("mkeInsightsEnabled", Integer.class);

    public final StringPath mobileOs = createString("mobileOs");

    public final StringPath mobileUuid = createString("mobileUuid");

    public final NumberPath<Integer> newsEnabled = createNumber("newsEnabled", Integer.class);

    public final NumberPath<Integer> paymentEnabled = createNumber("paymentEnabled", Integer.class);

    public final NumberPath<Integer> portfolioEnabled = createNumber("portfolioEnabled", Integer.class);

    public final NumberPath<Integer> priceEnabled = createNumber("priceEnabled", Integer.class);

    public final NumberPath<Integer> systemEnabled = createNumber("systemEnabled", Integer.class);

    public QSettingMst(String variable) {
        super(SettingMst.class, forVariable(variable));
    }

    public QSettingMst(Path<? extends SettingMst> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSettingMst(PathMetadata metadata) {
        super(SettingMst.class, metadata);
    }

}

