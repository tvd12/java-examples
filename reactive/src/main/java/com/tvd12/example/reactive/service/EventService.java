package com.tvd12.example.reactive.service;

import com.tvd12.example.common.JsonReader;
import com.tvd12.example.reactive.data.Event;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.util.List;

@EzySingleton
public class EventService {

    public List<Event> getMatchingEvents() {
        return JsonReader.readList("matching_events.json", Event.class);
    }

}
