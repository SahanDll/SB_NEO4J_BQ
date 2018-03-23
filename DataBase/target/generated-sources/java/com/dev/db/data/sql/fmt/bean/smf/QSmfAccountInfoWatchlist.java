package com.dev.db.data.sql.fmt.bean.smf;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSmfAccountInfoWatchlist is a Querydsl query type for SmfAccountInfoWatchlist
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSmfAccountInfoWatchlist extends EntityPathBase<SmfAccountInfoWatchlist> {

    private static final long serialVersionUID = -1924470823L;

    public static final QSmfAccountInfoWatchlist smfAccountInfoWatchlist = new QSmfAccountInfoWatchlist("smfAccountInfoWatchlist");

    public final StringPath clntNICNo = createString("clntNICNo");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath stkCode = createString("stkCode");

    public final StringPath stkName = createString("stkName");

    public final NumberPath<Double> targetPrice = createNumber("targetPrice", Double.class);

    public final StringPath wlGrpId = createString("wlGrpId");

    public QSmfAccountInfoWatchlist(String variable) {
        super(SmfAccountInfoWatchlist.class, forVariable(variable));
    }

    public QSmfAccountInfoWatchlist(Path<? extends SmfAccountInfoWatchlist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmfAccountInfoWatchlist(PathMetadata metadata) {
        super(SmfAccountInfoWatchlist.class, metadata);
    }

}

