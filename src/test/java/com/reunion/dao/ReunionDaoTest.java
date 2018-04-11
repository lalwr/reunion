package com.reunion.dao;

import com.reunion.config.DbConfig;
import com.reunion.domain.Member;
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

    /*@Test
    public void testSelectAll() throws Exception{
        List<Member> reunions = reunionDao.selectAll();
        Assert.assertEquals(2, reunions.size());
        for(Member reunion: reunions){
            System.out.println(reunion);
        }
    }

    @Test
    public void testInsertRole() throws Exception{
        Member member = new Member();
        member.setId("kim");
        member.setName("김형진");
        member.setPassword("123");
        member.setRegDate("2018-04-12 10:05:30");
        member.setEditDate("2018-04-12 10:05:30");
        int memberNo = reunionDao.insertReunion(member);
        System.out.println(memberNo);
    }

    @Test
    public void testUpdateRole() throws Exception{
        // given
        Member member = new Member();
        member.setId("test");
        member.setName("홍길동");
        member.setPassword("123");
        member.setRegDate("2018-04-12 10:05:30");
        member.setEditDate("2018-04-12 10:05:30");
        int memberNo = reunionDao.insertReunion(member);
        Member member1 = reunionDao.selectReunion(memberNo);

        // when
        member1.setName("testUpdate");
        reunionDao.updateReunion(member1);

        // then
        Member member2 = reunionDao.selectReunion(memberNo);
        Assert.assertEquals("testUpdate", member2.getName());
        System.out.println(memberNo);

    }

    @Test
    public void testSelectOne() throws Exception{
        // given
        Member member = new Member();
        member.setId("test123");
        member.setName("홍길동");
        member.setPassword("123");
        member.setRegDate("2018-04-12 10:05:30");
        member.setEditDate("2018-04-12 10:05:30");
        int memberNo = reunionDao.insertReunion(member);

        // when
        Member member1 = reunionDao.selectReunion(memberNo);

        // then
        Assert.assertEquals(member.getId(), member1.getId());
        Assert.assertEquals(member.getName(), member1.getName());

    }

    @Test
    public void deleteRole() throws Exception{

        // given
        Member member = new Member();
        member.setId("test4747");
        member.setName("홍길순");
        member.setPassword("123");
        member.setRegDate("2018-04-12 10:05:30");
        member.setEditDate("2018-04-12 10:05:30");
        int memberNo = reunionDao.insertReunion(member);
        List<Member> members = reunionDao.selectAll();
        Assert.assertEquals(3, members.size());

        // when
        reunionDao.deleteReunion(memberNo);

        // then
        List<Member> member2 = reunionDao.selectAll();
        Assert.assertEquals(member2.size(), members.size() - 1);
    }*/
}
