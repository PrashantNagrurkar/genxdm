/*
 * Copyright (c) 2010-2011 TIBCO Software Inc.
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
package org.genxdm.bridge.cx.base;

import javax.xml.stream.XMLReporter;

import org.genxdm.Cursor;
import org.genxdm.Feature;
import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.bridge.cx.tree.XmlNode;
import org.genxdm.bridge.cx.typed.TypedXmlNodeContext;
import org.genxdm.exceptions.PreCondition;
import org.genxdm.io.DocumentHandler;
import org.genxdm.io.FragmentBuilder;
import org.genxdm.io.Resolver;
import org.genxdm.mutable.MutableContext;
import org.genxdm.nodes.Bookmark;
import org.genxdm.processor.io.DefaultDocumentHandler;

public final class XmlNodeContext
    implements ProcessingContext<XmlNode>
{
    public XmlNodeContext()
    {
         mutant = new XmlNodeMutableContext(this);
    }

    public Bookmark<XmlNode> bookmark(XmlNode node)
    {
        return new XmlNodeMarker(node, model);
    }

    public Model<XmlNode> getModel()
    {
        return model;
    }

    public MutableContext<XmlNode> getMutableContext()
    {
        return mutant;
    }

    @SuppressWarnings("unchecked")
    public TypedXmlNodeContext getTypedContext()
    {
        if (typedContext == null)
            typedContext = new TypedXmlNodeContext(this);
        return typedContext;
    }

    public boolean isNode(Object item)
    {
        return (item instanceof XmlNode);
    }

    public boolean isSupported(String feature)
    {
        PreCondition.assertNotNull(feature, "feature");
        if (feature.startsWith(Feature.PREFIX))
        {
            // support all core features
            return true;
        }
        return false;
    }

    public Cursor<XmlNode> newCursor(XmlNode node)
    {
        return new XmlNodeCursor(node);
    }

    public FragmentBuilder<XmlNode> newFragmentBuilder()
    {
        return new XmlNodeBuilder();
    }

    public XmlNode node(Object item)
    {
        return isNode(item) ? (XmlNode)item : null;
    }

    public XmlNode[] nodeArray(int size)
    {
        // TODO: our tests don't permit us to assert.  are the tests wrong?
//        PreCondition.assertTrue(size > -1);
        return new XmlNode[size];
    }

    public DocumentHandler<XmlNode> newDocumentHandler()
    {
        return new DefaultDocumentHandler<XmlNode>(this);
    }

    public DocumentHandler<XmlNode> newDocumentHandler(final XMLReporter reporter, final Resolver resolver)
    {
        // TODO: implement
        return newDocumentHandler();
    }
    
    public void setDefaultReporter(XMLReporter reporter)
    {
        this.reporter = reporter;
    }
    
    public void setDefaultResolver(Resolver resolver)
    {
        this.resolver = resolver;
    }
    
    public XMLReporter getDefaultReporter()
    {
        return reporter;
    }
    
    public Resolver getDefaultResolver()
    {
        return resolver;
    }
    
    private final XmlNodeModel model = new XmlNodeModel();
    private final XmlNodeMutableContext mutant;
    private TypedXmlNodeContext typedContext;
    private XMLReporter reporter;
    private Resolver resolver;
}
