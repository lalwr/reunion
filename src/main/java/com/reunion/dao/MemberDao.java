package com.reunion.dao;

import com.reunion.domain.Member;
import com.reunion.domain.Reunion;
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
public class MemberDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);

    public MemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<Member> selectAll() {
        return jdbc.query("SELECT no AS no ,id AS id ,password AS password ,name AS name ," +
                "reg_date AS regDate ,edit_date AS editDate FROM member order by no desc", Collections.emptyMap(), rowMapper);
    }

    //test 다시.
    public int insertReunion(Member member){
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        // 자동으로 id를 생성할 경우에는 아래와 같이 생성된 pk를 반환할 수 있다.
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public int deleteReunion(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);
        return jdbc.update("delete from member where no = :no", params);
    }

    public int updateReunion(Member member){
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        return jdbc.update("update member set password = :password, name = :name, edit_date = :editDate where no = :no", params);
    }

    public Member selectReunion(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);
        return jdbc.queryForObject("SELECT no AS no ,id AS id ,password AS password ,name AS name ,reg_date AS regDate ," +
                "edit_date AS editDate FROM member WHERE no = :no", params, rowMapper);
    }

    public void list(Reunion reunion) {
    }
}
