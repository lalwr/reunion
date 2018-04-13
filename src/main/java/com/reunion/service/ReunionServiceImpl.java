package com.reunion.service;

import com.reunion.dao.ReunionDao;
import com.reunion.domain.Condition;
import com.reunion.domain.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReunionServiceImpl implements ReunionService{

    @Autowired
    ReunionDao reunionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Reunion> listReunion(Condition condition) throws Exception{
        return reunionDao.listReunion(condition);
    }

    @Override
    @Transactional(readOnly = true)
    public int listCnt(Condition conditionn) throws Exception {
        return reunionDao.listCnt(conditionn);
    }

    @Override
    @Transactional(readOnly = true)
    public Reunion detailReunion(String reunionNo)  throws Exception{
        return reunionDao.detailReunion(reunionNo);
    }

    @Override
    @Transactional
    public int updateReunion(Reunion reunion)  throws Exception{
        return reunionDao.updateReunion(reunion);
    }

    @Override
    @Transactional
    public int deleteReunion(Reunion reunion) throws Exception {
        return reunionDao.deleteReunion(reunion);
    }

    @Override
    @Transactional
    public int writeReunion(Reunion reunion) throws Exception {
        return reunionDao.writeReunion(reunion);
    }
}
