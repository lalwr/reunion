package com.reunion.service;

import com.reunion.dao.MemberDao;
import com.reunion.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(String memberId) {
        return memberDao.selectMember(memberId);
    }

    @Override
    @Transactional
    public int addMember(Member member) {
        return memberDao.insertMember(member);
    }

    @Override
    @Transactional
    public int deleteMember(int memberId) {
        return memberDao.deleteMember(memberId);
    }

    @Override
    @Transactional
    public int updateMember(Member member) {
        return memberDao.updateMember(member);
    }

}
