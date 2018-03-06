package com.dev.db.data.graph.repository.edge;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.data.graph.bean.edge.Downloaded;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DownloadedRepository extends Neo4jRepository<Downloaded, Long> {

    Downloaded findTop1ByUserIdAndVersionAndPlatform(String userId, String version, String platform);


}
