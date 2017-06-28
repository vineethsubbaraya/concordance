package concordance;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class ConcordBuilderTest {
	ConcordBuilder cb;
	
	@Before
	public void setUp() {
		cb = new ConcordBuilder();
	}

	// Case when string has no special characters
	@Test
	public void test1_checkCharacters() {
		String str = "abc";
		assertFalse(cb.checkCharacters(str));
	}
	
	// case when string has only special character
	@Test
	public void test2_checkCharacters() {
		String str = "?";
		assertTrue(cb.checkCharacters(str));
	}
	
	//case when string has one or two special character
	@Test
	public void test3_checkCharacters() {
		String str = "i.e.";
		assertFalse(cb.checkCharacters(str));
	}
	
	@Test
	public void test_parser() {
		String str = "Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		TreeMap<String, WordData> concordance = cb.parser(br);
	
		assertEquals(2, concordance.get("a").count);
		assertEquals(createList(new int[] {1,1}), concordance.get("a").sentences);
		
		assertEquals(1, concordance.get("all").count);
		assertEquals(createList(new int[] {1}), concordance.get("all").sentences);
		
		assertEquals(1, concordance.get("alphabetical").count);
		assertEquals(createList(new int[] {1}), concordance.get("alphabetical").sentences);
		
		assertEquals(2, concordance.get("an").count);
		assertEquals(createList(new int[] {1,1}), concordance.get("an").sentences);
		
		assertEquals(1, concordance.get("appeared").count);
		assertEquals(createList(new int[] {2}), concordance.get("appeared").sentences);
		
		assertEquals(1, concordance.get("arbitrary").count);
		assertEquals(createList(new int[] {1}), concordance.get("arbitrary").sentences);
		
		assertEquals(1, concordance.get("bonus").count);
		assertEquals(createList(new int[] {2}), concordance.get("bonus").sentences);
		
		assertEquals(1, concordance.get("concordance").count);
		assertEquals(createList(new int[] {1}), concordance.get("concordance").sentences);
		
		assertEquals(1, concordance.get("document").count);
		assertEquals(createList(new int[] {1}), concordance.get("document").sentences);
		
		assertEquals(2, concordance.get("each").count);
		assertEquals(createList(new int[] {2,2}), concordance.get("each").sentences);
		
		assertEquals(1, concordance.get("english").count);
		assertEquals(createList(new int[] {1}), concordance.get("english").sentences);
		
		assertEquals(1, concordance.get("frequencies").count);
		assertEquals(createList(new int[] {1}), concordance.get("frequencies").sentences);
		
		assertEquals(1, concordance.get("generate").count);
		assertEquals(createList(new int[] {1}), concordance.get("generate").sentences);
		
		assertEquals(1, concordance.get("given").count);
		assertEquals(createList(new int[] {1}), concordance.get("given").sentences);
		
		assertEquals(1, concordance.get("i.e.").count);
		assertEquals(createList(new int[] {1}), concordance.get("i.e.").sentences);
		
		assertEquals(2, concordance.get("in").count);
		assertEquals(createList(new int[] {1,2}), concordance.get("in").sentences);
		
		assertEquals(1, concordance.get("label").count);
		assertEquals(createList(new int[] {2}), concordance.get("label").sentences);
		
		assertEquals(1, concordance.get("labeled").count);
		assertEquals(createList(new int[] {1}), concordance.get("labeled").sentences);
		
		assertEquals(1, concordance.get("list").count);
		assertEquals(createList(new int[] {1}), concordance.get("list").sentences);
		
		assertEquals(1, concordance.get("numbers").count);
		assertEquals(createList(new int[] {2}), concordance.get("numbers").sentences);
		
		assertEquals(1, concordance.get("occurrence").count);
		assertEquals(createList(new int[] {2}), concordance.get("occurrence").sentences);
		
		assertEquals(1, concordance.get("occurrences").count);
		assertEquals(createList(new int[] {1}), concordance.get("occurrences").sentences);
		
		assertEquals(1, concordance.get("of").count);
		assertEquals(createList(new int[] {1}), concordance.get("of").sentences);
		
		assertEquals(1, concordance.get("program").count);
		assertEquals(createList(new int[] {1}), concordance.get("program").sentences);
		
		assertEquals(1, concordance.get("sentence").count);
		assertEquals(createList(new int[] {2}), concordance.get("sentence").sentences);
		
		assertEquals(1, concordance.get("text").count);
		assertEquals(createList(new int[] {1}), concordance.get("text").sentences);
		
		assertEquals(1, concordance.get("that").count);
		assertEquals(createList(new int[] {1}), concordance.get("that").sentences);
		
		assertEquals(1, concordance.get("the").count);
		assertEquals(createList(new int[] {2}), concordance.get("the").sentences);
		
		assertEquals(1, concordance.get("which").count);
		assertEquals(createList(new int[] {2}), concordance.get("which").sentences);
		
		assertEquals(1, concordance.get("will").count);
		assertEquals(createList(new int[] {1}), concordance.get("will").sentences);
		
		assertEquals(2, concordance.get("with").count);
		assertEquals(createList(new int[] {1,2}), concordance.get("with").sentences);
		
		assertEquals(3, concordance.get("word").count);
		assertEquals(createList(new int[] {1,1,2}), concordance.get("word").sentences);
		
		assertEquals(1, concordance.get("write").count);
		assertEquals(createList(new int[] {1}), concordance.get("write").sentences);
		
		assertEquals(1, concordance.get("written").count);
		assertEquals(createList(new int[] {1}), concordance.get("written").sentences);
	}
	
	public ArrayList<Integer> createList(int[] arr) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++) {
			list.add(arr[i]);
		}
		return list;
	}
}
