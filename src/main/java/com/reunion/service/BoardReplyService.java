package com.reunion.service;

import com.reunion.dao.BoardReplyDao;
import com.reunion.domain.BoardReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardReplyService {
    @Autowired
    BoardReplyDao boardReplyDao;

    @Transactional(readOnly = true)
    public List<BoardReply> list(int boardno) throws Exception{
        return boardReplyDao.list(boardno);
    }

    @Transactional
    public int insert(BoardReply reply)  throws Exception{
        int roleId = boardReplyDao.insert(reply);
        return roleId;
    }

    @Transactional
    public int delete(BoardReply reply) throws Exception {
        return boardReplyDao.delete(reply);
    }

    @Transactional
    public int update(BoardReply reply) throws Exception{
        return boardReplyDao.update(reply);
    }

    @Transactional
    public BoardReply selectContent(int no) throws Exception{
        return boardReplyDao.selectOne(no);
    }

    @Transactional
    public int deleteAll(int no) throws Exception {
        return boardReplyDao.deleteAll(no);
    }
}
