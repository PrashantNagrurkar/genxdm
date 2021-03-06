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
package org.genxdm.io;

import org.genxdm.NodeSource;

/**
 * An instance of this interface assembles the events into an XML tree representation.
 * 
 * <p>
 * The tree built by this interface contains text nodes in string form; it does not
 * contain any atoms or type information.
 * </p>
 * 
 * <p>Note that while this interface extends the {@link ContentHandler} interface, it specifically
 * relaxes the constraint that all namespaces must be properly declared via the
 * {@link ContentHandler#namespace(String, String)} method. It will also allows namespace
 * and attribute calls in any order so that namespaces need not be declared first, and it
 * accounts for missing calls to endElement when endDocument is called.</p>
 */
public interface FragmentBuilder<N>
    extends ContentHandler, NodeSource<N>
{
    /**
     * Resets the builder by clearing the list of nodes that have been constructed in earlier executions.
     */
    void reset();

}
