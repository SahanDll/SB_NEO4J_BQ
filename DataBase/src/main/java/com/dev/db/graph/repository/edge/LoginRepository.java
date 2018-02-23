package com.dev.db.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.graph.bean.edge.Login;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface LoginRepository extends Neo4jRepository<Login, Long> {

    //Login findTop1ByUserIdAndDeviceAndLocationAndCreateDateBetween(String userId, String device, String location, Date from, Date to);

    Login findTop1ByUserIdAndLocationAndPlatform(String userId, String location, String platform);


}
