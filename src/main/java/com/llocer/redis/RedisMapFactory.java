package com.llocer.redis;

import java.util.function.Function;

import com.llocer.collections.SimpleMapFactory;
import com.llocer.collections.SimpleMapKeyConverter;
import com.llocer.common.SimpleMap;

public class RedisMapFactory implements SimpleMapFactory {
	@Override
	public <V> SimpleMap<String, V> make( String id, Class<V> clV ) {
		return new RedisMap<V>( id, clV ); 
	}

	@Override
	public <K, V> SimpleMap<K, V> make(String id, Class<V> clV, Function<K, String> keyConverter) {
		RedisMap<V> map = new RedisMap<V>( id, clV ); 
		return new SimpleMapKeyConverter<K,String,V>( map, keyConverter );
	}
}

