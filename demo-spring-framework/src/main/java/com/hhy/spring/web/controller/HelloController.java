package com.hhy.spring.web.controller;

import com.hhy.spring.framework.annotation.Component;
import com.hhy.spring.web.annotation.Controller;
import com.hhy.spring.web.annotation.Param;
import com.hhy.spring.web.annotation.RequestMapping;
import com.hhy.spring.web.annotation.ResponseBody;
import com.hhy.spring.web.servlet.ModelAndView;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
@Controller
@Component
@RequestMapping("/hello")
public class HelloController {
    class User {
        String name;
        Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
    @RequestMapping("/a")
    public String hello(@Param("name")String name, @Param("age")Integer age) {
        return String.format("<h1>name = %s, age = %s</h1>", name ,age);
    }

    @RequestMapping("/ajson")
    @ResponseBody
    public User helloJson(@Param("name")String name, @Param("age")Integer age) {
        return new User(name, age);
    }

    @RequestMapping("/mav")
    public ModelAndView mav() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setParameter("name", "wxz");
        modelAndView.setView("index.html");
        return modelAndView;
    }

}
