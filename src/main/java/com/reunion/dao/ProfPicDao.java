package com.reunion.dao;

import com.reunion.domain.ProfPicDTO;
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
import java.util.Map;

@Repository
public class ProfPicDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<ProfPicDTO> rowMapper = BeanPropertyRowMapper.newInstance(ProfPicDTO.class);

    public ProfPicDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("profile")
                .usingGeneratedKeyColumns("no");
    }

    public int upload(ProfPicDTO profPicDTO) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(profPicDTO);
        return insertAction.executeAndReturnKey(params).intValue();
    }
    public ProfPicDTO profileDetail(int memberNo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select no, name, temp_name, file_size, path, format, reg_date, member_no from profile where member_no = :memberNo");
        Map<String, ?> params = Collections.singletonMap("memberNo", memberNo);
        try {
            // 결과가 없으면 Exception이 발생한다.
            ProfPicDTO profPicDTO = jdbc.queryForObject( sql.toString() ,params, rowMapper);
            return profPicDTO;
        }catch(DataAccessException e){
            return null;
        }
    }
}


