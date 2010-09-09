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
package org.gxml.xpath.v10.expressions;

import javax.xml.namespace.QName;

import org.gxml.xpath.v10.ExtensionContext;
import org.gxml.xpath.v10.variants.Variant;

/**
 * packages up the context available to the XSLT engine when evaluating XPath expressions
 */
public interface ExprContextDynamic<N> 
{
	int getContextPosition() throws ExprException;

	int getContextSize() throws ExprException;

	Variant<N> getVariableValue(QName name) throws ExprException;

	ExtensionContext<N> getExtensionContext(String namespace) throws ExprException;
}
