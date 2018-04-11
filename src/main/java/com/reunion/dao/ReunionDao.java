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
public class ReunionDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private BeanPropertyRowMapper<Reunion> rowMapper = BeanPropertyRowMapper.newInstance(Reunion.class);

    public ReunionDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우
    }

    public List<Reunion> list(Reunion reunion) throws Exception{
        SqlParameterSource params = new BeanPropertySqlParameterSource(reunion);
        return jdbc.query("SELECT no AS no ,subject AS subject ,content AS content ,reg_id AS regId ," +
                "reg_date AS regDate, edit_date AS editDate ,school_no AS schoolNo ,category_no AS categoryNo FROM board order by no desc",
                params, rowMapper);
    }

    public Reunion content(Reunion reunion) throws Exception{
        SqlParameterSource params = new BeanPropertySqlParameterSource(reunion);
        return jdbc.queryForObject("SELECT no AS no ,subject AS subject ,content AS content ,reg_id AS regId ," +
                "reg_date AS regDate, edit_date AS editDate ,school_no AS schoolNo ,category_no AS categoryNo FROM board WHERE no = :no", params, rowMapper);
    }

    public int update(Reunion reunion) throws Exception{
        SqlParameterSource params = new BeanPropertySqlParameterSource(reunion);
        return jdbc.update("update board set subject = :subject, content = :content, edit_date = now() where no = :no", params);
    }
}
