package com.tvd12.example.pattern.gson;

import com.google.gson.Gson;

public class SimpleDeserializationTest {

	public static void main(String[] args) {
		String jsonInput = "{\"imdbId\":\"tt0472043\",\"actors\":" +
				  "[{\"imdbId\":\"nm2199632\",\"dateOfBirth\":\"1982-09-21T12:00:00+01:00\"," +
				  "\"filmography\":[\"Apocalypto\",\"Beatdown\",\"Wind Walkers\"]}]}";
				         
		Movie outputMovie = new Gson().fromJson(jsonInput, Movie.class);
		System.out.println(outputMovie.toString());
	}
	
}
