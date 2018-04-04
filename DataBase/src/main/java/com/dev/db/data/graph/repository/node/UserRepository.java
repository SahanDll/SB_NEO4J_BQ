package com.dev.db.data.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.node.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    User findTop1ByUserId(String userId);

    User findTop1ByNric(String nric);

    @Query("MATCH (n:User) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p")
    List<User> findAll();
}
