package com.tvd12.example.reactive.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class HomeData {
    private List<News> newsList;
    private List<Event> eventList;
    private List<Member> memberList;
}
