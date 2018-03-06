package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.Using;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UsingRepository extends Neo4jRepository<Using, Long> {

    //Using findTop1ByUserIdAndDeviceAndVersionAndCreateDateBetween(String userId, String device, String version, Date from, Date to);

    Using findTop1ByUserIdAndDeviceAndPlatform(String userId, String device, String platform);


}
