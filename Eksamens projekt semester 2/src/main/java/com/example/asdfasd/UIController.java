package com.example.asdfasd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UIController {

    //Mapping i denne klasse osv.
    @GetMapping("/")
    public String  showFrontpage()
    {
        return "CheckInDan";
    }


    @PostMapping("/TidForCheckInDan")
    public String handleFormSubmit(@RequestParam String Firstname, @RequestParam String Lastname, @RequestParam String koerekort, Model model) {
        Usecase uc = new Usecase();
        String ok = uc.checkin(Firstname, Lastname, koerekort);
        model.addAttribute("okValue", ok);
        return "TidForCheckInDan";
    }

}
