package com.reunion.service;

import com.reunion.dao.SchoolDao;
import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.reunion.dao.MemberDao;
import com.reunion.dao.MemberSchoolDao;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Autowired
    MemberSchoolDao memberSchoolDao;
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    MemberDao memberDao;

    @Override
    @Transactional
    public void signUp(String id,String name,String password,String school) {
        Date date = new Date();
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        member.setRegDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        member.setEditDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        int memberNo = memberDao.insertMember(member);
        int schoolNo = schoolDao.selectSchool(school).getNo();
        MemberSchool ms = new MemberSchool();
        ms.setMemberNo(memberNo);
        ms.setSchoolNo(schoolNo);
        memberSchoolDao.insertMemberSchool(ms);
    }
}
