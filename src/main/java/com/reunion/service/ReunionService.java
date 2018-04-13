package com.reunion.service;

import com.reunion.domain.Condition;
import com.reunion.domain.Reunion;

import java.util.List;

public interface ReunionService {

    public List<Reunion> listReunion(Condition condition) throws Exception;

    public int listCnt(Condition condition) throws Exception;

    public Reunion detailReunion(String reunionNo) throws Exception;

    public int updateReunion(Reunion reunion) throws Exception;

    public int deleteReunion(Reunion reunionNo) throws Exception;

    public int writeReunion(Reunion reunion) throws Exception;

}
