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
import org.genxdm.processor.w3c.xs.exception.sm.SmLocationException;
import org.genxdm.xs.enums.ValidationOutcome;
import org.genxdm.xs.exceptions.SimpleTypeException;
import org.genxdm.xs.resolve.LocationInSchema;

@SuppressWarnings("serial")
public abstract class CvcAttributeException extends SmLocationException
{
    private final QName m_attributeName;

    public static final String PART_DECLARATION_ABSENT = "1";
    public static final String PART_TYPE_ABSENT = "2";
    public static final String PART_NORMALIZED_VALUE = "3";
    public static final String PART_VALUE_CONSTRAINT = "4";

    public CvcAttributeException(final String partNumber, final QName attributeName, final LocationInSchema location)
    {
        super(ValidationOutcome.CVC_Attribute, partNumber, location);
        this.m_attributeName = PreCondition.assertArgumentNotNull(attributeName, "attributeName");
    }

    public CvcAttributeException(final String partNumber, final QName attributeName, final LocationInSchema location, final SimpleTypeException cause)
    {
        super(ValidationOutcome.CVC_Attribute, partNumber, location, cause);
        m_attributeName = PreCondition.assertArgumentNotNull(attributeName, "attributeName");
    }

    public final QName getAttributeName()
    {
        return m_attributeName;
    }
}
