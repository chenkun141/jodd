// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.util;

import jodd.util.collection.ArrayEnumeration;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {

	@Test
	void testEquals() {
		final Object a = new Integer(173);
		final Object b = new Integer(1);
		final Object c = new Integer(173);

		assertTrue(Objects.equals(a, a));
		assertTrue(Objects.equals(a, c));
		assertTrue(Objects.equals(c, a));
		assertFalse(Objects.equals(a, b));
		assertFalse(Objects.equals(b, a));

		assertFalse(Objects.equals(a, null));
		assertFalse(Objects.equals(null, a));

		assertTrue(Objects.equals(null, null));
	}

	@Test
	void testToString() {
		assertNull(Util.toString(null));
		assertEquals("abcd", Util.toString("abcd"));
		assertEquals("1234", Util.toString(1234));
		assertEquals("1234.0", Util.toString(1234d));
	}
	
	@Test
	void testLength() {
		assertEquals(0, Util.length(null));
		assertEquals(-1, Util.length(1234));

		assertEquals(0, Util.length(StringPool.EMPTY));
		assertEquals(4, Util.length("abcd"));

		final Collection<String> coll = new ArrayList<>();
		assertEquals(0, Util.length(coll));
		coll.add("abcd");
		coll.add("1234");
		assertEquals(2, Util.length(coll));

		final Iterator<String> iterator = coll.iterator();
		assertEquals(2, Util.length(iterator));

		final Map<Long, String> map = new HashMap<>();
		assertEquals(0, Util.length(map));
		map.put(1l, "abcd");
		map.put(2l, "1234");
		assertEquals(2, Util.length(map));

		final Integer[] array = new Integer[] { 1, 2, 3, 4, 5 };
		assertEquals(5, Util.length(array));
		final ArrayEnumeration<Integer> ae = new ArrayEnumeration<>(array);
		assertEquals(5, Util.length(ae));
	}
	
	@Test
	void testContainsElement() {
		assertFalse(Util.containsElement(null, "abcd"));
		assertFalse(Util.containsElement(1234, "abcd"));

		assertFalse(Util.containsElement("abcd", null));
		assertTrue(Util.containsElement("abcd", "bc"));
		assertFalse(Util.containsElement("abcd", "xx"));

		final Collection<String> coll = new ArrayList<>();
		coll.add("abcd");
		coll.add("1234");
		assertTrue(Util.containsElement(coll, "abcd"));
		assertFalse(Util.containsElement(coll, "xx"));

		final Iterator<String> iterator = coll.iterator();
		assertTrue(Util.containsElement(iterator, "abcd"));
		assertFalse(Util.containsElement(iterator, "xx"));

		final Map<Long, String> map = new HashMap<>();
		map.put(1l, "abcd");
		map.put(2l, "1234");
		assertTrue(Util.containsElement(map, "abcd"));
		assertFalse(Util.containsElement(map, "xx"));

		final Integer[] array = new Integer[] { 1, 2, 3, 4, 5 };
		assertTrue(Util.containsElement(array, 1));
		assertFalse(Util.containsElement(array, 10));
		final ArrayEnumeration<Integer> ae = new ArrayEnumeration<>(array);
		assertTrue(Util.containsElement(ae, 1));
		assertFalse(Util.containsElement(ae, 10));
	}
}
