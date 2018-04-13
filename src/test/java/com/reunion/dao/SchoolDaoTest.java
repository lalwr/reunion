package com.reunion.dao;

import com.reunion.config.DbConfig;
import com.reunion.domain.School;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DbConfig.class)
@Transactional
//@Test annotation과 함께 설정된 @Transactional은 항상 rollback된다.
public class SchoolDaoTest {
    @Autowired
    DataSource dataSource;
    SchoolDao schoolDao;

    @Before
    public void setUp() {
        schoolDao = new SchoolDao(dataSource);
    }

    @Test
    public void config() throws Exception{
        Assert.assertTrue(true);
    }

    @Test
    public void testSelectAll() throws Exception{
        List<School> schools = schoolDao.selectAll();
        Assert.assertEquals(1, schools.size());
        for(School reunion: schools){
            System.out.println(reunion);
        }
    }

    @Test
    public void testInsertSchool() throws Exception{
        School school = new School();
        school.setName("패캠 중학교");
        school.setCategory("middle");
        school.setRegDate("2018-04-12");
        int schoolNo = schoolDao.insertSchool(school);
        System.out.println(schoolNo);
    }

    @Test
    public void testUpdateSchool() throws Exception{
        // given
        School school = new School();
        school.setName("패캠 중학교");
        school.setCategory("middle");
        school.setRegDate("2018-04-12");
        int schoolNo = schoolDao.insertSchool(school);
        School school1 = schoolDao.selectSchool(schoolNo);

        // when
        school1.setName("FC elementary");
        schoolDao.updateSchool(school1);

        // then
        School school2 = schoolDao.selectSchool(schoolNo);
        Assert.assertEquals("FC elementary", school2.getName());
        System.out.println(schoolNo);

    }

    @Test
    public void testSelectOne() throws Exception{
        // given
        School school = new School();
        school.setName("패캠 중학교");
        school.setCategory("middle");
        school.setRegDate("2018-04-12");
        int schoolNo = schoolDao.insertSchool(school);

        // when
        School school1 = schoolDao.selectSchool(schoolNo);

        // then
        Assert.assertEquals(school.getName(), school1.getName());
        Assert.assertEquals(school.getCategory(), school1.getCategory());

    }

    @Test
    public void deleteSchool() throws Exception{

        // given
        School school = new School();
        school.setName("패캠 중학교");
        school.setCategory("middle");
        school.setRegDate("2018-04-12");
        int schoolNo = schoolDao.insertSchool(school);
        List<School> schools = schoolDao.selectAll();
        Assert.assertEquals(2, schools.size());

        // when
        schoolDao.deleteSchool(schoolNo);

        // then
        List<School> school2 = schoolDao.selectAll();
        Assert.assertEquals(school2.size(), schools.size() - 1);
    }
}
