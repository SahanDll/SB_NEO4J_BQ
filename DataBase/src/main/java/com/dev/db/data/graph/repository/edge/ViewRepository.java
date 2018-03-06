package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.View;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ViewRepository extends Neo4jRepository<View, Long> {

    //View findTop1ByUserIdAndViewTimeAndCreateDateBetween(String userId, String viewTime, Date from, Date to);

    View findTop1ByUserIdAndFromScreenNameAndToScreenNameAndPlatform(String userId, String fromScr, String toScr, String platform);

}
