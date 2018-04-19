package com.reunion.dao;

import com.reunion.domain.Condition;
import com.reunion.domain.Reunion;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ReunionDao {
    // Spring JDBC는 기존 JDBC프로그래밍을 아주 간단하게 할 수 있도록 도와준다. ex) :필드명 으로 바인딩이 가능
    // JdbcTemplate , NamedParameterJdbcTemplate 이 핵심객체이다.
    private NamedParameterJdbcTemplate jdbc;
    // insert를 쉽게 도와주는 객체이다.
    private SimpleJdbcInsert insertAction;
    // RowMapper는 한건의 record를 쉽게 객체에 담아줄 수 있도록 도와준다.
    // jdbc에서 ResultSet으로부터 칼럼별로 값을 읽어오는 것을 도와주는 것.
    // Role클래스가 가지는 프로퍼티는 roleId, description이다.
    // ex) reg_date, edit_date 칼럼의 값을 regDate, editDate 프로퍼티에 설정한다.
    private BeanPropertyRowMapper<Reunion> rowMapper = BeanPropertyRowMapper.newInstance(Reunion.class);

    //spring에 종속족이지 않기 위해
    public ReunionDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<Reunion> listReunion(Condition condition) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT no AS no ,subject AS subject ,content AS content ,reg_id AS regId ,DATE_FORMAT(reg_Date,'%Y년%m월%d일 %H시%i분%S초') AS regDate,");
        sql.append("DATE_FORMAT(edit_date,'%Y년%m월%d일 %H시%i분%S초') AS editDate ,school_no AS schoolNo ,category_no AS categoryNo FROM board ");
        if(condition.getSearchType() != null && condition.getSearchType() != ""){
            if(condition.getSearchType().equals("subject")){
                sql.append("WHERE subject LIKE '%" + condition.getSearchText() + "%' ");
            }else if(condition.getSearchType().equals("content")){
                sql.append("WHERE content LIKE '%" + condition.getSearchText() + "%' ");
            }
        }
        sql.append("order by no desc LIMIT :pageVal , :pageSize");
        SqlParameterSource params = new BeanPropertySqlParameterSource(condition);
        return jdbc.query( sql.toString() , params, rowMapper);
    }

    public int listCnt(Condition condition) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(*) FROM board ");
        if(condition.getSearchType() != null && condition.getSearchType() != ""){
            if(condition.getSearchType().equals("subject")){
                sql.append("WHERE subject LIKE '%" + condition.getSearchText() + "%' ");
            }else if(condition.getSearchType().equals("content")){
                sql.append("WHERE content LIKE '%" + condition.getSearchText() + "%' ");
            }
        }
        SqlParameterSource params = new BeanPropertySqlParameterSource(condition);
        return jdbc.queryForObject(sql.toString(), params, Integer.class);
    }

    public Reunion detailReunion(String reunionNo) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT no AS no ,subject AS subject ,content AS content ,reg_id AS regId , DATE_FORMAT(reg_Date,'%Y년%m월%d일 %H시%i분%S초') AS regDate,");
        sql.append("DATE_FORMAT(edit_date,'%Y년%m월%d일 %H시%i분%S초') AS editDate , school_no AS schoolNo ,category_no AS categoryNo FROM board where no = :no");
        Map<String, ?> params = Collections.singletonMap("no", reunionNo);
        try {
            // 결과가 없으면 Exception이 발생한다.
            Reunion reunion = jdbc.queryForObject( sql.toString() ,params, rowMapper);
            return reunion;
        }catch(DataAccessException e){
            return null;
        }
    }

    public int updateReunion(Reunion reunion) throws Exception{
        SqlParameterSource params = new BeanPropertySqlParameterSource(reunion);
        return jdbc.update("update board set subject = :subject, content = :content, edit_date = now() where no = :no", params);
    }

    public int deleteReunion(Reunion reunion) throws Exception{
        SqlParameterSource params = new BeanPropertySqlParameterSource(reunion);
        return jdbc.update("delete from board where no = :no", params);
    }

    public int writeReunion(Reunion reunion) throws Exception{
        SqlParameterSource params = new BeanPropertySqlParameterSource(reunion);
        // 자동으로 id를 생성할 경우에는 아래와 같이 생성된 pk를 반환할 수 있다.
        return insertAction.executeAndReturnKey(params).intValue();
    }
}
