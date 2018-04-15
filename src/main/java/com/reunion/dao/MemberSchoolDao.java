package com.reunion.dao;

import com.reunion.domain.MemberSchool;
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
public class MemberSchoolDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<MemberSchool> rowMapper = BeanPropertyRowMapper.newInstance(MemberSchool.class);

    public MemberSchoolDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member_school");
//                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<MemberSchool> selectAll() {
        return jdbc.query("SELECT member_no, school_no FROM member_school order by member_no", Collections.emptyMap(), rowMapper);
    }

    public int insertMemberSchool(MemberSchool ms){
        SqlParameterSource params = new BeanPropertySqlParameterSource(ms);
        // 자동으로 id를 생성할 경우에는 아래와 같이 생성된 pk를 반환할 수 있다.
        int num = insertAction.execute(params);
        return num;
    }

    public int deleteMemberSchool(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);
        return jdbc.update("delete from member_school where member_no = :no", params);
    }

    public int updateMemberSchool(MemberSchool ms){
        SqlParameterSource params = new BeanPropertySqlParameterSource(ms);
        return jdbc.update("update member_school set member_no = :memberNo, school_no = :schoolNo where member_no = :memberNo", params);
    }

    public MemberSchool selectMemberSchool(int memberNo){
        Map<String, ?> params = Collections.singletonMap("memberNo", memberNo);
        return jdbc.queryForObject("SELECT member_no, school_no FROM member_school WHERE member_no = :memberNo", params, rowMapper);
    }
}
