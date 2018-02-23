package com.dev.db.graph.repository.node;

/**
 * Created by N5608296 on 03/01/2018 003.
 */
import com.dev.db.graph.bean.node.AppInfo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AppInfoRepository extends Neo4jRepository<AppInfo, Long> {
    //AppInfo findTop1ByAppVersionAndUserId(String version, String userId);

    AppInfo findTop1ByAppVersion(String version);

}
