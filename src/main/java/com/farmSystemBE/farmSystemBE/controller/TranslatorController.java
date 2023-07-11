package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/translate")
public class TranslatorController {

    @Autowired
    TranslatorService translatorService;

    @GetMapping
    String translateText(@RequestParam("text") String text,@RequestParam("from")String inputLanugage,@RequestParam("to") String outputLangugae){
        return translatorService.translateText(text,inputLanugage,outputLangugae);
    }
}
