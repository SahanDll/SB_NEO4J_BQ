package com.dev.db.data.sql.fmt.bean.ecos;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEcosAccountInfoPortfolio is a Querydsl query type for EcosAccountInfoPortfolio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEcosAccountInfoPortfolio extends EntityPathBase<EcosAccountInfoPortfolio> {

    private static final long serialVersionUID = 1200470776L;

    public static final QEcosAccountInfoPortfolio ecosAccountInfoPortfolio = new QEcosAccountInfoPortfolio("ecosAccountInfoPortfolio");

    public final NumberPath<Double> avgBuyPrice = createNumber("avgBuyPrice", Double.class);

    public final StringPath clntCode = createString("clntCode");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath stkCode = createString("stkCode");

    public final StringPath stkName = createString("stkName");

    public QEcosAccountInfoPortfolio(String variable) {
        super(EcosAccountInfoPortfolio.class, forVariable(variable));
    }

    public QEcosAccountInfoPortfolio(Path<? extends EcosAccountInfoPortfolio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEcosAccountInfoPortfolio(PathMetadata metadata) {
        super(EcosAccountInfoPortfolio.class, metadata);
    }

}

