package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.Open;
import com.dev.db.data.graph.bean.edge.Trade;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Repository
public interface OpenRepository extends Neo4jRepository<Open, Long> {

    Open findTop1ByUserIdAndNotificationTypeAndOpenTime(String userId, String type, Date tradeTime);


}
