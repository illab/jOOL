/**
 * Copyright (c) 2014, Data Geekery GmbH, contact@datageekery.com
 * All rights reserved.
 *
 * This software is licensed to you under the Apache License, Version 2.0
 * (the "License"); You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name "jOOQ" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.jooq.lambda.tuple;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jooq.lambda.function.Function1;
import org.jooq.lambda.function.Function2;

/**
 * A tuple of degree 2.
 *
 * @author Lukas Eder
 */
public final class Tuple2<T1, T2> implements Tuple, Comparable<Tuple2<T1, T2>>, Serializable, Cloneable {
    
    public final T1 v1;
    public final T2 v2;

    public Tuple2(Tuple2<T1, T2> tuple) {
        this.v1 = tuple.v1;
        this.v2 = tuple.v2;
    }

    public Tuple2(T1 v1, T2 v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
    
    /**
     * Get a tuple with the two attributes swapped.
     */
    public Tuple2<T2, T1> swap() {
        return new Tuple2<>(v2, v1);
    }
    
    /**
     * Apply this tuple as arguments to a function.
     */
    public <R> R map(Function2<T1, T2, R> function) {
        return function.apply(this);
    }
    
    /**
     * Apply attribute 1 as argument to a function and return a new tuple with the substituted argument.
     */
    public <U1> Tuple2<U1, T2> map1(Function1<T1, U1> function) {
        return Tuple.tuple(function.apply(v1), v2);
    }
    
    /**
     * Apply attribute 2 as argument to a function and return a new tuple with the substituted argument.
     */
    public <U2> Tuple2<T1, U2> map2(Function1<T2, U2> function) {
        return Tuple.tuple(v1, function.apply(v2));
    }
    
    @Override
    public Object[] array() {
        return new Object[] { v1, v2 };
    }

    @Override
    public List<?> list() {
        return Arrays.asList(array());
    }

    /**
     * The degree of this tuple: 2.
     */
    @Override
    public int degree() {
        return 2;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<Object> iterator() {
        return (Iterator<Object>) list().iterator();
    }

    @Override
    public int compareTo(Tuple2<T1, T2> other) {
        int result = 0;
        
        result = ((Comparable) v1).compareTo((Comparable) other.v1); if (result != 0) return result;
        result = ((Comparable) v2).compareTo((Comparable) other.v2); if (result != 0) return result;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;

        @SuppressWarnings({ "unchecked", "rawtypes" })
        final Tuple2<T1, T2> that = (Tuple2) o;
        
        if (v1 != that.v1) {
            if (v1 == null ^ that.v1 == null)
                return false;

            if (!v1.equals(that.v1))
                return false;
        }
        
        if (v2 != that.v2) {
            if (v2 == null ^ that.v2 == null)
                return false;

            if (!v2.equals(that.v2))
                return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
        result = prime * result + ((v2 == null) ? 0 : v2.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "("
             +        v1
             + ", " + v2
             + ")";
    }

    @Override
    public Tuple2<T1, T2> clone() {
        return new Tuple2<>(this);
    }
}
