package com.infinityappsolutions.lib.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class IASGson<T> {

	public IASGson() {
	}

	public String toGson(T object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		return gson.toJson(object);
	}

	public String toGson(ArrayList<T> object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		return gson.toJson(object);
	}

	public T fromGson(String gsonString, Type listOfTestObject)
			throws JsonSyntaxException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(gsonString, listOfTestObject);
	}

}
