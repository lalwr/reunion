//package com.reunion.dao;
//
//import com.reunion.config.DbConfig;
//import com.reunion.domain.BoardReply;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DbConfig.class)
//@Transactional
//public class BoardReplyDaoTest {
//    @Autowired
//    DataSource dataSource;
//    BoardReplyDao boardReplyDao;
//
//    @Before
//    public void setUp() {
//        boardReplyDao = new BoardReplyDao(dataSource);
//    }
//
//    @Test
//    public void config() throws Exception{
//        Assert.assertTrue(true);
//    }
//
//     @Test
//    public void testSelectAll() throws Exception{
//        //given
//        List<BoardReply> replies = boardReplyDao.list();
//
//        // then
//        Assert.assertEquals(3, replies.size());
//        for(BoardReply reunion: replies){
//            System.out.println(reunion);
//        }
//    }
//
//    @Test
//    public void testInsert() throws Exception{
//        // given
//        BoardReply reply = new BoardReply();
//        reply.setContent("모여라하~");
//        reply.setMemberId("kii");
//        reply.setRegDate("2018-04-12");
//        reply.setEditDate("2018-04-12");
//        reply.setBoardNo(1);
//
//        // when
//        int boardNo = boardReplyDao.insert(reply);
//
//        // then
//        Assert.assertEquals(1, boardNo);
//        System.out.println(reply.getMemberId());
//    }
//
//    @Test
//    public void testDelete() throws Exception{
//
//        // given
//        BoardReply reply = new BoardReply();
//        reply.setContent("모여라하~");
//        reply.setMemberId("kii");
//        reply.setRegDate("2018-04-12");
//        reply.setEditDate("2018-04-12");
//        reply.setBoardNo(1);
//
//        int replyNo = boardReplyDao.insert(reply);
//        List<BoardReply> replies = boardReplyDao.list();
//        Assert.assertEquals(4, replies.size());
//
//        // when
//        System.out.println(reply.getNo());
//        boardReplyDao.delete(3);
//
//        // then
//        List<BoardReply> replies2 = boardReplyDao.list();
//        Assert.assertEquals(replies2.size(), replies.size() - 1);
////        System.out.println(replies2.size());
//    }
//
//    @Test
//    public void testUpdate() throws Exception{
//        // given
//        BoardReply reply = new BoardReply();
//        reply.setContent("모여라하~");
//        reply.setMemberId("kii");
//        reply.setRegDate("2018-04-12");
//        reply.setEditDate("2018-04-12");
//        reply.setBoardNo(1);
//
//        int boardNo = boardReplyDao.insert(reply);
//        //BoardReply reply2 = boardReplyDao.selectReply(boardNo);
//
//        // when
//        reply.setContent("술 가즈아");
//        reply.setRegDate("2018.04.05");
//        reply.setEditDate("2018.04.05");
//        boardReplyDao.update(reply);
//        System.out.println(reply.getNo());
//
//        // then
//        //BoardReply reply2 = boardReplyDao.selectReply(reply.getNo());
//        Assert.assertEquals("술 가즈아", reply.getContent());
////        System.out.println(schoolNo);
//    }
//
////    @Test
////    public void testSelectOne() throws Exception{
////        // given
////        BoardReply reply = new BoardReply();
////        reply.setContent("모여라하~");
////        reply.setMemberId("kii");
////        reply.setRegDate("2018-04-12");
////        reply.setEditDate("2018-04-12");
////        reply.setBoardNo(1);
////
////        int replyNo = boardReplyDao.insert(reply);
////
////        System.out.println(replyNo);
////
////        // when
////        BoardReply reply2 = boardReplyDao.selectReply(replyNo);
////
////        // then
////        Assert.assertEquals(reply.getContent(), reply2.getContent());
//////        Assert.assertEquals(school.getCategory(), school1.getCategory());
////
////    }
//
//}
