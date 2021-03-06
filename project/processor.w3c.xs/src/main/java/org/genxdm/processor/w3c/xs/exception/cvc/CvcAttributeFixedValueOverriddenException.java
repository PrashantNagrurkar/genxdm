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
package org.genxdm.processor.w3c.xs.exception.cvc;

import javax.xml.namespace.QName;

import org.genxdm.exceptions.PreCondition;
import org.genxdm.xs.resolve.LocationInSchema;

@SuppressWarnings("serial")
public final class CvcAttributeFixedValueOverriddenException extends CvcAttributeException
{
    private final String m_expectValue;
    private final String m_actualValue;

    public CvcAttributeFixedValueOverriddenException(final QName attributeName, final String expectValue, final String actualValue, final LocationInSchema location)
    {
        super(PART_VALUE_CONSTRAINT, attributeName, location);
        m_expectValue = PreCondition.assertArgumentNotNull(expectValue, "expectValue");
        m_actualValue = PreCondition.assertArgumentNotNull(actualValue, "actualValue");
    }

    @Override
    public String getMessage()
    {
        return "Fixed value, '" + m_expectValue + "', has been overridden with '" + m_actualValue + "' for attribute '" + getAttributeName() + "'.";
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj instanceof CvcAttributeFixedValueOverriddenException)
        {
            final CvcAttributeFixedValueOverriddenException e = (CvcAttributeFixedValueOverriddenException)obj;
            return e.getAttributeName().equals(getAttributeName()) && e.m_expectValue.equals(m_expectValue) && e.m_actualValue.equals(m_actualValue);
        }
        else
        {
            return false;
        }
    }
}
