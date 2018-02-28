package com.dev.db.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.graph.bean.node.ScreenEngagement;
import com.dev.db.graph.bean.node.ScreenTransition;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ScreenEngagementRepository extends Neo4jRepository<ScreenEngagement, Long> {

    ScreenEngagement findTop1ByScreen(String screen);

}
