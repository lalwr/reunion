package com.reunion.dao;

import com.reunion.domain.BoardReply;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BoardReplyDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<BoardReply> rowMapper = BeanPropertyRowMapper.newInstance(BoardReply.class);

//    public BoardReplyDao(){}

    public BoardReplyDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board_reply")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<BoardReply> list() throws Exception{
        return jdbc.query("SELECT no AS no ,content AS content ,member_id AS memberId ,board_no AS boardNo, " +
                "reg_date AS regDate ,edit_date AS editDate FROM board_reply order by no desc", Collections.emptyMap(), rowMapper);
    }
//    public List<BoardReply> list(int boardNo) throws Exception{
//        Map<String, ?> params = Collections.singletonMap("boardNo", boardNo);
//
//        return jdbc.query("SELECT no AS no ,content AS content ,member_id AS memberId ,board_no AS boardNo, " +
//            "reg_date AS regDate ,edit_date AS editDate FROM board_reply where board_no=:boardNo order by no desc", Collections.emptyMap(), rowMapper);
//}

    public int insert(BoardReply reply){
        SqlParameterSource params = new BeanPropertySqlParameterSource(reply);
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO board_reply(content, member_id, board_no, reg_date, edit_date) ");
        sql.append("VALUES( :content, :memberId, :boardNo, now(), now())");

        return jdbc.update(sql.toString(), params);
    }

    public int delete(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);

        return jdbc.update("delete from board_reply where no = :no ", params);
    }

    public int update(BoardReply boardReply){
        SqlParameterSource params = new BeanPropertySqlParameterSource(boardReply);
        return jdbc.update("update board_reply set content = :content, " +
                "edit_date = now() where  no = :no", params);
    }

    public BoardReply selectOne(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);

        try {
            BoardReply reply = jdbc.queryForObject("select content from board_reply where no = :no", params, rowMapper);
            return reply;
        }catch(DataAccessException e){
            return null;
        }
    }

}


