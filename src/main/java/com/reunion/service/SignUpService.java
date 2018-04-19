package com.reunion.service;

import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;

import java.util.List;

public interface SignUpService {
    public void signUp(String memberId,String name,String password,String school);
    public void update(String memberId, String password,String school);
    public void delete(String memberId);
}
