package com.dev.db.data.sql.bo.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClientMst is a Querydsl query type for ClientMst
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClientMst extends EntityPathBase<ClientMst> {

    private static final long serialVersionUID = -2017896084L;

    public static final QClientMst clientMst = new QClientMst("clientMst");

    public final StringPath clntName = createString("clntName");

    public final StringPath clntNric = createString("clntNric");

    public final NumberPath<Long> indexId = createNumber("indexId", Long.class);

    public QClientMst(String variable) {
        super(ClientMst.class, forVariable(variable));
    }

    public QClientMst(Path<? extends ClientMst> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClientMst(PathMetadata metadata) {
        super(ClientMst.class, metadata);
    }

}

