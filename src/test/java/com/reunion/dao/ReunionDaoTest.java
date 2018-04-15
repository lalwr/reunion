package com.reunion.dao;

import com.reunion.config.DbConfig;
import com.reunion.domain.Condition;
import com.reunion.domain.Member;
import com.reunion.domain.Reunion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.zip.DataFormatException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DbConfig.class)
@Transactional
//@Test annotation과 함께 설정된 @Transactional은 항상 rollback된다.
public class ReunionDaoTest {
    @Autowired
    DataSource dataSource;
    ReunionDao reunionDao;

    @Before
    public void setUp() {
        reunionDao = new ReunionDao(dataSource);
    }

    @Test
    public void config() throws Exception{
        Assert.assertTrue(true);
    }

    @Test
    public void testListReunion() throws Exception{
        //given
        Condition condition1 = new Condition();
        condition1.setPageSize(999);
        condition1.setSearchType("subject");
        condition1.setSearchText("강남");

        Condition condition2 = new Condition();
        condition2.setPageSize(999);
        condition2.setSearchType("content");
        condition2.setSearchText("강남");

        //when
        List<Reunion> reunions1 = reunionDao.listReunion(condition1);
        int cnt1 = reunionDao.listCnt(condition1);

        List<Reunion> reunions2 = reunionDao.listReunion(condition2);
        int cnt2 = reunionDao.listCnt(condition2);


        //then
        Assert.assertEquals(cnt1, reunions1.size());
        Assert.assertEquals(cnt2, reunions2.size());

    }

    @Test
    public void testWriteReunion() throws Exception{
        Reunion reunion = new Reunion();
        reunion.setNo("13");
        reunion.setSubject("제목 테스트");
        reunion.setContent("내용 테스트중");
        reunion.setRegId("kim");
        reunion.setRegDate("2018-04-12 10:05:30");
        reunion.setEditDate("2018-04-12 10:05:30");
        reunion.setSchoolNo("1");
        reunion.setCategoryNo("1");

        int chk = reunionDao.writeReunion(reunion);

        Assert.assertEquals(1, chk);
    }

    @Test
    public void testUpdateReunion() throws Exception{
        // given
        Reunion detailReunion1 = reunionDao.detailReunion("1");

        // when
        detailReunion1.setContent("내용 수정");
        reunionDao.updateReunion(detailReunion1);

        // then
        Reunion detailReunion2 = reunionDao.detailReunion("1");
        Assert.assertEquals("내용 수정", detailReunion2.getContent());

    }

    @Test
    public void testdDetailReunion() throws Exception{

        // when
        Reunion detailReunion1 = reunionDao.detailReunion("1");
        Reunion detailReunion2 = reunionDao.detailReunion("12372137128372189371298");

        // then
        Assert.assertEquals("강남에서 번개", detailReunion1.getSubject());
        Assert.assertEquals(null, detailReunion2);

    }

    @Test
    public void testdDeleteReunion() throws Exception{
        //given
        Reunion reunion = new Reunion();
        reunion.setNo("2");

        Condition condition = new Condition();
        condition.setPageSize(999);

        // when
        List<Reunion> list = reunionDao.listReunion(condition);
        int cnt1 = reunionDao.listCnt(condition);
        reunionDao.deleteReunion(reunion);

        int cnt2 = reunionDao.listCnt(condition);

        // then
        Assert.assertEquals(cnt1, list.size());
        Assert.assertEquals(cnt2, list.size()-1);
    }
}
