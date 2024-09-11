package com.backend.jsp.controller.user;


import com.backend.jsp.entity.user.User;
import com.backend.jsp.exception.EmailAlreadyExistsException;
import com.backend.jsp.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    /*
    * 화면을 반환하는 controller
    * security 적용으로 action은 jsp파일에서 한다.
    * */
    @GetMapping("/login")
    public String login() {
        return "users/login-page";
    }

    // Start Create
    @GetMapping("/signup")
    public String signup() {
        return "users/signup-page";
    }
//
//    // check email
//    @GetMapping("/checkEmail")
//    @ResponseBody
//    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
//        boolean isAvailable = userService.isEmailAvailable(email);  // 이메일 중복 여부 확인
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("isAvailable", isAvailable);
//        return ResponseEntity.ok(response);  // JSON 형식으로 응답
//    }

    @PostMapping("/signup")
    public String signup(User user, RedirectAttributes redirectAttributes) {
        int signupCount = 0;
        try{
            signupCount = userService.signup(user);
            if(signupCount < 0 ){
                return "redirect:/";
            }
            return "redirect:users/login-page";
        }catch (EmailAlreadyExistsException e){
            // 중복되면 이렇게 메세지를 전달 할 수 있다?
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }


    }


    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/drop/{email}/{deleteYn}")
    public String drop(@PathVariable String email, @PathVariable String deleteYn ,
                       HttpSession session, RedirectAttributes redirectAttributes) {
       if(email != null && deleteYn != null) {
           int count = userService.dropUser(email,deleteYn.toUpperCase());
           log.info("controller count : " + count);
            if(count > 0){
                session.invalidate();
                redirectAttributes.addFlashAttribute("deleteMessage","탈퇴가 완료되었습니다.");
                return"redirect:/";
            }
       }

       redirectAttributes.addFlashAttribute("deleteMessage","탈퇴가 실패하였습니다. 다시 시도해주세요.");
        return "redirect:/";
    }





}
