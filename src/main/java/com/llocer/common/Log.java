package com.llocer.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	private static final boolean debugToStdout = true;
	private static final Logger logger = Logger.getLogger( Log.class.getName() );
	private static final int maxChars = 1000;
	
	public static boolean check( boolean v ) throws Exception {
		if( !v) throw new Exception( "check failed" );
		return v;
	}

	@SuppressWarnings("unused")
	private static void log ( boolean allChars, Level level, Throwable thrpwable, String format, Object... args ) {
		if( !debugToStdout && ( logger == null || !logger.isLoggable( level ) ) ) return;

		String msg = null;
		if( format != null ) {
			msg = String.format( format, args );
			int l = msg.length();
			if( l >= maxChars && !allChars ) {
				msg=msg.substring(0,900)+"[...]"+msg.substring( l-100 );
			}
		}

		if( logger != null ) {
			if( thrpwable == null ) {
				logger.log( level, msg );
			
			} else if( msg == null ) {
				logger.log( level, "{0}", thrpwable );
			
			} else { 
				logger.log( level, msg, thrpwable );
			
			}
		}

		if( debugToStdout ) {
			if( msg != null ) System.out.println( msg );
			
			if( thrpwable != null ) {
				thrpwable.printStackTrace();
			}
		}
	}
	
	public static void debug ( String format, Object... args ) {
		log( false, Level.FINE, null, format, args );
	}
	
	public static void dump ( String format, Object... args ) {
		log( true, Level.FINE, null, format, args );
	}
	
	public static void debug ( Throwable e, String s ) {
		log( false, Level.FINE, e, "%s", s );
	}
	
	public static void error ( String format, Object... args ) {
		log( false, Level.SEVERE, null, format, args );
	}
	
	public static void error ( Throwable e, String s ) {
		log( false, Level.SEVERE, e, "%s", s );
	}
	
	public static void error ( Throwable e ) {
		log( false, Level.SEVERE, e, null );
	}

	public static void warning( Throwable e ) {
		log( false, Level.WARNING, e, null );
	}

	public static void warning( String format, Object... args ) {
		log( false, Level.WARNING, null, format, args );
	}
	
	public static void warning(Exception e, String format, Object... args ) {
		log( false, Level.WARNING, e, format, args );
	}

	public static void info ( String format, Object... args ) {
		log( false, Level.INFO, null, format, args );
	}

}
