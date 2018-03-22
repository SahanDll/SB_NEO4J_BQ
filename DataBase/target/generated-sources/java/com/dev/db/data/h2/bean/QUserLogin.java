package com.dev.db.data.h2.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLogin is a Querydsl query type for UserLogin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLogin extends EntityPathBase<UserLogin> {

    private static final long serialVersionUID = 2033148170L;

    public static final QUserLogin userLogin = new QUserLogin("userLogin");

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final StringPath password = createString("password");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath userName = createString("userName");

    public final NumberPath<Integer> userRole = createNumber("userRole", Integer.class);

    public QUserLogin(String variable) {
        super(UserLogin.class, forVariable(variable));
    }

    public QUserLogin(Path<? extends UserLogin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLogin(PathMetadata metadata) {
        super(UserLogin.class, metadata);
    }

}

