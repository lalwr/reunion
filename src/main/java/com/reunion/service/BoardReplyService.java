package com.reunion.service;

import com.reunion.domain.BoardReply;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardReplyService {
    public List<BoardReply> list(int boardno) throws Exception;

    public int insert(BoardReply reply)  throws Exception;

    public int delete(BoardReply reply) throws Exception;

    public int update(BoardReply reply) throws Exception;

    public BoardReply selectContent(int no) throws Exception;
}
