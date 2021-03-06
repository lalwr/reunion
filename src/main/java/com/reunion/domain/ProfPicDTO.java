package com.reunion.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@NoArgsConstructor //디폴트 생성자를 생성
public class ProfPicDTO {
    private String no;
    private String name;
    private String tempName;
    private Long fileSize;
    private String path;
    private String format;
    private String regDate;
    private int memberNo;
}
