package com.reunion.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@NoArgsConstructor //디폴트 생성자를 생성
public class Reunion {
    private String no;
    private String subject;
    private String content;
    private String regId;
    private String regDate;
    private String editDate;
    private String schoolNo;
    private String categoryNo;
}

