package com.tvd12.example.reactive.service;

import com.tvd12.example.common.JsonReader;
import com.tvd12.example.reactive.data.News;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.util.List;

@EzySingleton
public class NewsService {
    public List<News> getHotNewsList() {
        return JsonReader.readList("hot_news_list.json", News.class);
    }
}
