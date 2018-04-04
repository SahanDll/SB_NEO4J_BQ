package com.dev.db.data.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.node.Notification;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Repository
public interface NotificationRepository extends Neo4jRepository<Notification, Long> {

    //View findTop1ByUserIdAndViewTimeAndCreateDateBetween(String userId, String viewTime, Date from, Date to);

    Notification findTop1ByType(String type);

    //List<Trade> findTop10ByOrderByTradeTime();

}
