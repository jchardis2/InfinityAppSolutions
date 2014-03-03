package com.infinityappsolutions.lib.gson;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class IASGson<T> {

	public IASGson() {
	}

	public String toGson(T object) {
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

	public T fromGson(String gsonString, Class<T> classT)
			throws JsonSyntaxException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(gsonString, classT);
	}

	public T fromGsonReader(JsonReader jsonReader, Type listOfTestObject)
			throws JsonSyntaxException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonReader, listOfTestObject);
	}

	public T fromGsonReader(JsonReader jsonReader, Class<T> classT)
			throws JsonSyntaxException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonReader, classT);
	}
}