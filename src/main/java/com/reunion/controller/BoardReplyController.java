package com.reunion.controller;

import com.reunion.domain.BoardReply;
import com.reunion.service.BoardReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
@RequestMapping(value="/boardreply")
public class BoardReplyController {

    @Autowired
    BoardReplyServiceImpl boardReplyServiceImpl;

    @GetMapping(value = "/list_reply/{boardNo}")
    public String list(HttpSession session,@PathVariable(value="boardNo")int no,BoardReply reply, ModelMap modelMap,Model model) throws Exception {
        List<BoardReply> replyList = boardReplyServiceImpl.list(no);

        modelMap.addAttribute("replyList", replyList);
        model.addAttribute("board_n",no);
        model.addAttribute("userid",session.getId());
//        return "ajax/reply/replyView";
        return "reply/replyView";
    }

    @PostMapping(value = "/write_reply/{boardNo}")
    public String write(HttpSession session, BoardReply reply, @PathVariable(value="boardNo")int no) throws Exception {
        reply.setMemberId(session.getId());
        reply.setBoardNo(no);
        reply.setMemberId("oh");
        boardReplyServiceImpl.insert(reply);

        return "redirect:/boardreply/list_reply/"+no;
    }

    @GetMapping(value = "/delete_reply/{boardNo}/{no}")
    public String delete(@PathVariable(value="no")int no, @PathVariable(value="boardNo")int boardno, BoardReply reply,Model model) throws Exception {
//        boardReplyService.delete(no,boardno);
        boardReplyServiceImpl.delete(reply);

//        String strNo = Integer.toString(no);
        model.addAttribute("boardno",boardno);
        return "reply/delete_reply";
    }

    @PostMapping("/update_reply/{boardNo}/{no}")
    public String update(@PathVariable(value="no")int no, @PathVariable(value="boardNo")int boardno, @RequestParam(name = "updatecontent") String content, BoardReply reply, ModelMap modelMap) throws Exception{
        List<BoardReply> replyList = boardReplyServiceImpl.list(boardno);
        reply.setContent(content);
        reply.setNo(no);
        reply.setBoardNo(boardno);
        boardReplyServiceImpl.update(reply);

        modelMap.addAttribute("replyList", replyList);
        return "redirect:/boardreply/list_reply/"+boardno;
    }

    @GetMapping("/update/form/{boardNo}/{no}")
    public String updateform(@PathVariable(value="no")int no,@PathVariable(value="boardNo")int boardno,BoardReply reply,Model model) throws Exception{
        reply = boardReplyServiceImpl.selectContent(no);
        reply.setBoardNo(boardno);
        String content = reply.getContent();
        model.addAttribute("content",content);
        System.out.println("내용:"+content);

        return "reply/update_reply";
    }

}
