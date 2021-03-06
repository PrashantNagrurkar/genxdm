/*
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
package org.genxdm.typed.types;

import org.genxdm.exceptions.PreCondition;

/**
 * The quantifier approximates the possible number of items in a sequence type with the occurrence indicators supported
 * by the XPath/XQuery type system (?, +, *).
 * <p/>
 * For interim results, the auxiliary occurrence indicator 1 denotes exactly one occurrence.
 * <p/>
 * <br/>
 * <table border="1">
 * <tr>
 * <th>Enum</th>
 * <th>0</th>
 * <th>1</th>
 * <th>*</th>
 * <th>{@link #toString}</th>
 * </tr>
 * <tr>
 * <td>{@link #NONE}</td>
 * <td>0</td>
 * <td>0</td>
 * <td>0</td>
 * <td>none</td>
 * </tr>
 * <tr>
 * <td>{@link #EMPTY}</td>
 * <td>1</td>
 * <td>0</td>
 * <td>0</td>
 * <td>empty</td>
 * </tr>
 * <tr>
 * <td>{@link #EXACTLY_ONE}</td>
 * <td>0</td>
 * <td>1</td>
 * <td>0</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>{@link #OPTIONAL}</td>
 * <td>1</td>
 * <td>1</td>
 * <td>0</td>
 * <td>?</td>
 * </tr>
 * <tr>
 * <td>{@link #ONE_OR_MORE}</td>
 * <td>0</td>
 * <td>1</td>
 * <td>1</td>
 * <td>+</td>
 * </tr>
 * <tr>
 * <td>{@link #ZERO_OR_MORE}</td>
 * <td>1</td>
 * <td>1</td>
 * <td>1</td>
 * <td>*</td>
 * </tr>
 * </table>
 */
public enum Quantifier
{
    NONE(0), EMPTY(4), EXACTLY_ONE(2), OPTIONAL(6), ONE_OR_MORE(3), ZERO_OR_MORE(7);

    final int m_mask;

    private Quantifier(final int mask)
    {
        m_mask = mask;
    }

    public boolean contains(final Quantifier other)
    {
        PreCondition.assertArgumentNotNull(other, "other");
        return (m_mask & other.m_mask) == other.m_mask;
    }

    /**
     * Determines whether this quantifier is contained in the specified set of quantifiers.
     */
    public boolean in(final Quantifier... others)
    {
        PreCondition.assertArgumentNotNull(others, "others");
        for (final Quantifier other : others)
        {
            if ((m_mask & other.m_mask) == other.m_mask)
            {
                return true;
            }
        }
        return false;
    }

    public Quantifier sum(final Quantifier other)
    {
        PreCondition.assertArgumentNotNull(other, "other");
        switch (this)
        {
            case NONE:
            {
                return NONE;
            }
            case EXACTLY_ONE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    default:
                    {
                        return ONE_OR_MORE;
                    }
                }
            }
            case EMPTY:
            case OPTIONAL:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    case EXACTLY_ONE:
                    case ONE_OR_MORE:
                    {
                        return ONE_OR_MORE;
                    }
                    case EMPTY:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        return ZERO_OR_MORE;
                    }
                }
            }
            case ONE_OR_MORE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    default:
                    {
                        return ONE_OR_MORE;
                    }
                }
            }
            case ZERO_OR_MORE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    case EMPTY:
                    {
                        return ZERO_OR_MORE;
                    }
                    case EXACTLY_ONE:
                    {
                        return ONE_OR_MORE;
                    }
                    case OPTIONAL:
                    {
                        return ZERO_OR_MORE;
                    }
                    case ONE_OR_MORE:
                    {
                        return ONE_OR_MORE;
                    }
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError(other);
                    }
                }
            }
            default:
            {
                throw new AssertionError(this);
            }
        }
    }

    public Quantifier choice(final Quantifier other)
    {
        PreCondition.assertArgumentNotNull(other, "other");
        switch (this)
        {
            case NONE:
            {
                // none is the identity for choice.
                return other;
            }
            case EMPTY:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    case EMPTY:
                    {
                        return EMPTY;
                    }
                    case EXACTLY_ONE:
                    {
                        return OPTIONAL;
                    }
                    case OPTIONAL:
                    {
                        return OPTIONAL;
                    }
                    case ONE_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError(other);
                    }
                }
            }
            case EXACTLY_ONE:
            {
                switch (other)
                {
                    case EMPTY:
                    {
                        return OPTIONAL;
                    }
                    case EXACTLY_ONE:
                    {
                        return EXACTLY_ONE;
                    }
                    case OPTIONAL:
                    {
                        return OPTIONAL;
                    }
                    case ONE_OR_MORE:
                    {
                        return ONE_OR_MORE;
                    }
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError(other);
                    }
                }
            }
            case ONE_OR_MORE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    case EMPTY:
                    {
                        return ZERO_OR_MORE;
                    }
                    case EXACTLY_ONE:
                    case ONE_OR_MORE:
                    {
                        return ONE_OR_MORE;
                    }
                    case OPTIONAL:
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError("lhs=" + this + ", rhs=" + other);
                    }
                }
            }
            case OPTIONAL:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return other;
                    }
                    case EMPTY:
                    {
                        return OPTIONAL;
                    }
                    case EXACTLY_ONE:
                    case OPTIONAL:
                    {
                        return OPTIONAL;
                    }
                    case ONE_OR_MORE:
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError(other);
                    }
                }
            }
            case ZERO_OR_MORE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    default:
                    {
                        return ZERO_OR_MORE;
                    }
                }
            }
            default:
            {
                throw new AssertionError("lhs=" + this + ", rhs=" + other);
            }
        }
    }

    public Quantifier product(final Quantifier other)
    {
        PreCondition.assertArgumentNotNull(other, "other");
        switch (this)
        {
            case NONE:
            {
                return NONE;
            }
            case EXACTLY_ONE:
            {
                return other;
            }
            case ONE_OR_MORE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    case EMPTY:
                    {
                        return EMPTY;
                    }
                    case EXACTLY_ONE:
                    case ONE_OR_MORE:
                    {
                        return ONE_OR_MORE;
                    }
                    case OPTIONAL:
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError("lhs=" + this + ", rhs=" + other);
                    }
                }
            }
            case EMPTY:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    default:
                    {
                        return EMPTY;
                    }
                }
            }
            case OPTIONAL:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    case EMPTY:
                    {
                        return EMPTY;
                    }
                    case EXACTLY_ONE:
                    case OPTIONAL:
                    {
                        return OPTIONAL;
                    }
                    case ONE_OR_MORE:
                    case ZERO_OR_MORE:
                    {
                        return ZERO_OR_MORE;
                    }
                    default:
                    {
                        throw new AssertionError("lhs=" + this + ", rhs=" + other);
                    }
                }
            }
            case ZERO_OR_MORE:
            {
                switch (other)
                {
                    case NONE:
                    {
                        return NONE;
                    }
                    default:
                    {
                        return ZERO_OR_MORE;
                    }
                }
            }
            default:
            {
                throw new AssertionError("lhs=" + this + ", rhs=" + other);
            }
        }
    }

    public boolean isNone()
    {
        return (this == NONE);
    }

    public boolean isEmpty()
    {
        return (this == EMPTY);
    }

    public boolean isOptional()
    {
        return (this == OPTIONAL);
    }

    public boolean isExactlyOne()
    {
        return (this == EXACTLY_ONE);
    }

    /**
     * Computes the quantifier for a type V when it is replaced by V[1] according to the following table: <br/>
     * <table border="1">
     * <tr>
     * <th>this</th>
     * <th>aggregate</th>
     * </tr>
     * <tr>
     * <td>?</td>
     * <td>?</td>
     * </tr>
     * <tr>
     * <td>*</td>
     * <td>?</td>
     * </tr>
     * <tr>
     * <td>1</td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td>+</td>
     * <td>1</td>
     * </tr>
     * <tr>
     * <td>none</td>
     * <td>none</td>
     * </tr>
     * <tr>
     * <td>empty</td>
     * <td>empty</td>
     * </tr>
     * </table>
     */
    public Quantifier single()
    {
        switch (this)
        {
            case OPTIONAL:
                return OPTIONAL;
            case ZERO_OR_MORE:
                return OPTIONAL;
            case EXACTLY_ONE:
                return EXACTLY_ONE;
            case ONE_OR_MORE:
                return EXACTLY_ONE;
            case NONE:
                return NONE;
            case EMPTY:
                return EMPTY;
            default:
            {
                throw new AssertionError(name());
            }
        }
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case NONE:
                return "none";
            case EXACTLY_ONE:
                return "1";
            case ONE_OR_MORE:
                return "+";
            case EMPTY:
                return "empty";
            case OPTIONAL:
                return "?";
            case ZERO_OR_MORE:
                return "*";
            default:
            {
                throw new AssertionError(name());
            }
        }
    }

    /**
     * Approximates the schema minOccurs and maxOccurs with the {@link Quantifier}.
     * 
     * @param minOccurs
     *            The min-occurs value.
     * @param maxOccurs
     *            The max-occurs value.
     * @return The {@link Quantifier} approximation.
     */
    public static Quantifier approximate(final int minOccurs, final int maxOccurs)
    {
        if (minOccurs == 0)
        {
            if (maxOccurs == 0)
            {
                return Quantifier.EMPTY;
            }
            else if (maxOccurs == 1)
            {
                return Quantifier.OPTIONAL;
            }
            else
            {
                return Quantifier.ZERO_OR_MORE;
            }
        }
        else if (minOccurs == 1)
        {
            if (maxOccurs == 1)
            {
                return Quantifier.EXACTLY_ONE;
            }
            else
            {
                return Quantifier.ONE_OR_MORE;
            }
        }
        else
        {
            return Quantifier.ONE_OR_MORE;
        }
    }
}
