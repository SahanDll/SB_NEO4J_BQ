package com.dev.db.data.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.node.ScreenTransition;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ScreenTransitionRepository extends Neo4jRepository<ScreenTransition, Long> {

    //Screen findTop1ByPreviousScreenAndScreenAndUserId(String fromScreen, String toScreen, String userId);

    ScreenTransition findTop1ByPreviousScreenAndScreen(String fromScreen, String toScreen);

}
