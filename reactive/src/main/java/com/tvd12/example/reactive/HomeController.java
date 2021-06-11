package com.tvd12.example.reactive;

import com.tvd12.example.reactive.data.Event;
import com.tvd12.example.reactive.data.HomeData;
import com.tvd12.example.reactive.data.Member;
import com.tvd12.example.reactive.data.News;
import com.tvd12.example.reactive.service.EventService;
import com.tvd12.example.reactive.service.MemberService;
import com.tvd12.example.reactive.service.NewsService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.util.List;

@EzySingleton
@AllArgsConstructor
public class HomeController {

    private final NewsService newsService;
    private final EventService eventService;
    private final MemberService memberService;

    public HomeData getHomeData() {
        return HomeData.builder()
            .newsList(getHotNewsList())
            .eventList(getMatchingEvents())
            .memberList(getNewPublicMembers())
            .build();
    }

    private List<News> getHotNewsList() {
        return newsService.getHotNewsList();
    }

    private List<Event> getMatchingEvents() {
        return eventService.getMatchingEvents();
    }

    private List<Member> getNewPublicMembers() {
        return memberService.getNewPublicMembers();
    }
}
