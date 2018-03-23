package com.dev.db.data.sql.fmt.bean.ecos;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEcosAccountInfoWatchlist is a Querydsl query type for EcosAccountInfoWatchlist
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEcosAccountInfoWatchlist extends EntityPathBase<EcosAccountInfoWatchlist> {

    private static final long serialVersionUID = -201249891L;

    public static final QEcosAccountInfoWatchlist ecosAccountInfoWatchlist = new QEcosAccountInfoWatchlist("ecosAccountInfoWatchlist");

    public final StringPath clntNICNo = createString("clntNICNo");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath stkCode = createString("stkCode");

    public final StringPath stkName = createString("stkName");

    public final NumberPath<Double> targetPrice = createNumber("targetPrice", Double.class);

    public final StringPath wlGrpId = createString("wlGrpId");

    public QEcosAccountInfoWatchlist(String variable) {
        super(EcosAccountInfoWatchlist.class, forVariable(variable));
    }

    public QEcosAccountInfoWatchlist(Path<? extends EcosAccountInfoWatchlist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEcosAccountInfoWatchlist(PathMetadata metadata) {
        super(EcosAccountInfoWatchlist.class, metadata);
    }

}

