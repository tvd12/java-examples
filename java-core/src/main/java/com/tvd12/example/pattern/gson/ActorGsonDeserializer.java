package com.tvd12.example.pattern.gson;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ActorGsonDeserializer implements JsonDeserializer<ActorGson> {
	 
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
 
    @Override
    public ActorGson deserialize(JsonElement json, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         
        JsonObject jsonObject = json.getAsJsonObject();
 
        JsonElement jsonImdbId = jsonObject.get("imdbId");
        JsonElement jsonDateOfBirth = jsonObject.get("dateOfBirth");
        JsonArray jsonFilmography = jsonObject.getAsJsonArray("filmography");
 
        ArrayList<String> filmList = new ArrayList<String>();
        if (jsonFilmography != null) {
            for (int i = 0; i < jsonFilmography.size(); i++) {
                filmList.add(jsonFilmography.get(i).getAsString());
            }
        }
 
    ActorGson actorGson = null;
	try {
		actorGson = new ActorGson(jsonImdbId.getAsString(),
		  sdf.parse(jsonDateOfBirth.getAsString()), filmList);
	} catch (ParseException e) {
		e.printStackTrace();
	}
        return actorGson;
    }
}