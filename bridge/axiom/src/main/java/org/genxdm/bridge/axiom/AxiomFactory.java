/**
 * Copyright (c) 2009-2010 TIBCO Software Inc.
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
package org.genxdm.bridge.axiom;

import java.net.URI;

import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.genxdm.mutable.NodeFactory;

public class AxiomFactory
    implements NodeFactory<Object>
{
    
    public AxiomFactory(OMFactory delegate)
    {
        omFactory = delegate;
    }

    public Object createAttribute(String namespaceURI, String localName, String prefix, String value)
    {
        return omFactory.createOMAttribute(localName, omFactory.createOMNamespace(namespaceURI, prefix), value);
    }

    public Object createComment(String data)
    {
        return omFactory.createOMComment(null, data);
    }

    public Object createDocument(final URI uri, final String docTypeDecl)
    {
        return omFactory.createOMDocument();
    }

    public Object createElement(String namespaceURI, String localName, String prefix)
    {
        OMNamespace ns = omFactory.createOMNamespace(namespaceURI, prefix);
        return omFactory.createOMElement(localName, ns);
    }

    public Object createProcessingInstruction(String target, String data)
    {
        return omFactory.createOMProcessingInstruction(null, target, data);
    }

    public Object createText(String value)
    {
        return omFactory.createOMText(value);
    }
    
    public AxiomMutableModel getMutableModel()
    {
        return model;
    }
    
    void setMutableModel(AxiomMutableModel model)
    {
        this.model = model;
    }

    private AxiomMutableModel model;
    private final OMFactory omFactory;
}
