package com.reunion.dao;

import com.reunion.config.DbConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DbConfig.class)
@Transactional
public class BoardReplyDaoTest {
    @Autowired
    DataSource dataSource;
    BoardReplyDao boardReplyDao;

    @Before
    public void setUp() {
        boardReplyDao = new BoardReplyDao(dataSource);
    }

    @Test
    public void config() throws Exception{
        Assert.assertTrue(true);
    }

    

}
