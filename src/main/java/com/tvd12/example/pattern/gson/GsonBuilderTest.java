package com.tvd12.example.pattern.gson;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonBuilderTest {

	public static void main(String[] args) throws Exception {
		Gson gson = new GsonBuilder()
				  .setPrettyPrinting()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .disableHtmlEscaping()
				  .registerTypeAdapter(ActorGson.class, new ActorGsonSerializer())
				  .create();
				  
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				  
				ActorGson rudyYoungblood = new ActorGson("nm2199632",
				  sdf.parse("21-09-1982"), Arrays.asList("Apocalypto","Beatdown", "Wind Walkers"));
				 
				MovieWithNullValue movieWithNullValue = new MovieWithNullValue(null,
				  "Mel Gibson", Arrays.asList(rudyYoungblood));
				  
				String serializedMovie = gson.toJson(movieWithNullValue);
				
				System.out.println(serializedMovie);
	}
	
}
