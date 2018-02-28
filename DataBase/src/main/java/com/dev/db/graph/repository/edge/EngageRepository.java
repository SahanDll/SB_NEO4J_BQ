package com.dev.db.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.graph.bean.edge.Engage;
import com.dev.db.graph.bean.edge.View;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EngageRepository extends Neo4jRepository<Engage, Long> {

    Engage findTop1ByUserIdAndScreenNameAndPlatform(String userId, String scr, String platform);

}
