-----------neo4j cql--------------------------
MATCH (:PopularSearch {nric: "123"})-[r:Search]->(:Client {nric: "123"})
 DELETE r
MATCH (:Client {})-[r:Search]->(:PopularSearch {}) DELETE r
MATCH (:Database {})-[r:SAYS]->(:Message {}) DELETE r
MATCH(j:Client)
 DELETE j
MATCH(j:PopularSearch)
 DELETE j
MATCH (n) detach delete n 


CREATE (n:PopularSearch { term: 'May', nric: '123' })
CREATE (n:Client { name: 'Deva', nric: '123' })

match (p1:PopularSearch)
with p1
match (p2:Client)
where p2.nric = p1.nric and p1 <> p2
merge(p1)-[r:Search]-(p2)


MATCH (a:Client), (b:PopularSearch) WHERE a.nric = "111" AND b.nric = "111" 
CREATE (a)-[r:Search]->(b) 
RETURN a,b,r;
MATCH (:Client {nric: "111"})-[r:Search]->(:PopularSearch {nric: "111"})
 DELETE r;

MATCH p=()-[r:HavingPf]->() DELETE r
MATCH(j:Portfolio) DELETE j



MATCH (n:`User`) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p


CREATE (n:AuthUser { userName: 'sahan', password: '123', role: '1', created: timestamp()})
MATCH (n:AuthUser {userName: "sahan"}) RETURN n


MATCH (:User {})-[r:View]->(:Stock {}) DELETE r



-----------neo4j admin--------------------------
#delete backup in bk folder
neo4j-admin.bat dump --to "C:\neo4j-community-3.3.2\bk"

#delete graph db folder in data folder
neo4j-admin.bat load --from "C:\neo4j-community-3.3.2\bk\graph.db.dump"


--------------------------bigquery---------------------------------------------------

SELECT user_dim.user_id, event_dim.name, event_dim.params.key, event_dim.params.value.string_value, event_dim.timestamp_micros FROM [mketrade-cefcb:com_mbb_mketrade_uat_ANDROID.app_events_20180217] 
WHERE (user_dim.user_id <> '-1' AND user_dim.user_id <> 'null') and event_dim.name= 'screen_view' and (event_dim.params.key = 'firebase_previous_screen' or event_dim.params.key = 'firebase_screen')
ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc 

SELECT user_dim.user_id, event_dim.name, event_dim.params.key, event_dim.params.value.string_value FROM [mketrade-cefcb:com_mbb_mketrade_uat_ANDROID.app_events_20180327]
WHERE event_dim.name <> 'user_engagement' 
AND event_dim.name <> 'screen_view' 
AND event_dim.name <> 'Login' 
AND event_dim.name <> 'session_start' 
AND event_dim.name <> 'first_open' 
AND event_dim.name <> 'app_remove' 
AND event_dim.name <> 'ViewMKEInsightFromViewMore'
AND event_dim.name <> 'ViewMKEInsightFromDiscover'
AND event_dim.name <> 'Trade'
AND event_dim.name <> 'TradeFromFloatingTradeButton'
AND event_dim.name <> 'TradeSearch'
AND event_dim.name <> 'ViewModelPortfolioType'
AND event_dim.name <> 'NotificationClicked'
AND event_dim.name <> 'ViewTopMoversType'
AND event_dim.name <> 'ViewNews'
AND event_dim.name <> 'ViewFullQuoteFromWatchlist'
AND event_dim.name <> 'ViewMKEInsightFromHome'
AND event_dim.name <> 'NotificationOpenedScreen'
AND event_dim.name <> 'GlobalSearch'
AND event_dim.name <> 'ViewAlertDetails'
AND event_dim.name <> 'TradeFromOrder'
AND event_dim.name <> 'TradeFromOrderList'
AND event_dim.name <> 'TradeFromMKECall'
LIMIT 1000


SELECT user_dim.user_id, event_dim.name, event_dim.params.key, event_dim.params.value.string_value, event_dim.params.value.int_value, event_dim.params.value.float_value, event_dim.params.value.double_value, event_dim.timestamp_micros
FROM [mketrade-cefcb:com_mbb_mketrade_uat_ANDROID.app_events_20180327] 
WHERE user_dim.user_id <> '-1'
AND user_dim.user_id <> 'null'
AND event_dim.name = 'Trade'
ORDER BY user_dim.user_id desc, event_dim.timestamp_micros asc
LIMIT 100


--------------------mongo db-----------------------
msiexec.exe /q /i mongodb-win32-i386-v3.2-latest-signed.msi INSTALLLOCATION="D:\MDB\" ADDLOCAL="all"

mongod.exe --storageEngine=mmapv1 --dbpath "D:\MDB\data\db"

mongod.exe --repair --dbpath "D:\MDB\data\db"

mongod.exe start


-----------------------------------------------------------