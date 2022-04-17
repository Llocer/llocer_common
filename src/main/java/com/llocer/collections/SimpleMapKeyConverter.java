package com.llocer.collections;

import java.util.Iterator;
import java.util.function.Function;

import com.llocer.common.SimpleMap;

public class SimpleMapKeyConverter<KS,KD,V> implements SimpleMap<KS,V>{
	private final SimpleMap<KD, V> map;
	private final Function<KS, KD> convertKey;

	public SimpleMapKeyConverter( SimpleMap<KD,V> map, Function<KS,KD> convertKey ) {
		this.map = map;
		this.convertKey = convertKey;
	}
	
	@Override
	synchronized public Iterator<V> iterator() {
		return map.iterator();
	}

	@Override
	synchronized public V get(KS k) {
		return map.get( convertKey.apply(k) );
	}

	@Override
	synchronized public void put(KS k, V v) {
		map.put( convertKey.apply(k), v);
	}

	@Override
	synchronized public void clear() {
		map.clear();
	}
	
	@Override
	synchronized public void remove(KS k) {
		map.remove( convertKey.apply(k) );
	}

}
