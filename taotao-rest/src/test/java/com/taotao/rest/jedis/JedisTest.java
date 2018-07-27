package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	/**
	 * @author: yuanj
	 * @date: 2018年6月26日 下午10:14:20
	 * @Title: testJedisCluster
	 * @Description: redis集群版测试
	 * @return void
	 * @throws
	 */
	public void testJedisCluster() {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.112", 7001));
		nodes.add(new HostAndPort("192.168.1.112", 7002));
		nodes.add(new HostAndPort("192.168.1.112", 7003));
		nodes.add(new HostAndPort("192.168.1.112", 7004));
		nodes.add(new HostAndPort("192.168.1.112", 7005));
		nodes.add(new HostAndPort("192.168.1.112", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);
	}

	/**
	 * @author: yuanj
	 * @date: 2018年6月27日 下午9:36:21
	 * @Title: testSpringJedisSingle
	 * @Description: spring集成redis单机版测试
	 * @return void
	 * @throws
	 */
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String key = jedis.get("a");
		System.out.println(key);
	}

	/**
	 * @author: yuanj
	 * @date: 2018年6月27日 下午9:36:47
	 * @Title: testSpringJedisCluster
	 * @Description: spring集成redis集群版测试
	 * @return void
	 * @throws
	 */
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		String key = jedisCluster.get("key1");
		System.out.println(key);
	}
}
