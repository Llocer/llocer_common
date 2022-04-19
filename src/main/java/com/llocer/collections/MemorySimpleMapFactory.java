package com.llocer.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

import com.llocer.common.SimpleMap;

public class MemorySimpleMapFactory implements SimpleMapFactory {

	@Override
	public <V> SimpleMap<String, V> make( String id, Class<V> clV ) {
		return MemorySimpleMapFactory.wrap( new HashMap<String,V>() );
	}

	@Override
	public <K, V> SimpleMap<K, V> make(String id, Class<V> clV, Function<K, String> keyConverter) {
		return MemorySimpleMapFactory.wrap( new HashMap<K,V>() );
	}

	public static <K,V> SimpleMap<K, V> make( Class<K> clK, Class<V> clV ) {
		return MemorySimpleMapFactory.wrap( new HashMap<K,V>() );
	}
	
	public static <K,V> SimpleMap<K,V> wrap( Map<K,V> map ) {
		return new SimpleMap<K,V>() {
			@Override
			public Iterator<V> iterator() {
				return map.values().iterator();
			}

			@Override
			public V get(K k) {
				return map.get(k);
			}

			@Override
			public void put( K k, V v) {
				map.put( k, v );
			}

			@Override
			public void clear() {
				map.clear();
			}

			@Override
			public void remove(K k) {
				map.remove(map);
			}

		};

	}
}
