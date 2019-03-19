package com.tvd12.example.pattern.gson;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorGson {
    private String imdbId;
    private Date dateOfBirth;
    private List<String> filmography;
    
    @Override
    public String toString() {
        return "ActorGson [imdbId=" + imdbId + ", dateOfBirth=" + dateOfBirth +
          ",filmography=" + filmography + "]";
    }
}