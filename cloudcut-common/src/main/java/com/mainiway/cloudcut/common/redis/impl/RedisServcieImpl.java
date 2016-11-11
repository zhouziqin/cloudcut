package com.mainiway.cloudcut.common.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mainiway.cloudcut.common.redis.RedisService;

@Service("redisCacheService")
public class RedisServcieImpl implements RedisService {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * ====================================================================
	 *函 数 名： @param key 储存redis的key
	 *函 数 名： @param t  储存redis的value
	 *函 数 名： @param expired 过期时间
	 *功 能：      将数据储存到redis中
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年2月24日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public <T> void set(final String key, final T t, final int expired) {
		if (null == key || 0 == key.length() || null == t) {
			return;
		}
		try {
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					connection.setEx(key.getBytes(), expired, JSON.toJSONString(t).getBytes());
					return null;
				}
			});
		} catch (Exception e) {
			System.out.println(e + "储存用户失败");
		}
	}

	/**
	 * ====================================================================
	 *函 数 名： @param key
	 *函 数 名： @param typeReference
	 *函 数 名： @return
	 *功 能：    通过key值获取value（存储的用户信息）
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年2月24日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public <T> T get(final String key, final TypeReference typeReference) {
		try {
			return (T) redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] val = connection.get(key.getBytes());
					if (null == val || 0 == val.length) {
						return null;
					}
					T result = (T) JSON.parseObject(new String(val), typeReference);
					return result;
				}
			}) ;
		} catch (Exception e) {
			System.out.println(e + "获取失败");
		}
		return null;
	}

	/**
	 * ====================================================================
	 *函 数 名： @param key
	 *函 数 名： @param expired
	 *功 能： 重置过期时间
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年2月24日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public void expired(final String key, final int expired) {
		try {
			// 获取序列化转换器
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					if (!StringUtils.isEmpty(key)) {
						connection.expire(key.getBytes(), expired);
					}
					return null;
				}
			});
		} catch (Exception e) {
			System.out.println(e + "重置时间失败");

		}

	}

	/**
	 * ====================================================================
	 *函 数 名： @param key
	 *功 能：    根据key删除redis中的信息
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年2月24日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public <T> void remove(final String key) {
		try {
			final byte[] keys = key.getBytes();
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection) throws DataAccessException {
					connection.del(keys);
					return null;
				}
			});
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
