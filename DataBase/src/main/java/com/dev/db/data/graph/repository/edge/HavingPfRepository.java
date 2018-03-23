package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.HavingPf;
import com.dev.db.data.graph.bean.edge.HavingWl;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface HavingPfRepository extends Neo4jRepository<HavingPf, Long> {

    HavingPf findTop1ByClntCodeAndClntNricAndSchema(String clntCode, String clntNric, String schema);


}
