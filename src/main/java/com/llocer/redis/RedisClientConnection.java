package com.llocer.redis;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.llocer.common.Log;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisClientConnection {
	private RedisClient redisClient;
	private StatefulRedisConnection<String, String> connection;
	private RedisCommands<String, String> sc;
	
	private void open( String serverAddress ) {
		redisClient = RedisClient.create( serverAddress );
		try {
			connection = redisClient.connect();
		} catch( Exception e ) {
			Log.error( e, "unable to connect to Redis server" );
			throw e;
		}
		sc = connection.sync();
	}
	
	private void close() {
		connection.close();
		redisClient.shutdown();
	}

	/*
	 * public static connection
	 */
	
	private static RedisClientConnection redis = new RedisClientConnection(); 

	public static void connect( String serverAddress ) {
		redis.open( serverAddress );
	}
	
	public static void shutdown(  ) {
		redis.close();
	}
	
	public static RedisCommands<String, String> getSC() {
		return redis.sc;
	}
	
	/*
	 * json data mapper
	 */
	
	private static ObjectMapper initMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// determines whether generator will automatically close underlying output target that is NOT owned by the generator.
		mapper.configure( JsonGenerator.Feature.AUTO_CLOSE_TARGET, false );

		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		mapper.registerModule( javaTimeModule );
		return mapper;
	}
	
	public static final ObjectMapper jsonMapper = initMapper();
	
}
