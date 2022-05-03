package com.tvd12.example.pattern.gson;

import java.util.List;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieWithNullValue {
    @Expose
    private String imdbId;
    private String director;

    @Expose
    private List<ActorGson> actors;
}

