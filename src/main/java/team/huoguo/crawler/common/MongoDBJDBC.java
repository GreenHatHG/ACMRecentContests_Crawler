package team.huoguo.crawler.common;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
/**
 * @description: 连接MongoDB
 * @author: GreenHatHG
 * @create: 2019-07-16 21:50
 **/
public class MongoDBJDBC {

    private static MongoClient mongoClient;
    public static MongoDatabase mongoDatabase = null;

    /**
     * 访问名称为database的数据库
     * @param database
     */
    public static void connect(String database){

        //连接本地数据库
        mongoClient = MongoClients.create();
        //为POJO创建Codec Registry
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        //获取数据库的句柄
        mongoDatabase = mongoClient.getDatabase(database).withCodecRegistry(pojoCodecRegistry);
    }

    public static  void close(){
        mongoClient.close();
        mongoClient = null;
        mongoDatabase = null;
    }

}
