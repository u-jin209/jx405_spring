package com.bit.spring.controller;

import com.bit.spring.model.BoardDTO;
import com.bit.spring.model.UserDTO;
import com.bit.spring.service.BoardService;
import com.bit.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/board/")
public class BoardController {
    BoardService boardService;
    UserService userService;
    //ReplyService replyService;

    @Autowired
    public BoardController(BoardService boardService, UserService userService){
        this.boardService = boardService;
        this.userService = userService;
        //this.replyService = replyService;
    }
    @GetMapping("showAll/{pageNo}")
    public String showAll(HttpSession session, RedirectAttributes redirectAttributes, Model model, @PathVariable int pageNo){
        if(session.getAttribute("logIn")==null){
            redirectAttributes.addFlashAttribute("message","다시 로그인해주세요");

            return "redirect:/";
        }


        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
        model.addAttribute("logIn",logIn);
        model.addAttribute("list",boardService.selectAll(pageNo));
        model.addAttribute("paging" , setPages(pageNo, boardService.selectLastPage()));
        model.addAttribute("pagingAddr","/board/showAll");





        return "/board/showAll";
    }

    @GetMapping("search/{pageNo}")
    public String search(String keyword,@PathVariable int pageNo ,Model model){
        System.out.println(boardService.selectByKeyword(keyword,pageNo));
        Map<String, Object> map =boardService.selectByKeyword(keyword,pageNo);
        model.addAttribute("list", map.get("list"));
        model.addAttribute("pagingAddr","board/search/"+keyword);
        model.addAttribute("keyword",keyword);
        model.addAttribute("paging" , setPages(pageNo, boardService.countSearchResult(keyword)));
        return "/board/showAll";
    }
    @GetMapping("showOne/{id}")
    public String showOne(HttpSession session, RedirectAttributes redirectAttributes, Model model,@PathVariable int id){
        if(session.getAttribute("logIn")==null){
            redirectAttributes.addFlashAttribute("message","다시 로그인해주세요");

            return "redirect:/";
        }
        BoardDTO b = boardService.selectOne(id);
        //ArrayList<ReplyDTO> r = replyService.selectAll(id);
        if(b == null){
            redirectAttributes.addFlashAttribute("message","존재하지 않는 글 번호 입니다.");
            return "redirect:/board/showAll/1";
        }

        //UserDTO user = userService.selectOne(b.getWriterId());
        model.addAttribute("result",b);
        //model.addAttribute("user", user);
       // model.addAttribute("replylist",r);
        int logInId =((UserDTO)session.getAttribute("logIn")).getId();
        model.addAttribute("logInId",logInId);
        return "/board/showOne";

    }

    @PostMapping("insert")//insert 로직
    public String insertBoard(HttpSession session, BoardDTO boardDTO){
        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
        if(logIn == null){
            return "redirect:/";
        }

        boardDTO.setWriterId(logIn.getId());
        boardService.insert(boardDTO);

        return "redirect:/board/showOne/"+boardDTO.getId();
    }
    @GetMapping("insert/new")//insert 작성 페이지
    public String showInsert(HttpSession session,RedirectAttributes redirectAttributes, Model model) {
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            redirectAttributes.addFlashAttribute("message", "다시 로그인해주세요");
            return "redirect:/";
        }

        return "/board/upsert";

    }


    @GetMapping("update/{id}") //update 작성 페이지
    public String showUpdate(HttpSession session,RedirectAttributes redirectAttributes, Model model,@PathVariable int id){
        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
        if(logIn == null){
            redirectAttributes.addFlashAttribute("message","다시 로그인해주세요");
            return "redirect:/";
        }
        BoardDTO b = boardService.selectOne(id);
        if(b==null || b.getWriterId() != logIn.getId() ){
            redirectAttributes.addFlashAttribute("message","유효하지 않은 접근입니다.");
            return "redirect:/";
        }
        model.addAttribute("result",b);
        model.addAttribute("board",b);
        return "/board/upsert";

    }



    @PostMapping("update") //update 로직
    public String updateBoard(HttpSession session,BoardDTO boardDTO){
        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
        if(logIn == null){
            return "redirect:/";
        }
        BoardDTO origin = boardService.selectOne(boardDTO.getId());
        if(origin == null){
            return "redirect:/board/showAll/1";
        }

        origin.setTitle(boardDTO.getTitle());
        origin.setContent(boardDTO.getContent());

        boardService.update(origin);
        return "redirect:/board/showOne/"+boardDTO.getId();
    }



    @GetMapping("delete/{id}")
    public String delete(HttpSession session,RedirectAttributes redirectAttributes, Model model,@PathVariable int id){
        UserDTO logIn =(UserDTO) session.getAttribute("logIn");
        if(logIn == null){
            redirectAttributes.addFlashAttribute("message","다시 로그인해주세요");
            return "redirect:/";
        }
        BoardDTO b = boardService.selectOne(id);
        if(b==null || b.getWriterId() != logIn.getId() ){
            redirectAttributes.addFlashAttribute("message","유효하지 않은 접근입니다.");
            return "redirect:/board/showAll/1";
        }

        boardService.delete(id);
        return "redirect:/board/showAll/1";
    }

    private HashMap<String ,Integer> setPages(int pageNo, int totalPage){
        HashMap<String,Integer> paging = new HashMap<>();

        int start =0;
        int end = 0;

        if(totalPage <5){
            start =1;
            end =totalPage;
        }else if(pageNo <3) {
            start =1;
            end = 5;

        }else if(pageNo>totalPage -3){
            start =totalPage -4;
            end = totalPage;
        }else {
            start = pageNo -2;
            end = pageNo +2;
        }
        paging.put("start", start);
        paging.put("end", end);
        paging.put("totalPage",totalPage);
        paging.put("current", pageNo);


        return paging;
    }

    @GetMapping("upsert")
    public String showUpsert(@PathVariable @Nullable int id){
        System.out.println("id");
        return "/board/upsert";
    }
}
