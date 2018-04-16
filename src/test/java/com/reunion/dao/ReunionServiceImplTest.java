package com.reunion.dao;

import com.reunion.config.DbConfig;
import com.reunion.domain.Condition;
import com.reunion.domain.Reunion;
import com.reunion.service.ReunionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Transactional
//@Test annotation과 함께 설정된 @Transactional은 항상 rollback된다.
public class ReunionServiceImplTest {

    @Mock
    ReunionDao reunionDao;

    @InjectMocks
    ReunionServiceImpl reunionServiceImpl;

    @Mock
    List<Reunion> list;

    @Mock
    Condition condition;

    @Mock
    Reunion reunion;

    @Test
    public void testListReunion() throws Exception{
        when(reunionDao.listReunion(condition)).thenReturn(list);
        when(reunionDao.listCnt(condition)).thenReturn(1);

        List<Reunion> reunionList = reunionServiceImpl.listReunion(condition);
        int cnt = reunionServiceImpl.listCnt(condition);

        Assert.assertEquals(reunionList, list);
        Assert.assertEquals(cnt, 1);
    }

    @Test
    public void testWriteReunion() throws Exception{
        when(reunionDao.writeReunion(reunion)).thenReturn(1);
       // int reunionWrite = reunionServiceImpl.writeReunion(reunion);

        //Assert.assertEquals(reunionWrite, 1);
    }

    @Test
    public void testUpdateReunion() throws Exception{
        when(reunionDao.updateReunion(reunion)).thenReturn(1);

        //int reunionUpdate = reunionServiceImpl.updateReunion(reunion);

        //Assert.assertEquals(reunionUpdate, 1);
    }

    @Test
    public void testdDetailReunion() throws Exception{
        when(reunionDao.detailReunion("1")).thenReturn(reunion);

        Reunion reunionDetail = reunionServiceImpl.detailReunion("1");

        Assert.assertEquals(reunionDetail, reunion);
    }

    @Test
    public void testdDeleteReunion() throws Exception{
        when(reunionDao.deleteReunion(reunion)).thenReturn(1);

        int reunionDelete = reunionServiceImpl.deleteReunion(reunion);

        Assert.assertEquals(reunionDelete, 1);
    }


}
