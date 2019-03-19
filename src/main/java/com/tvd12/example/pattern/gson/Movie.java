package com.tvd12.example.pattern.gson;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String imdbId;
    private String director;
    private List<ActorGson> actors;
    
    @Override
    public String toString() {
      return "Movie [imdbId=" + imdbId + ", director=" + director + ",actors=" + actors + "]";
    }
}