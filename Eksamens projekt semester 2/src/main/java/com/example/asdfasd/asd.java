package com.example.asdfasd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class asd {

    //Mapping i denne klasse osv.
    @GetMapping("/")
    public String  ShowFrontpage()
    {
        return "CheckInDan";
    }


}
