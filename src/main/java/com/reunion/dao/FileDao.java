package com.reunion.dao;

import com.reunion.domain.FileVO;
import com.reunion.domain.Reunion;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
public class FileDao {

    // Spring JDBC는 기존 JDBC프로그래밍을 아주 간단하게 할 수 있도록 도와준다. ex) :필드명 으로 바인딩이 가능
    // JdbcTemplate , NamedParameterJdbcTemplate 이 핵심객체이다.
    private NamedParameterJdbcTemplate jdbc;
    // insert를 쉽게 도와주는 객체이다.
    private SimpleJdbcInsert insertAction;
    // RowMapper는 한건의 record를 쉽게 객체에 담아줄 수 있도록 도와준다.
    // jdbc에서 ResultSet으로부터 칼럼별로 값을 읽어오는 것을 도와주는 것.
    // Role클래스가 가지는 프로퍼티는 roleId, description이다.
    // ex) reg_date, edit_date 칼럼의 값을 regDate, editDate 프로퍼티에 설정한다.
    private BeanPropertyRowMapper<FileVO> rowMapper = BeanPropertyRowMapper.newInstance(FileVO.class);

    //spring에 종속족이지 않기 위해
    public FileDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("file")
                .usingGeneratedKeyColumns("no"); // 자동으로 id가 생성될 경우

    }

    public int fileUpload(FileVO fileVO) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(fileVO);
        return insertAction.executeAndReturnKey(params).intValue();
    }

    public List<FileVO> fileReunion(String reunionNo) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT no as no, name AS name, temp_name AS tempName, file_size AS fileSize, path AS path, format AS format ");
        sql.append(", reg_date AS reg_date, board_no AS boardNo ");
        sql.append("FROM file ");
        sql.append("WHERE board_no = :reunionNo");
        Map<String, ?> params = Collections.singletonMap("reunionNo", reunionNo);
        try{
            return jdbc.query(sql.toString(), params, rowMapper);
        }catch (Exception e){
            return null;
        }
    }

    public FileVO fileDownload(String fileNo) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT no as no, name AS name, temp_name AS tempName, file_size AS fileSize, path AS path, format AS format ");
        sql.append(", reg_date AS reg_date, board_no AS boardNo ");
        sql.append("FROM file ");
        sql.append("WHERE no = :fileNo");
        Map<String, ?> params = Collections.singletonMap("fileNo", fileNo);
        try {
            // 결과가 없으면 Exception이 발생한다.
            FileVO fileVO = jdbc.queryForObject( sql.toString() ,params, rowMapper);
            return fileVO;
        }catch(DataAccessException e){
            return null;
        }
    }

    public int fileDelete(String fileNo) {
        Map<String, ?> params = Collections.singletonMap("fileNo", fileNo);
        return jdbc.update("DELETE FROM file WHERE no = :fileNo", params);
    }
}
