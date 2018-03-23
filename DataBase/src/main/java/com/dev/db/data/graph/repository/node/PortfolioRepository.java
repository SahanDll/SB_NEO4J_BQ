package com.dev.db.data.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.node.Portfolio;
import com.dev.db.data.graph.bean.node.Watchlist;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PortfolioRepository extends Neo4jRepository<Portfolio, Long> {

    Portfolio findTop1ByClntCodeAndClntNricAndSchema(String clntCode, String clntNric, String schema);

}
