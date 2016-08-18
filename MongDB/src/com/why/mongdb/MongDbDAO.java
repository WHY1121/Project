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
 * ����java����mongDB�����ݿ�
 * @author fsdfsdsss
 *
 */
public class MongDbDAO {
   
	
	
	public static DBCollection getConnection() throws Exception{
		// host:���� port:�˿� ����һ������
		Mongo mongo = new Mongo("localhost", 27017);
		// ��ȡ���ݿ�,�汾����Ϣ
		DB db = mongo.getDB("itheima");
		DBCollection collection = db.getCollection("c1");
		return collection;
	}
	public static void getClosed() throws Exception{
		Mongo mongo = new Mongo("localhost", 27017);
	    mongo.close();;
	}
	
	/**
	 * ���
	 * @throws Exception 
	 */
	@Test
	public void add() throws Exception{
		
	    //����
		DBObject object=new BasicDBObject();
	    object.put("name", "С����");
	    object.put("desc", "�Ǹ�����");
		getConnection().insert(object);
		//�ر�
		getClosed();
	};
	
	/**
	 * ��ѯ
	 * @throws Exception
	 */
	
	@Test
	public void testQuery() throws Exception{
	
		DBObject object=new BasicDBObject();
		object.put("name", "������");
		DBCursor cursor=getConnection().find(object);
		while(cursor.hasNext()){
			DBObject object2=cursor.next();
			System.out.println(object2);
		}
		getClosed();
		
	}
	/**
	 * ɾ��
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
		 *   ��������
		 *   
		 *   �������ݵĶ���
		 *   
		 *   ���û�з���������¼�Ƿ�����һ��
		 *   
		 *   ���������¼���ϣ��Ƿ�ȫ������
		 */
		
		
		
//		BasicDBObject query = new BasicDBObject("_id",new ObjectId("519e2e393296cf3baccdb10c"));
//		BasicDBObject object = (BasicDBObject) collection.findOne(query);
//		object.put("name", ��wangwu");
//		int n = collection.update(query, object).getN();
//		System.out.println(n);
//		mongo.close();
//
//		getConnection().update(q, o, upsert, multi);
	}
	
}
