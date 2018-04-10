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
  category_no   INTEGER NOT NULL auto_increment COMMENT '게시판 카테고리 번호',
  category_name VARCHAR(50) NULL  COMMENT '카테고리 이름',
  PRIMARY KEY  (category_no)
);

CREATE TABLE board_reply(
  reply_no      INTEGER NOT NULL auto_increment COMMENT '댓글 번호',
  reply_content VARCHAR(500) NOT NULL COMMENT '댓글 내용',
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
  reg_date       DATETIME NOT NULL COMMENT '등록일시',
  edit_date     DATETIME NOT NULL COMMENT '수정일시',
  PRIMARY KEY (member_no)
);

CREATE TABLE member_school(
  member_no      INTEGER NOT NULL auto_increment COMMENT '번호',
  school_no       INTEGER NOT NULL auto_increment COMMENT '학교 번호',
  FOREIGN KEY (member_no) REFERENCES member (member_no),
  FOREIGN KEY (school_no) REFERENCES school (school_no)
);

CREATE TABLE school(
  school_no       INTEGER NOT NULL auto_increment COMMENT '학교 번호',
  school_name     VARCHAR(50) NOT NULL COMMENT  '학교명',
  school_category VARCHAR(20) NOT NULL COMMENT '학교 카테고리',
  reg_date        DATETIME NOT NULL COMMENT '등록일시',
  PRIMARY KEY (school_no)
);

#유저
INSERT INTO member(id, password, name, school_no, reg_date, edit_date)
VALUES('kim', '123', '김형진', '1', now(), now());

#학교
INSERT INTO school(school_name, school_category, reg_date)
VALUES('패스트캠퍼스 초등학교', 'elementary', now());

#게시판 카테고리
INSERT INTO board_category(category_name)
VALUES('잡담게시판');
INSERT INTO board_category(category_name)
VALUES('공지게시판');

# 게시판
INSERT INTO board(subject, img_name, content, category_no, school_no, reg_id, reg_date)
VALUES('강남에서 번개', 'map.png', '강남 8시 모입시다.', '1', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('홍대 번개', 'map1.png', '홍대 모입시다.', '1', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('번개', 'map.png', '8시 번개.', '1', '1', 'kim', now());
INSERT INTO board(subjec2t, img_name, content, category, school_no, reg_id, reg_date)
VALUES('강남 급번개', 'map3.png', '강남 번개 모집.', '1', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('놀아요', 'map4.png', '오늘 노실분', '1', '1', 'kim', now());

INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('모임공지 1', '1.png', '공지 1단계', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('모임공지 2', '2.png', '공지 2단계', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('모임공지 3', '3.png', '공지 3단계', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('모임공지 4', '4.png', '공지 4단계', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('모임공지 5', '5.png', '공지 5단계', 'chat', '1', 'kim', now());

INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('잡담 합시다', 'ad.png', '댓글 타임', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('치킨 이벤트', 'adsasd.png', '10번째 분에게 치.', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('날씨가 춥네요', 'dasda.png', '춥다', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('날씨ㅠ', 'da123.png', '추운 날.', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('도배 123123', 'dsa123.png', '도배!!!', 'chat', '1', 'kim', now());

INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('게임하실분??', 'dsada.png', '게임 같이해요', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('게임하시는분??', 'dsa.png', '게임뭐하시나요', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('치맥!!', 'dsad.png', '치맥 먹어용!!', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('주말 놀러가요!', '123.png', '주말 여의도로!!', 'chat', '1', 'kim', now());
INSERT INTO board(subject, img_name, content, category, school_no, reg_id, reg_date)
VALUES('동창회 모집', 'dsad.png', '동창회 10기 모여', 'chat', '1', 'kim', now());

#게시판 답글
INSERT INTO board_reply(reply_content, member_id, reg_date, edit_date, board_no)
VALUES('추천!!', 'kim', now(), now(), 1);
INSERT INTO board_reply(reply_content, member_id, reg_date, edit_date, board_no)
VALUES('좋아요~~', 'kim', now(), now(), 1);
INSERT INTO board_reply(reply_content, member_id, reg_date, edit_date, board_no)
VALUES('무댓!!', 'kim', now(), now(), 1);

SELECT board_no AS boardNo
	   ,subject AS subject
	   ,img_name AS imgNmme
	   ,content AS content
	   ,reg_id AS regId
	   ,edit_date AS editDate
	   ,school_no AS schoolNo
	   ,category_no AS categoryNo
FROM board
WHERE 1=1;

SELECT category_no AS categoryNo
       , category_name AS categoryName
FROM board_category
WHERE 1=1;

SELECT reply_no AS replyNo
	   ,reply_content AS replyContent
	   ,member_id AS memberId
	   ,reg_date AS regDate
	   ,edit_date AS editDate
	   ,board_no AS boardNo
FROM board_reply
WHERE 1=1;

SELECT member_no AS memberNo
	   ,id AS id
	   ,password AS password
	   ,name AS name
	   ,school_no AS schoolNo
	   ,reg_date AS regDate
	   ,edit_date AS editDate
FROM member
WHERE 1=1;

SELECT school_no AS schoolNo
	   ,school_name AS password
	   ,name AS name
	   ,school_no AS schoolNo
	   ,reg_date AS regDate
	   ,edit_date AS editDate
FROM school
WHERE 1=1;




