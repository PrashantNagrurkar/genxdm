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
package org.genxdm.processor.w3c.xs.validation;

import org.genxdm.names.NameSource;
import org.genxdm.processor.w3c.xs.validation.api.VxValidator;
import org.genxdm.processor.w3c.xs.validation.api.VxValidatorCache;
import org.genxdm.typed.types.AtomBridge;


final class ValidatorCacheImpl<N, A> implements ValidatorCache<N, A>
{
	private final VxValidatorCache<A> validation;
	private final AtomBridge<A> atomBridge;
	private final NameSource nameBridge;

	public ValidatorCacheImpl(final VxValidatorCache<A> validation, final AtomBridge<A> atomBridge, final NameSource nameBridge)
	{
		this.validation = validation;
		this.atomBridge = atomBridge;
		this.nameBridge = nameBridge;
	}

	public ContentValidator<N, A> newContentValidator()
	{
		final VxValidator<A> kernel = validation.newValidator();
		return new ContentValidatorImpl<N, A>(kernel, atomBridge, nameBridge);
	}

	public SAXContentValidator<A> newSAXContentValidator()
	{
		final VxValidator<A> kernel = validation.newValidator();
		return new SAXContentValidatorImpl<A>(kernel, nameBridge);
	}
}
