package pl.pjwstk.PKP_IC_Copy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pjwstk.PKP_IC_Copy.model.Train;
import pl.pjwstk.PKP_IC_Copy.modelDTO.TrainDTO;
import pl.pjwstk.PKP_IC_Copy.reposiory.TrainService;

import java.util.List;

@Controller
public class TrainController {

    TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/train")
    public String displayTrainForm(Model model){

        model.addAttribute("train", new TrainDTO());
        return "index";
    }

    @PostMapping("/trains")
    public String submitTrainPlace(@ModelAttribute TrainDTO trainDTO, RedirectAttributes attributes){

        attributes.addFlashAttribute("train", trainDTO);
        return "redirect:/listOfTrains";
    }

    @GetMapping("/listOfTrains")
    public String TrainList(Model model, @ModelAttribute("train") TrainDTO trainDTO){

        List<Train> trainList = trainService.displayTrains();
        model.addAttribute("trains", trainList);
        return "chooseTrain";
    }

    @PostMapping("/listOfTrains")
    public String submitTrain(@ModelAttribute TrainDTO trainDTO, RedirectAttributes attributes){

        attributes.addFlashAttribute("trainId", trainDTO);
        return "redirect:/personalData";
    }

    @GetMapping("/personalData")
    public String personalDataForm(Model model, @ModelAttribute("trainId") TrainDTO trainDTO){

        model.addAttribute("train", trainDTO);
        return "personalData";
    }

    @PostMapping("/personalData")
    public String submitPersonalDataFrom(){

        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation(){

     return "confirmation";
    }

    @GetMapping("/add/train")
    public String displayAddTrainForm(Model model){

        model.addAttribute("train", new TrainDTO());
        return "addTrain";
    }

    @PostMapping("/add/trains")
    public String submitAddTrainForm(@ModelAttribute TrainDTO trainDTO){

        trainService.addTrainToRepo(trainDTO);
        return "redirect:/add/train";
    }

    @GetMapping("/delete/train")
    public String displayDeleteTrainForm(Model model){

        List<Train> trainList = trainService.displayTrains();
        model.addAttribute("trains", trainList);
        model.addAttribute("trainDTO", new TrainDTO());
        return "deleteTrain";
    }

    @PostMapping("/delete/trains")
    public String submitDeleteTrainForm(@ModelAttribute TrainDTO trainDTO){

        trainService.deleteTrainFromRepo(trainDTO.getId());
        return "redirect:/delete/train";
    }
}