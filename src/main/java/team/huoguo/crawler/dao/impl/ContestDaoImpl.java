package team.huoguo.crawler.dao.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertManyOptions;
import team.huoguo.crawler.common.MongoDBJDBC;
import team.huoguo.crawler.dao.ContestDao;
import team.huoguo.crawler.entity.Contest;

import java.util.List;

/**
 * @description:
 * @author: GreenHatHG
 * @create: 2019-07-18 16:47
 **/

public class ContestDaoImpl implements ContestDao {

    private MongoCollection<Contest> mongoCollection = null;

    public void setMongoCollection(){
        mongoCollection = MongoDBJDBC.mongoDatabase.getCollection("contest", Contest.class);
    }

    /**
     * 该方法在服务器不能去重插入
     * @param list
     */
    public void insertAll(List<Contest> list) {
        setMongoCollection();
        if(!list.isEmpty()){
            mongoCollection.insertMany(list, new InsertManyOptions().ordered(false));
        }
    }

    public void insertOne(Contest contest) {
        mongoCollection.insertOne(contest);
    }
}
