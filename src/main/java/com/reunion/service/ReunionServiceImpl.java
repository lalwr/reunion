package com.reunion.service;

import com.reunion.dao.FileDao;
import com.reunion.dao.ReunionDao;
import com.reunion.domain.Condition;
import com.reunion.domain.FileVO;
import com.reunion.domain.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class ReunionServiceImpl implements ReunionService{

    @Autowired
    ReunionDao reunionDao;

    @Autowired
    FileDao fileDao;

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
    public int writeReunion(Reunion reunion, MultipartFile[] files) throws Exception {
        reunion.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
        reunion.setEditDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
        int reunionNo = reunionDao.writeReunion(reunion);

        String dir = "/Users/mac/tmp/";

        File fileObj = new File(dir);
        if(!fileObj.exists()){ // 해당 디렉토리가 존재하지 않는다면
            fileObj.mkdirs(); // 하위 폴더까지 생성한다.
        }

        //file.getBytes() // 사용금지
        InputStream in = null;
        FileOutputStream out = null;
        try {
            for (int i = 0; i < files.length; i++) {
                if(!files[i].isEmpty()){
                    UUID uuid = UUID.randomUUID(); // 파일 중복명 처리
                    String saveFileName = uuid.toString();  // 임시 파일명
                    String saveFilePath = dir + saveFileName; // 디렉토리 + 파일명

                    in = files[i].getInputStream();
                    out = new FileOutputStream(saveFilePath);
                    byte[] buffer = new byte[1024];
                    int readCount = 0;
                    // 만약 파일길이가 1026 (1024, 2)
                    while((readCount = in.read(buffer)) != -1){ // -1(EoF)
                        out.write(buffer, 0, readCount);
                    }

                    // 본래 파일명
                    String originalfileName = files[i].getOriginalFilename();

                    long fileSize = files[i].getSize(); // 파일 사이즈

                    String fileType = files[i].getContentType();

                    //db 에는 다음과 같은 내용이 저장될 것이다.
                    // 게시물 id, 글쓴이 id, title, 내용,
                    // 게시물id, 파일의 오리지날이름, 파일의 저장이름 , 파일저장 경로, 파일길이, 파일type
                    FileVO fileVO = new FileVO();
                    fileVO.setName(originalfileName);
                    fileVO.setTempName(saveFileName);
                    fileVO.setFileSize(String.valueOf(fileSize));
                    fileVO.setPath(dir);
                    fileVO.setFormat(fileType);
                    fileVO.setBoardNo(String.valueOf(reunionNo));
                    fileVO.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
                    fileDao.fileUpload(fileVO);
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(); //@Transactional
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reunionNo;
    }
}
