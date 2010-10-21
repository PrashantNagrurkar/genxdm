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
package org.genxdm.bridgekit.xs;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.genxdm.names.NameSource;
import org.genxdm.typed.types.AtomBridge;
import org.genxdm.xs.components.EnumerationDefinition;
import org.genxdm.xs.enums.DerivationMethod;
import org.genxdm.xs.enums.ScopeExtent;
import org.genxdm.xs.enums.WhiteSpacePolicy;
import org.genxdm.xs.facets.Facet;
import org.genxdm.xs.facets.FacetKind;
import org.genxdm.xs.facets.Pattern;
import org.genxdm.xs.resolve.PrefixResolver;
import org.genxdm.xs.types.ComplexUrType;
import org.genxdm.xs.types.NativeType;
import org.genxdm.xs.types.PrimeChoiceType;
import org.genxdm.xs.types.PrimeType;
import org.genxdm.xs.types.PrimeTypeKind;
import org.genxdm.xs.types.SequenceTypeVisitor;
import org.genxdm.xs.types.SimpleType;
import org.genxdm.xs.types.SimpleUrType;
import org.genxdm.xs.types.Type;

final class SimpleUrTypeImpl<A> extends AbstractPrimeExcludingNoneType<A> implements SimpleUrType<A>
{
	private final AtomBridge<A> atomBridge;
	private final NameSource nameBridge;
	private final QName m_name;
	private final SchemaCache<A> cache;

	public SimpleUrTypeImpl(final String W3C_XML_SCHEMA_NS_URI, final AtomBridge<A> atomBridge, final SchemaCache<A> cache)
	{
		this.atomBridge = atomBridge;
		this.nameBridge = atomBridge.getNameBridge();
		this.m_name = new QName(W3C_XML_SCHEMA_NS_URI, "anySimpleType");
		this.cache = cache;
	}

	public void accept(final SequenceTypeVisitor<A> visitor)
	{
		visitor.visit(this);
	}

	public void addEnumeration(final EnumerationDefinition<A> enumeration)
	{
		throw new AssertionError(getName());
	}

	public void addFacet(final Facet<A> facet)
	{
		throw new AssertionError(getName());
	}

	public void addPattern(final Pattern pattern)
	{
		throw new AssertionError(getName());
	}

	public boolean derivedFrom(final String namespace, final String name, final Set<DerivationMethod> derivationMethods)
	{
		return SchemaSupport.derivedFrom(this, namespace, name, derivationMethods, nameBridge);
	}

	public boolean derivedFromType(final Type<A> ancestorType, final Set<DerivationMethod> derivationMethods)
	{
		return SchemaSupport.derivedFromType(this, ancestorType, derivationMethods, nameBridge);
	}

	public ComplexUrType<A> getBaseType()
	{
		return cache.getComplexUrType();
	}

	public DerivationMethod getDerivationMethod()
	{
		return DerivationMethod.Restriction;
	}

	public Iterable<EnumerationDefinition<A>> getEnumerations()
	{
		throw new AssertionError(getName());
	}

	public Facet<A> getFacetOfKind(final FacetKind facetKind)
	{
		throw new AssertionError(getName());
	}

	public Iterable<Facet<A>> getFacets()
	{
		throw new AssertionError(getName());
	}

	public Set<DerivationMethod> getFinal()
	{
		return EnumSet.noneOf(DerivationMethod.class);
	}

	public PrimeTypeKind getKind()
	{
		return PrimeTypeKind.ANY_SIMPLE_TYPE;
	}

	public String getLocalName()
	{
		return m_name.getLocalPart();
	}

	public QName getName()
	{
		return m_name;
	}

	public NativeType getNativeType()
	{
		return NativeType.ANY_SIMPLE_TYPE;
	}

	public SimpleType<A> getNativeTypeDefinition()
	{
		return this;
	}

	public Iterable<Pattern> getPatterns()
	{
		throw new AssertionError(getName());
	}

	public ScopeExtent getScopeExtent()
	{
		return ScopeExtent.Global;
	}

	public String getTargetNamespace()
	{
		return m_name.getNamespaceURI();
	}

	public WhiteSpacePolicy getWhiteSpacePolicy()
	{
		return WhiteSpacePolicy.PRESERVE;
	}

	public boolean hasEnumerations()
	{
		return false;
	}

	public boolean hasFacetOfKind(final FacetKind facetKind)
	{
		return false;
	}

	public boolean hasFacets()
	{
		return false;
	}

	public boolean hasPatterns()
	{
		return false;
	}

	public boolean isAbstract()
	{
		return false;
	}

	public boolean isAnonymous()
	{
		return false;
	}

	public boolean isAtomicType()
	{
		return false;
	}

	public boolean isAtomicUrType()
	{
		return false;
	}

	public boolean isChoice()
	{
		return false;
	}

	public boolean isComplexUrType()
	{
		return false;
	}

	public boolean isFinal(final DerivationMethod derivation)
	{
		return false;
	}

	public boolean isID()
	{
		return false;
	}

	public boolean isIDREF()
	{
		return false;
	}

	public boolean isIDREFS()
	{
		return false;
	}

	public boolean isListType()
	{
		return false;
	}

	public boolean isLocked()
	{
		return true;
	}

	public boolean isNative()
	{
		return true;
	}

	public boolean isSimpleUrType()
	{
		return true;
	}

	public boolean isUnionType()
	{
		return false;
	}

	public void lock()
	{
		// Ignore
	}

	public String normalize(final String initialValue)
	{
		return initialValue;
	}

	public SimpleUrType<A> prime()
	{
		return this;
	}

	public void setAbstract(final boolean isAbstract)
	{
		throw new AssertionError(getName());
	}

	public void setFinal(final DerivationMethod derivation, final boolean enabled)
	{
		throw new AssertionError(getName());
	}

	public boolean subtype(final PrimeType<A> rhs)
	{
		switch (rhs.getKind())
		{
			case CHOICE:
			{
				final PrimeChoiceType<A> choiceType = (PrimeChoiceType<A>)rhs;
				return subtype(choiceType.getLHS()) || subtype(choiceType.getRHS());
			}
			case ANY_TYPE:
			case ANY_SIMPLE_TYPE:
			{
				return true;
			}
			default:
			{
				return false;
			}
		}
	}

	@Override
	public String toString()
	{
		return "xs:anySimpleType";
	}

	public List<A> validate(final List<? extends A> value)
	{
		final String strval = atomBridge.getC14NString(value);
		return atomBridge.wrapAtom(atomBridge.createUntypedAtomic(strval));
	}

	public List<A> validate(final String value)
	{
		return atomBridge.wrapAtom(atomBridge.createUntypedAtomic(value));
	}

	public List<A> validate(final String value, final PrefixResolver resolver)
	{
		return validate(value);
	}
}
