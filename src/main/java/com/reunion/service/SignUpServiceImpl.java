package com.reunion.service;

import com.reunion.dao.ProfPicDao;
import com.reunion.dao.SchoolDao;
import com.reunion.domain.Member;
import com.reunion.domain.MemberSchool;
import com.reunion.domain.ProfPicDTO;
import com.reunion.security.ShaEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.reunion.dao.MemberDao;
import com.reunion.dao.MemberSchoolDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Autowired
    MemberSchoolDao memberSchoolDao;
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    ProfPicDao profPicDao;

    @Override
    @Transactional
    public void signUp(String id,String name,String password,String school) {
        Date date = new Date();
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        member.setRegDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        member.setEditDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        int memberNo = memberDao.insertMember(member);
        int schoolNo = schoolDao.selectSchool(school).getNo();
        MemberSchool ms = new MemberSchool();
        ms.setMemberNo(memberNo);
        ms.setSchoolNo(schoolNo);
        memberSchoolDao.insertMemberSchool(ms);
    }

    @Override
    @Transactional
    public void update(String memberId, String password,String school) {
        Member member = memberDao.selectMember(memberId);
        member.setPassword(ShaEncoding.cryptedPwd(password));
        member.setEditDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        int memberNo = memberDao.updateMember(member);
        int schoolNo = schoolDao.selectSchool(school).getNo();
        MemberSchool ms = memberSchoolDao.selectMemberSchool(member.getNo());
        ms.setSchoolNo(schoolNo);
        memberSchoolDao.updateMemberSchool(ms);
    }

    @Override
    @Transactional
    public void delete(String memberId) {
        Member member = memberDao.selectMember(memberId);
        memberSchoolDao.deleteMemberSchool(member.getNo());
        memberDao.deleteMember(member.getNo());
    }

    @Override
    @Transactional
    public ProfPicDTO getProfile(int memberNo){
        return profPicDao.profileDetail(memberNo);
    }
    @Override
    @Transactional
    public void profile(String memberId, MultipartFile file){
        // 중복된 파일 문제를 해결.
        // 외부에서는 직접 접근하면 안된다. ex> jsp를 업로드 실행할 수 없는 경로 (외부에서 접근하지 못하는 경로)
        // 하나의 폴더에 너무 많은 파일이 저장되면 관리하기 어렵다.
        // /tmp/helloboard 에 저장하도록 한다.
        // /tmp/helloboard/년/월/일/uuid
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // 월은 0부터 시작
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // 윈도우의 경우엔 디렉토리 구분자가 \
        // unix계열은 디렉토리 구분자가 /
        // File.separator 를 이용하여 디렉토리를 구분한다.
        StringBuffer sb = new StringBuffer("/Users/life4honor/Downloads/profPic/");
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

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString();  // 파일명
        String saveFilePath = dir + saveFileName; // 디렉토리 + 파일명

        //file.getBytes() // 망하자.
        InputStream in = null;
        FileOutputStream out = null;
        try {
            in = file.getInputStream();
            out = new FileOutputStream(saveFilePath);
            byte[] buffer = new byte[1024];
            int readCount = 0;
            // 만약 파일길이가 1026
            // 1024
            // 2
            while((readCount = in.read(buffer)) != -1){ // -1(EoF)
                out.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
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


        //db 에는 다음과 같은 내용이 저장될 것이다.
        // 사용자 id, 사진파일,
        // 파일 id, 사용자id, 파일의 오리지날이름, 파일의 실제 저장경로, 파일길이, 파일type
        Member member = memberDao.selectMember(memberId);
        String originFileName = file.getOriginalFilename();
        ProfPicDTO profPicDTO = new ProfPicDTO();
        profPicDTO.setMemberNo(member.getNo());
        profPicDTO.setName(originFileName);
        profPicDTO.setTempName(saveFileName);
        profPicDTO.setFileSize(file.getSize());
        profPicDTO.setPath(dir);
        profPicDTO.setFormat(file.getContentType());
        profPicDTO.setRegDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(new Date()));
        profPicDao.upload(profPicDTO);
    }
}
