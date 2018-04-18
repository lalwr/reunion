package com.reunion.dao;

import com.reunion.domain.Info;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@Repository
public class InfoDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Info> rowMapper = BeanPropertyRowMapper.newInstance(Info.class);

    public InfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }
    public Info showInfo(String id){
        Map<String, ?> params = Collections.singletonMap("id", id);
        try{
            return jdbc.queryForObject("SELECT m.no, m.id, m.name, s.name as schoolname from member AS m LEFT JOIN (member_school AS ms,school AS s) on (m.no=ms.member_no AND ms.school_no=s.no) where m.id =:id", params, rowMapper);
        }catch (Exception e){
            return null;
        }
    }

}
