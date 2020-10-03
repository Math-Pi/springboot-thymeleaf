package com.example.web;

import com.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExampleController {
    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "Jax");
        return "string";
    }
    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }
    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }
    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "more");
        map.addAttribute("img", "https://www.baidu.com/img/flexible/logo/pc/privacy.gif");
        return "url";
    }
    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "example");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "eq";
    }
    @RequestMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }
    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("Tom",21,"123");
        User user2=new User("Jenny",22,"124");
        User user3=new User("Alice",20,"125");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}