package com.reunion.service;

import com.reunion.dao.ReunionDao;
import com.reunion.domain.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReunionService {

    @Autowired
    ReunionDao reunionDao;

    public List<Reunion> list(Reunion reunion) throws Exception{
        return reunionDao.list(reunion);
    }

    public Reunion content(Reunion reunion)  throws Exception{
        return reunionDao.content(reunion);
    }
}
