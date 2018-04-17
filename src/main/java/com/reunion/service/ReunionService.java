package com.reunion.service;

import com.reunion.domain.Condition;
import com.reunion.domain.FileVO;
import com.reunion.domain.Reunion;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ReunionService {

    public List<Reunion> listReunion(Condition condition) throws Exception;

    public int listCnt(Condition condition) throws Exception;

    public Reunion detailReunion(String reunionNo) throws Exception;

    public int updateReunion(Reunion reunion, MultipartFile[] files) throws Exception;

    public int deleteReunion(Reunion reunionNo) throws Exception;

    public int writeReunion(Reunion reunion, MultipartFile[] files) throws Exception;

    public List<FileVO> fileReunion(String reunionNo) throws Exception;

    public FileVO fileDetail(String fileNo) throws Exception;

    public boolean fileDelete(String fileNo) throws Exception;
}
