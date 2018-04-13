package com.reunion.service;

import com.reunion.dao.SchoolDao;
import com.reunion.domain.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolDao schoolDao;

    @Override
    @Transactional(readOnly = true)
    public List<School> getSchools() {
        return schoolDao.selectAll();
    }

    @Override
    @Transactional(readOnly = true)
    public School getSchool(String schoolName) {
        return schoolDao.selectSchool(schoolName);
    }

    @Override
    @Transactional
    public int addSchool(School school) {
        return schoolDao.insertSchool(school);
    }

    @Override
    @Transactional
    public int deleteSchool(int schoolId) {
        return schoolDao.deleteSchool(schoolId);
    }

    @Override
    @Transactional
    public int updateSchool(School school) {
        return schoolDao.updateSchool(school);
    }
}
