package com.llocer.collections;

import java.util.function.Function;

import com.llocer.common.Log;
import com.llocer.common.SimpleMap;

public interface SimpleMapFactory {
	<V> SimpleMap<String, V> make( String id, Class<V> clV );
	<K,V> SimpleMap<K, V> make( String id, Class<V> clV, Function<K, String> keyConverter);
	
	static SimpleMapFactory forName( String dataBackend ) {
		switch( dataBackend ) {
		case "memory":
			return new MemorySimpleMapFactory();

		default:
			try {
				return (SimpleMapFactory) Class.forName(dataBackend).getConstructor().newInstance();
			} catch (Exception  e) {
				Log.error( e, "SimpleMapFactory.getMapFactory: unable to init "+dataBackend);
				throw new IllegalArgumentException();
			}
		}
	}

}
