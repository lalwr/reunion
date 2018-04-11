package com.reunion.dao;

import com.reunion.domain.School;
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
public class SchoolDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<School> rowMapper = BeanPropertyRowMapper.newInstance(School.class);

    public SchoolDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("school")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<School> selectAll() {
        return jdbc.query("SELECT no, name, category, reg_date FROM school order by no", Collections.emptyMap(), rowMapper);
    }

    //test 다시.
    public int insertSchool(School school){
        SqlParameterSource params = new BeanPropertySqlParameterSource(school);
        // 자동으로 id를 생성할 경우에는 아래와 같이 생성된 pk를 반환할 수 있다.
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public int deleteSchool(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);
        return jdbc.update("delete from school where no = :no", params);
    }

    public int updateSchool(School school){
        SqlParameterSource params = new BeanPropertySqlParameterSource(school);
        return jdbc.update("update school set name = :name, category = :category, reg_date = :regDate where no = :no", params);
    }

    public School selectSchool(int no){
        Map<String, ?> params = Collections.singletonMap("no", no);
        return jdbc.queryForObject("SELECT no, name, category, reg_date FROM school WHERE no = :no", params, rowMapper);
    }
}
