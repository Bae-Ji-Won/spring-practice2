package spring.basic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.basic.demo.domain.Member;
import spring.basic.demo.service.MemberService;

import java.util.List;


@Controller
public class MemberController {

    MemberService service;
    @Autowired
    public MemberController(MemberService service){         // controller와 service 연결하는 느낌
        this.service = service;
    }

    @GetMapping("members/new")
    public String createMember(){
        return "members/createForm";
    }

    // URL 이 변경되지 않은 상태에서 실행
    @PostMapping("members/new")
    public String create(@RequestParam("name") String name){

        Member m = new Member();
        m.setName(name);

        //DB에 입력한 값을 넣어야 해요.
        service.join(m);

        return "redirect:/";    // 제일 첫 페이지로 돌아감
    }

    @GetMapping("members/find")
    public String findMember(){

        return "members/findForm";
    }

    @PostMapping("members/find")
    public String find(@RequestParam("id")int id, Model model){
        // Service를 통해서 id로 member를 찾아서
        Member m = service.findMemberBuId(id);

        // 찾은 객체를 통재로 다음 페이지로 넘김
        model.addAttribute("member",m);  // member라는 상자에 m객체를 넣어서 보내줌

        // 다음 페이지로 이동
        return "members/findMember";
    }

    @GetMapping("members/memberList")
    public String memberList(Model model){
        List<Member> members = service.findAllMember();
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
