package com.dev.db.data.sql.fmt.bean.ecos;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEcosAccountInfoMst is a Querydsl query type for EcosAccountInfoMst
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEcosAccountInfoMst extends EntityPathBase<EcosAccountInfoMst> {

    private static final long serialVersionUID = -1374133954L;

    public static final QEcosAccountInfoMst ecosAccountInfoMst = new QEcosAccountInfoMst("ecosAccountInfoMst");

    public final StringPath clntCode = createString("clntCode");

    public final StringPath clntNICNo = createString("clntNICNo");

    public final StringPath clntOICNo = createString("clntOICNo");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public QEcosAccountInfoMst(String variable) {
        super(EcosAccountInfoMst.class, forVariable(variable));
    }

    public QEcosAccountInfoMst(Path<? extends EcosAccountInfoMst> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEcosAccountInfoMst(PathMetadata metadata) {
        super(EcosAccountInfoMst.class, metadata);
    }

}

