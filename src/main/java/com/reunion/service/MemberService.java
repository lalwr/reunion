package com.reunion.service;

import com.reunion.domain.Member;

import java.util.List;

public interface MemberService {
    public List<Member> getMembers();
    public Member getMember(String memberId);
    public int addMember(Member member);
    public int deleteMember(int memberNo);
    public int updateMember(Member member);
}
