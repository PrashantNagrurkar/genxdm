/**
 * Copyright (c) 2011 TIBCO Software Inc.
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
package org.genxdm.bridgetest.base;

import org.genxdm.base.Cursor;
import org.genxdm.bridgetest.TestBase;

import org.junit.Test;

// this is also the test for base/io/Reader
// is it the test for nodes/Informer?  Bookmark is-a Informer and is-a NodeSource, too.
// it may *not* be the test case for the navigator, though.
public abstract class CursorTestBase<N>
    extends TestBase<N>
{
    @Test
    public void doSomething()
    {
    }
}
