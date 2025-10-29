package com.mybatis.kdt9.controller;

import com.mybatis.kdt9.dto.UserDTO;
import com.mybatis.kdt9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/insert")
    @ResponseBody
    public String insertUser(@RequestParam String name, @RequestParam String nickname){
        String newName = userService.insertUser(name, nickname);
        return newName + " Success";
    }
    @GetMapping("/nickname")
    @ResponseBody
    public String findNameorNickname(@RequestParam String word){
        // 검색어 word를 받고, 이름이 word거나 닉네임이 word인 사람이 몇 명인지 조회
        int cnt = userService.searchNameOrNickname(word);
        return cnt + "명입니다.";
    }
    @GetMapping("/check")
    @ResponseBody
    public String checkName(@RequestParam String name) {
        // name을 param으로 받아서 그런 이름을 가진 사용자가 있는지 여부를 return
        boolean result = userService.checkUser(name);
        if ( result ) return "있습니다.";
        else return "없습니다.";
    }

    @GetMapping("/search")
    @ResponseBody
    public String selectUser(@RequestParam String name){
        return userService.searchUser(name);
    }
    @GetMapping("/searchid")
    @ResponseBody
    public String searchid(@RequestParam int id){
        return userService.searchId(id);
    }
}
