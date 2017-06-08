package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import bo.ucbcba.taller.proyecto.proyecto.services.BandService;
import bo.ucbcba.taller.proyecto.proyecto.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by amolina on 15/05/17.
 */

@RestController
public class StepLogController {

    private StepService stepService;
    private BandService bandService;
    private SimpMessagingTemplate template;

    @Autowired
    public StepLogController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }

    @Autowired
    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    @RequestMapping(value = "/API/stepLog/{register}", method = RequestMethod.POST)
    public Step saveStep(@Valid Step step,@PathVariable Long register, BindingResult bindingResult, Model model) {
        //establezco el band por el bandId del step
        int bandId=step.getBandId();
        Band band=bandService.getBandById(bandId);
        step.setBand(band);
        //establezco la fecha de subida
        Date uploadDate=new Date(System.currentTimeMillis());
        step.setUploadDate(uploadDate);
        //establezco la fecha de registro, que se recibio como PathVariable
        Date registerDate=new Date(register);
        step.setRegisterDate(registerDate);
        //guardo el Step en la base de datos
        stepService.saveStep(step);
        //informamos a los clientes suscritos a '/topic/general'
        this.template.convertAndSend("/topic/steps", "");
        return step;
    }

    @RequestMapping(value = "/API/stepLogs", method = RequestMethod.GET)
    public Iterable<Step> listStep( ) {
        Iterable<Step>stepLogs=stepService.listAllSteps();
        return stepLogs;
    }

    @RequestMapping(value = "/API/stepLog/{id}", method = RequestMethod.GET)
    public Step getStep(@PathVariable Integer id)
    {
        Step step=stepService.getStepById(id);
        step.setBandId(step.getBand().getId());
        return step;
    }

    @SendTo("/topic/steps")
    @RequestMapping(value = "/API/getStepsInner", method = RequestMethod.GET)
    public Step getSteps() {
        Integer steps = 0;
        Iterator<Step> iterator = stepService.listAllSteps().iterator();

        Step step = iterator.next();
        steps+=step.getQuantity();
        while(iterator.hasNext()){
            steps += iterator.next().getQuantity();
        };

        step.setQuantity(steps);
        return step;
    }

}
