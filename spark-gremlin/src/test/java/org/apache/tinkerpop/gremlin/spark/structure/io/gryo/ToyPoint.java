/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.tinkerpop.gremlin.spark.structure.io.gryo;

import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.InputShim;
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.KryoShim;
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.OutputShim;
import org.apache.tinkerpop.gremlin.structure.io.gryo.kryoshim.SerializerShim;

import java.io.Serializable;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public final class ToyPoint implements Serializable {

    private final int x;
    private final int y;

    public ToyPoint(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int hashCode() {
        return this.x + this.y;
    }

    public boolean equals(final Object other) {
        return other instanceof ToyPoint && ((ToyPoint) other).x == this.x && ((ToyPoint) other).y == this.y;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "]";
    }

    public static class ToyPointSerializer implements SerializerShim<ToyPoint> {
        @Override
        public <O extends OutputShim> void write(final KryoShim<?, O> kryo, final O output, final ToyPoint toyPoint) {
            output.writeInt(toyPoint.x);
            output.writeInt(toyPoint.y);
        }

        @Override
        public <I extends InputShim> ToyPoint read(final KryoShim<I, ?> kryo, final I input, final Class<ToyPoint> toyPointClass) {
            return new ToyPoint(input.readInt(), input.readInt());
        }
    }
}
