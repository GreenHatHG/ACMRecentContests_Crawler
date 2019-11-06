package team.huoguo.crawler.dao;

import team.huoguo.crawler.entity.Contest;

import java.util.List;

/**
 * @description: 对ContestInfo类数据库操作的接口
 * @author: GreenHatHG
 * @create: 2019-07-18 16:43
 **/

public interface ContestDao {

    /**
     * 批量插入数据,存在重复数据情况下只会插入重复数据中的一条
     *
     * @param list
     */
    void insertAll(List<Contest> list);

    /**
     * 向数据库中插入一个数据
     *
     * @param contest
     */
    void insertOne(Contest contest);

    /**
     * 清空数据库
     */
    void deleteAll();
}
