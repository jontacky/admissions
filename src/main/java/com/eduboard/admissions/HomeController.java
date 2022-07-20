package com.eduboard.admissions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by: Jonathan Tanye
 * Email: jtanye@gmail.com
 * Project: admissions
 * Date: 19/07/2022
 */
@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Welcome to Eduboard Admissions Portal!";
    }

}
