package com.reunion.service;

import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;

import java.util.List;

public interface SignUpService {
    public void signUp(String id,String name,String password,String school);
}
