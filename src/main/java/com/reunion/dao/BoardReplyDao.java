package com.reunion.dao;

import com.reunion.domain.BoardReply;
import com.reunion.domain.Member;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class BoardReplyDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<BoardReply> rowMapper = BeanPropertyRowMapper.newInstance(BoardReply.class);

    public BoardReplyDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board_reply")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<BoardReply> selectAll() {
        return jdbc.query("SELECT no AS no ,content AS content ,member_id AS id ,board_no AS boardNo, " +
                "reg_date AS regDate ,edit_date AS editDate FROM board_reply order by no desc", Collections.emptyMap(), rowMapper);
    }
}

/*
CREATE TABLE board_reply(
  no     	    INTEGER NOT NULL auto_increment COMMENT '댓글 번호',
  content 	    VARCHAR(500) NOT NULL COMMENT '댓글 내용',
  member_id     VARCHAR(50) NOT NULL COMMENT '아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  board_no      INTEGER NOT NULL COMMENT '게시글 번호',
  PRIMARY KEY  (no),
  FOREIGN KEY (board_no) REFERENCES board (no)
);
 */
