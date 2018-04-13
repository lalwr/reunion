package com.reunion.service;

import com.reunion.domain.MemberSchool;

import java.util.List;

public interface MemberSchoolService {
    public List<MemberSchool> getMemberSchools();
    public MemberSchool getMemberSchool(int memberSchoolId);
    public int addMemberSchool(MemberSchool memberSchool);
    public int deleteMemberSchool(int memberSchoolId);
    public int updateMemberSchool(MemberSchool memberSchool);
}
