/**
 * Copyright (c) 2014-2016, Data Geekery GmbH, contact@datageekery.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jooq.lambda.fi.util.function;

import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import org.jooq.lambda.Unchecked;

/**
 * A {@link DoubleFunction} that allows for checked exceptions.
 *
 * @author Lukas Eder
 */
@FunctionalInterface
public interface CheckedDoubleFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(double value) throws Throwable;

    /**
     * @See {@link Unchecked#doubleFunction(CheckedDoubleFunction)}
     */
    static <R> DoubleFunction<R> unchecked(CheckedDoubleFunction<R> function) {
        return Unchecked.doubleFunction(function);
    }

    /**
     * @See {@link Unchecked#doubleFunction(CheckedDoubleFunction, Consumer)}
     */
    static <R> DoubleFunction<R> unchecked(CheckedDoubleFunction<R> function, Consumer<Throwable> handler) {
        return Unchecked.doubleFunction(function, handler);
    }
}
