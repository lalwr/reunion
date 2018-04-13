package com.reunion.service;

import com.reunion.dao.MemberSchoolDao;
import com.reunion.domain.MemberSchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberSchoolServiceImpl implements MemberSchoolService{
    @Autowired
    MemberSchoolDao memberSchoolDao;

    @Override
    @Transactional(readOnly = true)
    public List<MemberSchool> getMemberSchools() {
        return memberSchoolDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberSchool getMemberSchool(int memberSchoolId) {
        return memberSchoolDao.selectMemberSchool(memberSchoolId);
    }

    @Override
    @Transactional
    public int addMemberSchool(MemberSchool memberSchool) {
        return memberSchoolDao.insertMemberSchool(memberSchool);
    }

    @Override
    @Transactional
    public int deleteMemberSchool(int memberSchoolId) {
        return memberSchoolDao.deleteMemberSchool(memberSchoolId);
    }

    @Override
    @Transactional
    public int updateMemberSchool(MemberSchool memberSchool) {
        return memberSchoolDao.updateMemberSchool(memberSchool);
    }
}
