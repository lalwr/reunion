# Database 생성
CREATE DATABASE reunion;
# 원격으로도 접속가능
GRANT ALL PRIVILEGES ON reunion.* TO fastcamp@'%' IDENTIFIED BY 'fastcamp';
# 로컬로만 접속가능
GRANT ALL PRIVILEGES ON reunion.* TO fastcamp@'localhost' IDENTIFIED BY 'fastcamp';
# DB에 여태까지 실행한 명령여 적용
FLUSH PRIVILEGES ;

USE reunion;

-- creates some test-tables and data
-- DROP TABLE EMPLOYEE;

CREATE TABLE board(
  board_no      INTEGER NOT NULL auto_increment COMMENT '게시글 번호',
  subejct       VARCHAR(50) NOT NULL COMMENT '제목',
  img_name      VARCHAR(200) NULL  COMMENT '이미지명',
  content       VARCHAR(500) NOT NULL COMMENT '내용',
  reg_id        VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  school_no		VARCHAR(15) NOT NULL COMMENT '학교 번호',
  category_no   INTEGER NOT NULL COMMENT '게시판 카테고리 번호', 
  PRIMARY KEY  (board_no),
  FOREIGN KEY (school_no) REFERENCES school (school_no),
  FOREIGN KEY (category) REFERENCES board_category (category_no)
);

CREATE TABLE board_category(
  category_no   INTEGER NOT NULL auto_increment COMMENT '번호',
  category_name VARCHAR(50) NULL  COMMENT '카테고리 이름',
  PRIMARY KEY  (category_no)
);

CREATE TABLE board_reply(
  reply_no      INTEGER NOT NULL auto_increment COMMENT '댓글 번호',
  reply_content VARCHAR(500) NOT NULL COMMENT '리뷰 내용',
  member_id     VARCHAR(50) NOT NULL COMMENT '아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  board_no      INTEGER NOT NULL COMMENT '게시글 번호',
  PRIMARY KEY  (reply_no),
  FOREIGN KEY (reply_no) REFERENCES board (board_no)
);

CREATE TABLE member(
  member_no      INTEGER NOT NULL auto_increment COMMENT '번호',
  id       	     VARCHAR(50) NOT NULL COMMENT '아이디',
  password       VARCHAR(20) NOT NULL COMMENT '비밀번호',
  name		     VARCHAR(15) NOT NULL COMMENT '성명',
  school_no		 VARCHAR(15) NOT NULL COMMENT '학교 번호',
  reg_date       DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  PRIMARY KEY (member_no)
);

CREATE TABLE school(
  school_no       INTEGER NOT NULL auto_increment COMMENT '학교 번호',
  school_name     VARCHAR(50) NOT NULL COMMENT  '학교명',
  school_category VARCHAR(20) NOT NULL COMMENT '학교 카테고리',
  reg_date        DATETIME NOT NULL COMMENT '등록일시',
  PRIMARY KEY (school_no)
);

INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('강남에서 번개', 'map.png', '강남 8시 모입시다.', 'chat', '1', '', '');



board_no      INTEGER NOT NULL auto_increment COMMENT '게시판 번호',
  subejct       VARCHAR(50) NOT NULL COMMENT '제목',
  img_name    VARCHAR(200) NOT NULL COMMENT '이미지명',
  content       VARCHAR(500) NOT NULL COMMENT '내용',
  category      VARCHAR(20) NOT NULL COMMENT '카테고리', 
  school_no		 VARCHAR(15) NOT NULL COMMENT '학교 번호',
  reg_id        VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
