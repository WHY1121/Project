package com.why.mongdb;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * 利用java调用mongDB的数据库
 * @author fsdfsdsss
 *
 */
public class MongDbDAO {
   
	
	
	public static DBCollection getConnection() throws Exception{
		// host:主机 port:端口 建立一个连接
		Mongo mongo = new Mongo("localhost", 27017);
		// 获取数据库,版本等信息
		DB db = mongo.getDB("itheima");
		DBCollection collection = db.getCollection("c1");
		return collection;
	}
	public static void getClosed() throws Exception{
		Mongo mongo = new Mongo("localhost", 27017);
	    mongo.close();;
	}
	
	/**
	 * 添加
	 * @throws Exception 
	 */
	@Test
	public void add() throws Exception{
		
	    //插入
		DBObject object=new BasicDBObject();
	    object.put("name", "小李子");
	    object.put("desc", "是个好人");
		getConnection().insert(object);
		//关闭
		getClosed();
	};
	
	/**
	 * 查询
	 * @throws Exception
	 */
	
	@Test
	public void testQuery() throws Exception{
	
		DBObject object=new BasicDBObject();
		object.put("name", "王惠宇");
		DBCursor cursor=getConnection().find(object);
		while(cursor.hasNext()){
			DBObject object2=cursor.next();
			System.out.println(object2);
		}
		getClosed();
		
	}
	/**
	 * 删除
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception{
	
	     DBObject object=new BasicDBObject();
	     getConnection().remove(object);
		 getClosed();
		
	}
	@Test
	public void update() throws Exception{
		/**
		 *   更新条件
		 *   
		 *   更新内容的对象
		 *   
		 *   如果没有符合条件记录是否新增一条
		 *   
		 *   如过有条记录符合，是否全部更新
		 */
		
		
		
//		BasicDBObject query = new BasicDBObject("_id",new ObjectId("519e2e393296cf3baccdb10c"));
//		BasicDBObject object = (BasicDBObject) collection.findOne(query);
//		object.put("name", “wangwu");
//		int n = collection.update(query, object).getN();
//		System.out.println(n);
//		mongo.close();
//
//		getConnection().update(q, o, upsert, multi);
	}
	
}
