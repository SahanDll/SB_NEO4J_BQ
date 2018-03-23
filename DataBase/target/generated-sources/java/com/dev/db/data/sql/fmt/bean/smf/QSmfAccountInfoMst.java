package com.dev.db.data.sql.fmt.bean.smf;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSmfAccountInfoMst is a Querydsl query type for SmfAccountInfoMst
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSmfAccountInfoMst extends EntityPathBase<SmfAccountInfoMst> {

    private static final long serialVersionUID = 2139290234L;

    public static final QSmfAccountInfoMst smfAccountInfoMst = new QSmfAccountInfoMst("smfAccountInfoMst");

    public final StringPath clntCode = createString("clntCode");

    public final StringPath clntNICNo = createString("clntNICNo");

    public final StringPath clntOICNo = createString("clntOICNo");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public final DateTimePath<java.util.Date> lastModified = createDateTime("lastModified", java.util.Date.class);

    public QSmfAccountInfoMst(String variable) {
        super(SmfAccountInfoMst.class, forVariable(variable));
    }

    public QSmfAccountInfoMst(Path<? extends SmfAccountInfoMst> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmfAccountInfoMst(PathMetadata metadata) {
        super(SmfAccountInfoMst.class, metadata);
    }

}

