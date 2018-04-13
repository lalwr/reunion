package com.reunion.service;

import com.reunion.dao.BoardReplyDao;
import com.reunion.domain.BoardReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardReplyService {
    @Autowired
    BoardReplyDao boardReplyDao;

    public List<BoardReply> list(BoardReply reply) throws Exception{
        return boardReplyDao.list();
    }
}
