package com.dev.db.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.graph.bean.node.GeoInfo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface GeoInfoRepository extends Neo4jRepository<GeoInfo, Long> {

    //GeoInfo findTop1ByCityAndUserId(String city, String userId);

    GeoInfo findTop1ByCity(String city);
}
