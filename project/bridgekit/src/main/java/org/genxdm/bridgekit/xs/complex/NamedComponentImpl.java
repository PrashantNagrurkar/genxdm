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
package org.genxdm.bridgekit.xs.complex;

import javax.xml.namespace.QName;

import org.genxdm.bridgekit.xs.ForeignAttributesImpl;
import org.genxdm.bridgekit.xs.ForeignAttributesSink;
import org.genxdm.exceptions.PreCondition;
import org.genxdm.xs.components.SchemaComponent;
import org.genxdm.xs.enums.ScopeExtent;

public abstract class NamedComponentImpl 
    extends LockableImpl 
    implements SchemaComponent, ForeignAttributesSink
{

    public NamedComponentImpl(final QName name, final boolean isAnonymous, final ScopeExtent scope)
    {
        if (isAnonymous)
        {
            this.name = name;
        }
        else
        {
            this.name = PreCondition.assertArgumentNotNull(name, "name");
        }
        this.isAnonymous = isAnonymous;
        this.scope = PreCondition.assertArgumentNotNull(scope, "scope");
    }

    @Override
    public final String getLocalName()
    {
        return name.getLocalPart();
    }

    @Override
    public final QName getName()
    {
        return name;
    }

    @Override
    public final ScopeExtent getScopeExtent()
    {
        return scope;
    }

    @Override
    public final String getTargetNamespace()
    {
        return name.getNamespaceURI();
    }

    @Override
    public final boolean isAnonymous()
    {
        return isAnonymous;
    }

    @Override
    public Iterable<QName> getForeignAttributeNames()
    {
        return forAtts.getForeignAttributeNames();
    }

    @Override
    public String getForeignAttributeValue(QName name)
    {
        return forAtts.getForeignAttributeValue(name);
    }

    @Override
    public void putForeignAttribute(QName name, String value)
    {
        forAtts.putForeignAttribute(name, value);
    }

    private ForeignAttributesImpl forAtts = new ForeignAttributesImpl();
    private final boolean isAnonymous;
    private final QName name;
    private final ScopeExtent scope;
}
