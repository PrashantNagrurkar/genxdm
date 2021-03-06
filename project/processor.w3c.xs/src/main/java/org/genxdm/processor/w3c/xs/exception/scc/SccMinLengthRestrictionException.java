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
package org.genxdm.processor.w3c.xs.exception.scc;

import org.genxdm.xs.enums.ValidationOutcome;
import org.genxdm.xs.exceptions.ComponentConstraintException;

/**
 * Date: Mar 11, 2008
 */
@SuppressWarnings("serial")
final public class SccMinLengthRestrictionException extends ComponentConstraintException
{
    private final int m_minLength;
    private final int m_restrictedMinLength;

    public SccMinLengthRestrictionException(final int minLength, final int restrictedMinLength)
    {
        super(ValidationOutcome.SCC_FractionDigitsValidRestriction, "4.3.2.4");
        m_minLength = minLength;
        m_restrictedMinLength = restrictedMinLength;
    }
    @Override
    public String getMessage()
    {
        return "The {value}, " + m_minLength + ", of minLength must be greater than or equal to the {value}, " + m_restrictedMinLength + ", of its inherited minLength.";
    }
}
