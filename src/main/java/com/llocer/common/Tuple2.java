package com.llocer.common;

import java.util.Objects;

public class Tuple2<T1, T2> {
	public T1 f1;
	public T2 f2;
	
	public Tuple2() {
		this.f1 = null;
		this.f2 = null;
	}
	
	public Tuple2( T1 f1, T2 f2 ) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
    @Override
    public int hashCode() {
    	return Objects.hash( f1, f2 );
    }

    @Override
    public boolean equals( Object that ) {
        if( that == this) return true;
        
        if( !(that instanceof Tuple2) ) return false;
        @SuppressWarnings("unchecked")
		Tuple2<T1,T2> tthat = (Tuple2<T1,T2>) that;

        return Objects.equals( f1, tthat.f1 ) 
        	&& Objects.equals( f2, tthat.f2 );
    }
    
    @Override
	public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append( "{" );
    	builder.append( f1 );
    	builder.append( "," );
    	builder.append( f2 );
    	builder.append( "}" );
    	return builder.toString();
    }

	public String keyString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append( f1 );
    	builder.append( "::" );
    	builder.append( f2 );
    	return builder.toString();
	}

}
