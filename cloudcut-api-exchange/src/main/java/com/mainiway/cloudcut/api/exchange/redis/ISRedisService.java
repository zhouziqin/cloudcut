package com.mainiway.cloudcut.api.exchange.redis;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.TypeReference;
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public interface ISRedisService {

	<T> void set(final String key, final T t, final int expired);

	<T> T get(final String key, final TypeReference typeReference);

	void expired(String key, int expired);

	<T> void remove(String key);
	
	<T> void set(String key,T value);
	
	<T> T get( String key,Class clz);

}
