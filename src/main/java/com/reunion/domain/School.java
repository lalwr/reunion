package com.reunion.domain;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@NoArgsConstructor //디폴트 생성자를 생성
public class School {
    private int no;
    private String name;
    private String category;
    private String regDate;
}