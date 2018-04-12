package com.reunion.domain;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@NoArgsConstructor //디폴트 생성자를 생성
public class BoardReply {
    int no;
    String content;
    String memberId;
    String regDate;
    String editDate;
    int bardNo;
}


/*
no     	    INTEGER NOT NULL auto_increment COMMENT '댓글 번호',
  content 	    VARCHAR(500) NOT NULL COMMENT '댓글 내용',
  member_id     VARCHAR(50) NOT NULL COMMENT '아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  board_no      INTEGER NOT NULL COMMENT '게시글 번호',
  PRIMARY KEY  (no),
 */