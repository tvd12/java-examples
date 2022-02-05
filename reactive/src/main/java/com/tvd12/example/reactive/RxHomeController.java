package com.tvd12.example.reactive;

import com.tvd12.example.reactive.core.Reactive;
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
public class RxHomeController {

    private final NewsService newsService;
    private final EventService eventService;
    private final MemberService memberService;

    public HomeData getHomeData() {
        return Reactive.single()
            .register("newsList", this::getHotNewsList)
            .register("events", this::getMatchingEvents)
            .register("members", this::getNewPublicMembers)
            .blockingGet(it ->
                HomeData.builder()
                    .newsList(it.get("newsList"))
                    .eventList(it.get("events"))
                    .memberList(it.get("members"))
                    .build()
            );
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
