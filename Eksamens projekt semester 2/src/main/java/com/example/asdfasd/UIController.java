package com.example.asdfasd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UIController {


    // Mapping for the front page
    @GetMapping("/")
    public String showFrontpage() {
        return "CheckInDan";
    }

    // Mapping for the registration form submission
    @PostMapping("/TidForCheckInDan")
    public String handleFormSubmitDan(@RequestParam String Firstname, @RequestParam String Lastname, @RequestParam String DriverLicenseNumber, Model model) {
        Usecase uc = new Usecase();
        User user = uc.createUser(Firstname, Lastname, DriverLicenseNumber);

        DBController db = new DBController();
        db.InsertUser(user); // Pass the User object as an argument

        // Rest of your code

        return "TidForCheckInDan";
    }

    @PostMapping("/CheckInDan")
    public String handleFormSubmitChangeLanguageToDan(Model model) {
        // Process form submission for Danish check-in
        model.addAttribute("okValue", "Check-in successful!");
        return "CheckInDan";
    }

    @PostMapping("/CheckInEng")
    public String handleFormSubmitChangeLanguageToEng(Model model) {
        // Process form submission for English check-in
        model.addAttribute("okValue", "Check-in successful!");
        return "CheckInEng";
    }


    @PostMapping("/TimeForCheckInEng")
    public String handleFormSubmitEng(@RequestParam String Firstname, @RequestParam String Lastname, @RequestParam String DriverLicenseNumber, Model model) {
        Usecase uc = new Usecase();
        User user = uc.createUser(Firstname, Lastname, DriverLicenseNumber);

        DBController db = new DBController();
        db.InsertUser(user); // Pass the User object as an argument

        // Rest of your code

        return "TimeForCheckInEng";
    }

    @PostMapping("/DatabasePrintOut")
    public String handleFormSubmitDatabasePrintOut(Model model) {
        // Process form submission for English check-in
        model.addAttribute("okValue", "!");
        return "DatabasePrintOut";
    }


    /*@GetMapping("/displayUser")
    public String displayUser(@RequestParam int userId, Model model) {
        DBController db = new DBController();
        User user = db.getUserById(userId);

        model.addAttribute("user", user);

        return "userDetailsPage"; // Return the name of the view to display the user details
    }

     */

   /* @GetMapping("/databaseudtraek")
    public String searchUser(@RequestParam("speceltID") int userId, Model model) {
        DBController db = new DBController();
        User user = db.getUserById(userId);

        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("error", "User not found.");
        }

        return "databaseudtraek";
    }

    */



}

     /*@RequestMapping(value = "/CheckInDan", method = {RequestMethod.GET, RequestMethod.POST})
    public String handleFormSubmitTilbageTilCheckInDan( Model model) {
        Usecase uc = new Usecase();
        model.addAttribute("okValue");
        return "CheckInDan";
    }

     */



/*
    @PostMapping("/TidForCheckInDan")
    public String handleFormSubmitDan(
            @RequestParam String Firstname,
            @RequestParam String Lastname,
            @RequestParam String DriverLicenseNumber,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        Usecase uc = new Usecase();
        String ok = uc.checkin(Firstname, Lastname, DriverLicenseNumber);
        model.addAttribute("okValue", ok);

        // Delay in milliseconds (e.g., 5 seconds = 5000 milliseconds)
        int delay = 5000;

        // Add the delay value to the redirect attributes
        redirectAttributes.addFlashAttribute("delay", delay);

        return "redirect:/TidForCheckInDan)";
    }

    @GetMapping("/CheckInDan")
    public String frontPage() {
        return "CheckInDan";
    }

    @GetMapping("/DelayedCheckInDan")
    public String delayedFrontPage(
            @ModelAttribute("delay") Integer delay,
            Model model
    ) {
        // Check if the delay value is present
        if (delay != null && delay > 0) {
            model.addAttribute("delay", delay);
        }

        return "CheckInDan";
    }


 */
