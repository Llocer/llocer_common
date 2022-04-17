package com.llocer.common;

import java.util.Objects;

public class Tuple3<T1, T2, T3> {
	public T1 f1;
	public T2 f2;
	public T3 f3;
	
	public Tuple3( T1 f1, T2 f2, T3 f3 ) {
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
	}
	
    @Override
    public int hashCode() {
    	return Objects.hash( f1, f2, f3 );
    }

    @Override
    public boolean equals( Object that ) {
        if( that == this) return true;
        
        if( !(that instanceof Tuple3) ) return false;
        @SuppressWarnings("unchecked")
		Tuple3<T1,T2, T3> tthat = (Tuple3<T1,T2,T3>) that;

        return Objects.equals( f1, tthat.f1 ) 
        	&& Objects.equals( f2, tthat.f2 )
        	&& Objects.equals( f3, tthat.f3 );
    }

}
