package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.HavingWl;
import com.dev.db.data.graph.bean.edge.Login;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface HavingWlRepository extends Neo4jRepository<HavingWl, Long> {

    //Login findTop1ByUserIdAndDeviceAndLocationAndCreateDateBetween(String userId, String device, String location, Date from, Date to);

    HavingWl findTop1ByClntNricAndSchema(String clntNric, String schema);


}
