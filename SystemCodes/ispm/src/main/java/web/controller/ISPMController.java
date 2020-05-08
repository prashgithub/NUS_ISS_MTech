package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.ApplicationDto;
import web.dao.QuestionDto;
import web.jpa.jparepository.QuestionRepository;
import web.jpa.jparepository.UserFeedbackRepository;
import web.jpa.model.Question;
import web.jpa.model.UserFeedback;
import web.service.CalcService;
import web.service.ISPMService;

import java.util.Optional;

@Controller
public class ISPMController {
    ApplicationDto currentApplication;

    @Autowired CalcService calcService;
    @Autowired ISPMService ispmService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserFeedbackRepository userFeedbackRepository;

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
        currentApplication.setCalcService(calcService);
        return "redirect:questions";
    }

    @GetMapping("/questions")
    public String questions(@ModelAttribute QuestionDto questionDto, Model model) {
        final Long answeredQueId = questionDto.getQid();
        String answer = questionDto.getAnswer();
        String preference = questionDto.getPreference();
        Question answeredQuestion = questionRepository.findById(answeredQueId).get();
        if(currentApplication.getCalcService() == null)
            currentApplication.setCalcService(calcService);

        if (answeredQueId != 0)
            currentApplication.setAnswer(answeredQuestion, answeredQueId, answer, preference);

        final Long nextQuestionId = answeredQueId + 1;
        Optional<Question> nextQuestionOp = questionRepository.findById(nextQuestionId);

        //detect stage change
        boolean isRedirection = !nextQuestionOp.isPresent()
//                || answeredQuestion.getStage() < nextQuestionOp.get().getStage()
                || ("Preference".equalsIgnoreCase(answeredQuestion.getValue()) && answer.equals("2"))
                ;
        if (isRedirection && answeredQueId != 0)
            return "redirect:complete";

        Question nextQuestion = nextQuestionOp.get();
        model.addAttribute("qid", nextQuestion.getId());
        model.addAttribute("name", nextQuestion.getName());
        model.addAttribute("choices", nextQuestion.getExtraDataValuesAsArray(currentApplication));
        return "questions";
    }

    @GetMapping("/complete")
    public String result(@ModelAttribute ApplicationDto applicationDto, Model model) {
        model.addAttribute("matchedPolicy", ispmService.getMatchedPolicy(currentApplication));
        //OPtaplannerService.invoke()
        return "complete";
    }

    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

    @GetMapping("/end")
    public String end(@ModelAttribute UserFeedback userFeedback) {
        userFeedback.setName(currentApplication.getName());
        userFeedback.setAge(Integer.parseInt(currentApplication.getAge()));
        userFeedback.setGender(currentApplication.getGender());
        userFeedback.setStatus(currentApplication.getStatus());
        userFeedbackRepository.save(userFeedback);
        return "redirect:start";
    }
}
