package com.reunion.service;

import com.reunion.domain.ProfPicDTO;
import org.springframework.web.multipart.MultipartFile;


public interface SignUpService {
    public void signUp(String memberId,String name,String password,String school);
    public void update(String memberId, String password,String school);
    public void delete(String memberId);
    public void profile(String memberId, MultipartFile file);
    public ProfPicDTO getProfile(int memberNo);
}
