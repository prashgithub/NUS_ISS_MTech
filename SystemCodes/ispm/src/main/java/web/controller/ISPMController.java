package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.ApplicationDto;
import web.model.Question;
import web.repository.QuestionRepository;
import web.service.ISPMService;

import java.util.Optional;

@Controller
public class ISPMController {

    ApplicationDto currentApplication;

    @Autowired ISPMService ispmService;
    @Autowired QuestionRepository questionRepository;

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

    @GetMapping("/questions")
    public String questions(@RequestParam(name="id", required=false, defaultValue="1") Long id, Model model) {
        Optional<Question> byId = questionRepository.findById(id);
        Optional<Question> def = questionRepository.findById(0L);
        Question question = byId.orElseGet(def::get);
        model.addAttribute("question", question);
        return question.getName();
    }

    @PostMapping("/question1")
    public String question1(@ModelAttribute ApplicationDto applicationDto, Model model) {
        model.addAttribute("matchedPolicy", ispmService.getMatchedPolicy(applicationDto));
        return "question1";
    }

    @PostMapping("/question2")
    public String question2(@ModelAttribute ApplicationDto applicationDto, Model model) {
        model.addAttribute("matchedPolicy", ispmService.getMatchedPolicy(applicationDto));
        return "question2";
    }

    @PostMapping("/complete")
    public String result(@ModelAttribute ApplicationDto applicationDto, Model model) {
        model.addAttribute("matchedPolicy", ispmService.getMatchedPolicy(applicationDto));
        //OPtaplannerService.invoke()
        return "complete";
    }

}
