package com.llocer.common;

public interface SimpleMap<K,V> extends Iterable<V> {
	V get( K k );
	void put( K k, V v );
	void remove( K k );
	void clear();
}
