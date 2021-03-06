package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.Trade;
import com.dev.db.data.graph.bean.edge.View;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface TradeRepository extends Neo4jRepository<Trade, Long> {

    //View findTop1ByUserIdAndViewTimeAndCreateDateBetween(String userId, String viewTime, Date from, Date to);

    Trade findTop1ByUserIdAndTradeTime(String userId, Date tradeTime);

    //List<Trade> findTop10ByOrderByTradeTime();

}
