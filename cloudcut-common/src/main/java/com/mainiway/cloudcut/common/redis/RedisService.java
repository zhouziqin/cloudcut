package com.mainiway.cloudcut.common.redis;

import com.alibaba.fastjson.TypeReference;

public interface RedisService {

	<T> void set(final String key, final T t, final int expired);

	<T> T get(final String key, final TypeReference typeReference);

	void expired(String key, int expired);

	<T> void remove(String key);

}
