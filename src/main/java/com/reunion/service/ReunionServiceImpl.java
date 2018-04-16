package com.reunion.service;

import com.reunion.dao.BoardReplyDao;
import com.reunion.dao.FileDao;
import com.reunion.dao.ReunionDao;
import com.reunion.domain.Condition;
import com.reunion.domain.FileVO;
import com.reunion.domain.Reunion;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReunionServiceImpl implements ReunionService{

    @Autowired
    ReunionDao reunionDao;

    @Autowired
    FileDao fileDao;

    @Autowired
    BoardReplyDao boardReplyDao;

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
    public int updateReunion(Reunion reunion, MultipartFile[] files)  throws Exception{

        //file.getBytes() // 사용금지
        @Cleanup InputStream in = null;
        @Cleanup FileOutputStream out = null;
        try {
            for (int i = 0; i < files.length; i++) {
                if(!files[i].isEmpty()){

                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1; // 월은 0부터 시작
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    // 윈도우의 경우엔 디렉토리 구분자가 \
                    // unix계열은 디렉토리 구분자가 /
                    // File.separator 를 이용하여 디렉토리를 구분한다.
                    StringBuffer sb = new StringBuffer("/Users/mac/tmp/reunion/");
                    sb.append(year);
                    sb.append("/");
                    sb.append(month);
                    sb.append("/");
                    sb.append(day);
                    sb.append("/");
                    String dir = sb.toString();

                    File fileObj = new File(dir);
                    if(!fileObj.exists()){ // 해당 디렉토리가 존재하지 않는다면
                        fileObj.mkdirs(); // 하위 폴더까지 생성한다.
                    }

                    // 본래 파일명
                    String originalfileName = files[i].getOriginalFilename();
                    //확장자
                    String originalFileExtension = originalfileName.substring(originalfileName.lastIndexOf("."));

                    UUID uuid = UUID.randomUUID(); // 파일 중복명 처리
                    String saveFileName = uuid.toString();  // 임시 파일명
                    String saveFilePath = dir + saveFileName + originalFileExtension; // 디렉토리 + 파일명

                    in = files[i].getInputStream();
                    out = new FileOutputStream(saveFilePath);
                    byte[] buffer = new byte[1024];
                    int readCount = 0;
                    // 만약 파일길이가 1026 (1024, 2)
                    while((readCount = in.read(buffer)) != -1){ // -1(EoF)
                        out.write(buffer, 0, readCount);
                    }

                    //확장자 추가
                    saveFileName = saveFileName + originalFileExtension;

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
                    fileVO.setBoardNo(reunion.getNo());
                    fileVO.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
                    fileDao.fileUpload(fileVO);
                }
            }

        }catch(Exception ex){
            throw new RuntimeException(); //@Transactional
        }
        return reunionDao.updateReunion(reunion);
    }

    @Override
    @Transactional
    public int deleteReunion(Reunion reunion) throws Exception {
        //댓글 삭제
        boardReplyDao.deleteAll(Integer.parseInt(reunion.getNo()));
        //파일 삭제
        List<FileVO> fileList = fileDao.fileReunion(reunion.getNo());
        for(int i =0; i < fileList.size(); i++){
            fileDelete(fileList.get(i).getNo());
        }

        return reunionDao.deleteReunion(reunion);
    }

    @Override
    @Transactional
    public int writeReunion(Reunion reunion, MultipartFile[] files) throws Exception {
        reunion.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
        reunion.setEditDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
        int reunionNo = reunionDao.writeReunion(reunion);

        //file.getBytes() // 사용금지
        @Cleanup InputStream in = null;
        @Cleanup FileOutputStream out = null;
        try {
            for (int i = 0; i < files.length; i++) {
                if(!files[i].isEmpty()){

                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1; // 월은 0부터 시작
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    // 윈도우의 경우엔 디렉토리 구분자가 \
                    // unix계열은 디렉토리 구분자가 /
                    // File.separator 를 이용하여 디렉토리를 구분한다.
                    StringBuffer sb = new StringBuffer("/Users/mac/tmp/reunion/");
                    sb.append(year);
                    sb.append("/");
                    sb.append(month);
                    sb.append("/");
                    sb.append(day);
                    sb.append("/");
                    String dir = sb.toString();

                    File fileObj = new File(dir);
                    if(!fileObj.exists()){ // 해당 디렉토리가 존재하지 않는다면
                        fileObj.mkdirs(); // 하위 폴더까지 생성한다.
                    }

                    // 본래 파일명
                    String originalfileName = files[i].getOriginalFilename();
                    //확장자
                    String originalFileExtension = originalfileName.substring(originalfileName.lastIndexOf("."));

                    UUID uuid = UUID.randomUUID(); // 파일 중복명 처리
                    String saveFileName = uuid.toString();  // 임시 파일명
                    String saveFilePath = dir + saveFileName + originalFileExtension; // 디렉토리 + 파일명

                    in = files[i].getInputStream();
                    out = new FileOutputStream(saveFilePath);
                    byte[] buffer = new byte[1024];
                    int readCount = 0;
                    // 만약 파일길이가 1026 (1024, 2)
                    while((readCount = in.read(buffer)) != -1){ // -1(EoF)
                        out.write(buffer, 0, readCount);
                    }

                    //확장자 추가
                    saveFileName = saveFileName + originalFileExtension;

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
            throw new RuntimeException(); //@Transactional
        }
        return reunionNo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileVO> fileReunion(String reunionNo) throws Exception {
        return fileDao.fileReunion(reunionNo);
    }

    @Override
    @Transactional(readOnly = true)
    public FileVO fileDetail(String fileNo) throws Exception {
        return fileDao.fileDetail(fileNo);
    }

    @Override
    @Transactional
    public boolean fileDelete(String fileNo) throws Exception {

        try{

            FileVO fileVO = fileDao.fileDetail(fileNo);
            String path = fileVO.getPath() + fileVO.getTempName();

            File file = new File(path);
            if(!file.exists()){
                return false;
            }else{
                fileDao.fileDelete(fileNo);
                return file.delete();
            }

        }catch(Exception ex){
            throw new RuntimeException("file delete Error");
        }finally { }

    }
}
