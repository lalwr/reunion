# Database 생성
CREATE DATABASE reunion;
# Database 삭제
# DROP DATABASE reunion;
# 유니크키 설정
ALTER IGNORE TABLE member ADD UNIQUE (id);
# 원격으로도 접속가능
GRANT ALL PRIVILEGES ON reunion.* TO fastcamp@'%' IDENTIFIED BY 'fastcamp';
# 로컬로만 접속가능
GRANT ALL PRIVILEGES ON reunion.* TO fastcamp@'localhost' IDENTIFIED BY 'fastcamp';
# DB에 여태까지 실행한 명령여 적용
FLUSH PRIVILEGES ;

USE reunion;

show databases;

#table 컬럼명 변경
ALTER TABLE board_reply CHANGE reply_content content VARCHAR(500) NOT NULL COMMENT '댓글 내용';

-- creates some test-tables and data
-- DROP TABLE EMPLOYEE;

#학교
CREATE TABLE school(
  no       INTEGER NOT NULL auto_increment COMMENT '학교 번호',
  name     VARCHAR(50) NOT NULL COMMENT  '학교명',
  category VARCHAR(20) NOT NULL COMMENT '학교 카테고리',
  reg_date        DATETIME NOT NULL COMMENT '등록일시',
  PRIMARY KEY (no)
);

#게시판 학교별 카테고리
CREATE TABLE school_category(
  no        INTEGER NOT NULL auto_increment COMMENT '게시판 카테고리 번호',
  name 	    VARCHAR(50) NULL COMMENT '게시판 카테고리 이름',
  school_no INTEGER NOT NULL COMMENT '학교 번호',
  PRIMARY KEY  (no),
  FOREIGN KEY (school_no) REFERENCES school(no)
);

#유저
CREATE TABLE member(
  no      INTEGER NOT NULL auto_increment COMMENT '번호',
  id       	     VARCHAR(50) NOT NULL COMMENT '아이디',
  password       VARCHAR(20) NOT NULL COMMENT '비밀번호',
  name		     VARCHAR(15) NOT NULL COMMENT '성명',
  reg_date       DATETIME NOT NULL COMMENT '등록일시',
  edit_date      DATETIME NOT NULL COMMENT '수정일시',
  PRIMARY KEY (no),
  UNIQUE (id)
);

#유저와 학교관리
CREATE TABLE member_school(
  member_no      INTEGER NOT NULL COMMENT '번호',
  school_no       INTEGER NOT NULL COMMENT '학교 번호',
  FOREIGN KEY (member_no) REFERENCES member (no),
  FOREIGN KEY (school_no) REFERENCES school (no)
);

CREATE TABLE board(
  no      	    INTEGER NOT NULL auto_increment COMMENT '게시글 번호',
  subject       VARCHAR(50) NOT NULL COMMENT '제목',
  content       VARCHAR(500) NOT NULL COMMENT '내용',
  reg_id        VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  school_no     INTEGER NOT NULL  COMMENT '학교 번호',
  category_no   INTEGER NOT NULL COMMENT '게시판 카테고리 번호', 
  PRIMARY KEY  (no),
  FOREIGN KEY (school_no) REFERENCES school (no)
);

#댓글
CREATE TABLE board_reply(
  no     	    INTEGER NOT NULL auto_increment COMMENT '댓글 번호',
  content 	    VARCHAR(500) NOT NULL COMMENT '댓글 내용',
  member_id     VARCHAR(50) NOT NULL COMMENT '아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  board_no      INTEGER NOT NULL COMMENT '게시글 번호',
  PRIMARY KEY  (no),
  FOREIGN KEY (board_no) REFERENCES board (no)
);

CREATE TABLE file(
  no      	    INTEGER NOT NULL auto_increment COMMENT '파일번호',
  name          VARCHAR(100) NOT NULL COMMENT '파일명',
  temp_name     VARCHAR(100) NOT NULL COMMENT '임시 파일명',
  path          VARCHAR(100) NOT NULL COMMENT '파일 경로',
  format		VARCHAR(100) NOT NULL COMMENT '파일 형식',
  reg_id        VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
  reg_date      DATETIME NOT NULL COMMENT '등록일시',
  board_no      INTEGER NOT NULL COMMENT '게시글 번호',
  PRIMARY KEY  (no),
  FOREIGN KEY (board_no) REFERENCES board (no)
);

#charset 변경
alter table school convert to character set utf8;
alter table school_category convert to character set utf8;
alter table member convert to character set utf8;
alter table board convert to character set utf8;
alter table board_reply convert to character set utf8;
alter table file convert to character set utf8;


#유저
INSERT INTO member(id, password, name , reg_date, edit_date)
VALUES('kim', '123', '김형진', now(), now());

#학교
INSERT INTO school(name, category, reg_date)
VALUES('패스트캠퍼스 초등학교', 'elementary', now());

#유저 학교 관리
INSERT INTO member_school(member_no, school_no)
VALUES('1', '1');

#게시판 카테고리
INSERT INTO school_category(name, school_no)
VALUES('잡담게시판', '1');
INSERT INTO school_category(name, school_no)
VALUES('공지게시판', '1');

# 게시판
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('강남에서 번개', '강남 8시 모입시다.', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('홍대 번개',  '홍대 모입시다.', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('번개',  '8시 번개.', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('강남 급번개', '강남 번개 모집.', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('놀아요',  '오늘 노실분', '1', '1', 'kim', now(), now());

INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('모임공지 1',  '공지 1단계', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('모임공지 2',  '공지 2단계', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('모임공지 3', '공지 3단계', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('모임공지 4', '공지 4단계', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('모임공지 5', '공지 5단계', '1', '1', 'kim', now(), now());

INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('잡담 합시다', '댓글 타임', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('치킨 이벤트', '10번째 분에게 치맥.', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('날씨가 춥네요', '춥다', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('날씨ㅠ', '추운 날.', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('도배 123123', '도배!!!', '1', '1', 'kim', now(), now());

INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('게임하실분??', '게임 같이해요', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('게임하시는분??', '게임뭐하시나요', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('치맥!!',  '치맥 먹어용!!', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('주말 놀러가요!', '주말 여의도로!!', '1', '1', 'kim', now(), now());
INSERT INTO board(subject, content, category_no, school_no, reg_id, reg_date, edit_date)
VALUES('동창회 모집',  '동창회 10기 모여', '1', '1', 'kim', now(), now());

#게시판 답글
#select * from board_reply;
INSERT INTO board_reply(reply_content, member_id, reg_date, edit_date, board_no)
VALUES('추천!!', 'kim', now(), now(), 1);
INSERT INTO board_reply(reply_content, member_id, reg_date, edit_date, board_no)
VALUES('좋아요~~', 'kim', now(), now(), 1);
INSERT INTO board_reply(reply_content, member_id, reg_date, edit_date, board_no)
VALUES('무댓!!', 'kim', now(), now(), 1);

#파일
INSERT INTO file (name, temp_name, path, format, reg_id, reg_date, board_no)
VALUES("test.png", "dwjw123.png", "image","/mac/download/", "kim", now(), '1');

SELECT no AS no
	   ,subject AS subject
	   ,content AS content
	   ,reg_id AS regId
	   ,reg_date AS regDate
	   ,edit_date AS editDate
	   ,school_no AS schoolNo
	   ,category_no AS categoryNo
FROM board
WHERE 1=1;

SELECT no AS no
       , name AS name
	   , school_no schoolNo
FROM school_category
WHERE 1=1;

SELECT no AS no
	   ,subject AS subject
	   ,content AS content
	   ,reg_id AS regId
	   ,reg_date AS regDate
	   ,edit_date AS editDate
	   ,school_no AS schoolNo
	   ,category_no AS categoryNo
FROM board
WHERE 1=1;

SELECT no AS replyNo
	   ,content AS content
	   ,member_id AS memberId
	   ,reg_date AS regDate
	   ,edit_date AS editDate
	   ,board_no AS boardNo
FROM board_reply
WHERE 1=1;

SELECT no AS no
	   ,id AS id
	   ,password AS password
	   ,name AS name
	   ,reg_date AS regDate
	   ,edit_date AS editDate
FROM member
WHERE 1=1;

SELECT no AS no
	   ,name AS name
	   ,category AS category
	   ,reg_date AS regDate
	   ,edit_date AS editDate
FROM school
WHERE 1=1;

SELECT no AS no
	   ,name AS name
	   ,temp_name AS tempName
	   ,path AS path
	   ,format AS fotmat
	   ,reg_id AS regID
	   ,reg_date AS regDate
	   ,board_no AS boardNo
FROM file
WHERE 1=1;