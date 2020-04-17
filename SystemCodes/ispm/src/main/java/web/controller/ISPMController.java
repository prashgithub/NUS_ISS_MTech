package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.ApplicationDto;
import web.dao.QuestionDto;
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
    public String start() {
        return "application_info";
    }

    @GetMapping("/user")
    public String user(@ModelAttribute ApplicationDto applicationDto) {
        currentApplication = applicationDto;
        return "redirect:questions";
    }

    @GetMapping("/questions")
    public String questions(@ModelAttribute QuestionDto questionDto, Model model) {
        Long questionId = questionDto.getQid();
        String answer = questionDto.getAnswer();
        String preference = questionDto.getPreference();
        currentApplication.setAnswer(questionId, answer, preference);
        if (questionId.longValue() == 5L || (questionId.longValue() == 1L && answer.equals("2"))){
            return "redirect:complete";
        }
        else{
            questionId ++;
        }
        Optional<Question> byId = questionRepository.findById(questionId);
        Optional<Question> def = questionRepository.findById(0L);
        Question question = byId.orElseGet(def::get);
        model.addAttribute("qid", question.getId());
        model.addAttribute("name", question.getName());
        model.addAttribute("choices", question.getExtraData().split(","));
        return "questions";
    }

    @GetMapping("/complete")
    public String result(@ModelAttribute ApplicationDto applicationDto, Model model) {
        model.addAttribute("matchedPolicy", ispmService.getMatchedPolicy(applicationDto));
        //OPtaplannerService.invoke()
        return "complete";
    }

    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

}
