package com.tvd12.example.reactive.service;

import com.tvd12.example.common.JsonReader;
import com.tvd12.example.reactive.data.Member;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;

import java.util.List;

@EzySingleton
public class MemberService {
    public List<Member> getNewPublicMembers() {
        return JsonReader.readList("new_public_members.json", Member.class);
    }
}
