package com.infinityappsolutions.lib.webterms.test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.infinityappsolutions.lib.gson.IASGson;
import com.infinityappsolutions.lib.webterms.bean.Term;

public class TestMain {

	public static void main(String args[]) {
		testType();
		testGsonArrayList();
		testGson();
	}

	public static void testType() {
		Type listOfTestObject = new TypeToken<Term>() {
		}.getType();
		System.out.println(listOfTestObject);
		IASGson<Term> gson = new IASGson<Term>();
		Term term;
		Type listOfTestObject2 = new TypeToken<Term>() {
		}.getType();
		System.out.println(listOfTestObject2);
	}

	public static void testGson() {
		Term term = new Term(1L, "TestTerm", "TestDefinition", 1L);
		IASGson<Term> gson = new IASGson<Term>();
		String gsonTerm = gson.toGson(term);
		System.out.println(gsonTerm);
		Type type = new TypeToken<Term>() {
		}.getType();
		Term term2 = gson.fromGson(gsonTerm, type);
		System.out.println(equals(term, term2));
	}

	public static void testGsonArrayList() {
		IASGson<ArrayList<Term>> gson = new IASGson<ArrayList<Term>>();
		ArrayList<Term> terms = generateTermsList();
		String gsonTerm = gson.toGson(terms);
		System.out.println(gsonTerm);
		Type type = new TypeToken<ArrayList<Term>>() {
		}.getType();
		System.out.println(type);
		ArrayList<Term> term2 = gson.fromGson(gsonTerm, type);
		for (int i = 0; i < terms.size(); i++) {
			System.out.println(equals(terms.get(i), term2.get(i)));
		}
	}

	public static ArrayList<Term> generateTermsList() {
		ArrayList<Term> arrayList = new ArrayList<>();
		Long id = 0L;
		String name = "a";
		String def = "b";
		Long ownerID = 0L;
		for (long i = 0; i < 5; i++) {
			arrayList.add(new Term(i, name += name, def += def, i));
		}
		return arrayList;
	}

	public static boolean equals(Term term1, Term term2) {
		if (term1.getId() == term2.getId()
				&& term1.getName().equals(term2.getName())
				&& term1.getDefinition().equals(term2.getDefinition())
				&& term1.getOwnerId() == term2.getOwnerId()) {
			return true;
		}
		return false;
	}
}
