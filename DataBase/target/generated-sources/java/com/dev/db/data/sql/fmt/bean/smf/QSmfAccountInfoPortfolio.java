package com.dev.db.data.sql.fmt.bean.smf;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSmfAccountInfoPortfolio is a Querydsl query type for SmfAccountInfoPortfolio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSmfAccountInfoPortfolio extends EntityPathBase<SmfAccountInfoPortfolio> {

    private static final long serialVersionUID = -522750156L;

    public static final QSmfAccountInfoPortfolio smfAccountInfoPortfolio = new QSmfAccountInfoPortfolio("smfAccountInfoPortfolio");

    public final NumberPath<Double> avgBuyPrice = createNumber("avgBuyPrice", Double.class);

    public final StringPath clntCode = createString("clntCode");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath stkCode = createString("stkCode");

    public final StringPath stkName = createString("stkName");

    public QSmfAccountInfoPortfolio(String variable) {
        super(SmfAccountInfoPortfolio.class, forVariable(variable));
    }

    public QSmfAccountInfoPortfolio(Path<? extends SmfAccountInfoPortfolio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmfAccountInfoPortfolio(PathMetadata metadata) {
        super(SmfAccountInfoPortfolio.class, metadata);
    }

}

