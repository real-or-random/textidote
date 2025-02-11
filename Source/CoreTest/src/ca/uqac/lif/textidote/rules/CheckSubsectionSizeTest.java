/*
    TeXtidote, a linter for LaTeX documents
    Copyright (C) 2018  Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.textidote.rules;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import ca.uqac.lif.textidote.Advice;
import ca.uqac.lif.textidote.as.AnnotatedString;
import ca.uqac.lif.textidote.rules.CheckSubsectionSize;

public class CheckSubsectionSizeTest 
{
	@Test
	public void test1()
	{
		AnnotatedString in_string = AnnotatedString.read(new Scanner(CheckSubsectionSizeTest.class.getResourceAsStream("data/test-subsec-1.tex")));
		CheckSubsectionSize r = new CheckSubsectionSize();
		r.setMinNumWords(40);
		List<Advice> ad_list = r.evaluate(in_string);
		assertEquals(1, ad_list.size());
		Advice ad = ad_list.get(0);		
		assertEquals(17, in_string.getPosition(ad.getRange().getStart()).getLine());
	}
	
	@Test
	public void test2()
	{
		AnnotatedString in_string = AnnotatedString.read(new Scanner(CheckSubsectionSizeTest.class.getResourceAsStream("data/test-subsec-2.tex")));
		CheckSubsectionSize r = new CheckSubsectionSize();
		r.setMinNumWords(40);
		List<Advice> ad_list = r.evaluate(in_string);
		assertTrue(ad_list.isEmpty());
	}
	
	@Test
	public void test3()
	{
		AnnotatedString in_string = AnnotatedString.read(new Scanner(CheckSubsectionSizeTest.class.getResourceAsStream("data/test-subsec-4.tex")));
		CheckSubsectionSize r = new CheckSubsectionSize();
		r.setMinNumWords(40);
		List<Advice> ad_list = r.evaluate(in_string);
		assertFalse(ad_list.isEmpty());
	}
}
