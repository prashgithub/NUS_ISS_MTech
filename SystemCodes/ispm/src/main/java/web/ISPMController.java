package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.ApplicationDto;

import java.util.ArrayList;

@Controller
public class ISPMController {

    ApplicationDto currentApplication;

    @Autowired
    ISPMService ispmService;


    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/start")
    public String start(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("name", name);
        ApplicationDto applicationDto = new ApplicationDto();
        model.addAttribute("applicationDto", applicationDto);
        return "application_info";
    }



    @PostMapping("/submit")
    public String submit(@ModelAttribute ApplicationDto applicationDto, Model model) {
        model.addAttribute("matchedPolicy", ispmService.getMatchedPolicy(applicationDto));
        return "complete";
    }

}
