package com.reunion.service;

import com.reunion.dao.InfoDao;
import com.reunion.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfoServiceImpl implements  InfoService {

    @Autowired
    InfoDao infoDao;

    @Override
    @Transactional(readOnly = true)
    public Info showInfo(String memberId) {
        return infoDao.showInfo(memberId);
    }
}
