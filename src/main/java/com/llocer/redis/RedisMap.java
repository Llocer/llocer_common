package com.llocer.redis;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.llocer.common.Log;
import com.llocer.common.SimpleMap;

import io.lettuce.core.KeyValue;
import io.lettuce.core.ScanIterator;

class RedisMap< V > implements SimpleMap< String, V > {

	private class IteratorWrapper implements Iterator<V> {
		private final ScanIterator<KeyValue<String, String>> it;
		
		public IteratorWrapper() {
			this.it = ScanIterator.hscan( RedisClientConnection.getSC(), id );
		}
		
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}
		
		@Override
		public V next() {
			KeyValue<String, String> d = it.next();
			try {
				return valueReader.readValue( d.getValue() );
			} catch (IOException e) {
				Log.error(e);
				throw new IllegalArgumentException(e);
			}
		}
	}

	private final String id;
	private final ObjectWriter valueWriter;
	private final ObjectReader valueReader;
	
	RedisMap( String id, Class<V> vclass ) {
		this.id = id;
		this.valueWriter = RedisClientConnection.jsonMapper.writerFor(vclass);
		this.valueReader = RedisClientConnection.jsonMapper.readerFor(vclass);
	}
	
//	public RedisMap( String id, TypeReference<V> vclass ) {
//		this.id = id;
//		this.valueWriter = RedisClientConnection.jsonMapper.writerFor(vclass);
//		this.valueReader = RedisClientConnection.jsonMapper.readerFor(vclass);
//	}
	
	public V get( String ks ) {
		try {
			String v = RedisClientConnection.getSC().hget( id, ks );
			return ( v == null ? null : valueReader.readValue(v) );
			
		} catch (JsonProcessingException e) {
			Log.error( e );
			throw new IllegalArgumentException();
			
		} catch (IOException e) {
			Log.error( e );
			throw new IllegalArgumentException();
		}
	}
	
	public void put( String ks, V vo ) {
		try {
			String vs = valueWriter.writeValueAsString(vo);
			RedisClientConnection.getSC().hset( id, ks, vs );
			
		} catch (JsonProcessingException e) {
			Log.error( e );
			throw new IllegalArgumentException();
			
		}
	}
	
	public void clear() {
		RedisClientConnection.getSC().del( id );
	}

	@Override
	public Iterator<V> iterator() {
		return new IteratorWrapper();
	}

	@Override
	public void remove(String k) {
		RedisClientConnection.getSC().hdel( id, k );
	}
}
