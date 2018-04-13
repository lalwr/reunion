package com.reunion.service;

import com.reunion.domain.School;

import java.util.List;

public interface SchoolService {
    public List<School> getSchools();
    public School getSchool(String schoolName);
    public int addSchool(School school);
    public int deleteSchool(int schoolId);
    public int updateSchool(School school);
}
