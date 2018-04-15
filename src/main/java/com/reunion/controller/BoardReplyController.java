package com.reunion.controller;

import com.reunion.dao.BoardReplyDao;
import com.reunion.dao.MemberDao;
import com.reunion.dao.MemberSchoolDao;
import com.reunion.dao.SchoolDao;
import com.reunion.domain.BoardReply;
import com.reunion.domain.Reunion;
import com.reunion.service.BoardReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*
    1. 댓글 등록
      로그인 세션 존재? ok --> 1) 로그인 ok --> 버튼 활성화 및 에디티박스 활성화
                                      --> 사용자 아이디 표시
                                      --> 등록 버튼 누르면 댓글 등록 완료
      로그인 세션 존재? no --> 2) 로그인 no --> 버튼 비활성화 및 에디티박스 비활성화

    2. 댓슬 수정
      1) 로그인 된 사람의 해당 글만 수정 버튼 활성화
      2) 수정 버튼 클릭 시 에디트 박스에 저장 된 내용 뿌려주고 수정할 수 있게 동작
      3) 확인 버튼 누르면 수정 완료

    3. 댓글 삭제
      1) 로그인 된 사람의 해당 글만 삭제 버튼 활성화
      2) 삭제 버튼 누를 시 댓글 목록에서 삭제
*/

@Controller
public class BoardReplyController {

    @Autowired
    BoardReplyService boardReplyService;

    @GetMapping(value = "/list_reply")
    public String list(BoardReply reply, ModelMap modelMap) throws Exception {
        List<BoardReply> replyList = boardReplyService.list(reply);
        modelMap.addAttribute("replyList", replyList);
        return "ajax/reply/replyView";
    }

    @PostMapping(value = "/write_reply")
    public String write(BoardReply reply, ModelMap modelMap) throws Exception {
        reply.setMemberId("oh");
        reply.setBoardNo(1);
        boardReplyService.insert(reply);

        return "redirect:/list_reply";
    }

    @PostMapping(value = "/delete_reply}")
    public String delete(BoardReply reply,ModelMap modelMap) throws Exception {
        boardReplyService.delete(reply);
        return "redirect:/list_reply";
    }

}
