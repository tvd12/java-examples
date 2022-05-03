package com.tvd12.example.pattern.gson;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.google.gson.Gson;

public class SimpleSerializationTest {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        ActorGson rudyYoungblood = new ActorGson(
            "nm2199632",
            sdf.parse("21-09-1982"),
            Arrays.asList("Apocalypto",
                "Beatdown", "Wind Walkers")
        );
        Movie movie = new Movie(
            "tt0472043",
            "Mel Gibson",
            Arrays.asList(rudyYoungblood));

        String serializedMovie = new Gson().toJson(movie);

        System.out.println(serializedMovie);
    }

}
