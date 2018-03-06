package com.dev.db.data.h2.repository;

import com.dev.db.data.h2.bean.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by N5608296 on 13/09/17.
 */
public interface UserLoginRepository extends CrudRepository<UserLogin, String> {
    UserLogin findByUserName(String userName);

    List<UserLogin> findByUserRole(Integer userRole);

    List<UserLogin> findByUserRoleOrUserRoleGreaterThanOrderByUserRoleDesc(Integer userRoleMain, Integer userRole);

    @SuppressWarnings("JpaQlInspection")
    @Query("select ul from UserLogin ul where ul.userName = :userName")
    Stream<UserLogin> findByUserNameReturnStream(@Param("userName") String userName);
}
