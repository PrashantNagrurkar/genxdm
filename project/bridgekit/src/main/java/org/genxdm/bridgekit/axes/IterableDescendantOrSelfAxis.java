/*
 * Copyright (c) 2009-2011 TIBCO Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.genxdm.bridgekit.axes;

import java.util.Iterator;

import org.genxdm.Model;
import org.genxdm.exceptions.PreCondition;


public final class IterableDescendantOrSelfAxis<N> implements Iterable<N>
{
    final N m_origin;
    private final Model<N> m_navigator;

    public IterableDescendantOrSelfAxis(final N origin, final Model<N> navigator)
    {
        this.m_navigator = PreCondition.assertArgumentNotNull(navigator, "navigator");
        this.m_origin = PreCondition.assertArgumentNotNull(origin, "origin");
    }

    public Iterator<N> iterator()
    {
        return new IteratorDescendantOrSelfAxis<N>(m_origin, m_navigator);
    }
}
