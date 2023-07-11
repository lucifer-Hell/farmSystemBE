package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.TranslationRequestDTO;
import com.farmSystemBE.farmSystemBE.DTO.TranslationResponseDTO;
import com.farmSystemBE.farmSystemBE.service.TranslatorService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
@Service
public class AzureTranslatorServiceImpl implements TranslatorService {
    @Override
    public String translateText(String text, String inputLanguage, String outputLanguage) {
        String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0"+"&from="+inputLanguage+"&to="+outputLanguage;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", "7715d445453f41229d4f259229faa010");
        headers.set("Ocp-Apim-Subscription-Region", "eastus");
        TranslationRequestDTO translationRequestDTO=new TranslationRequestDTO();
        HashMap<String,String> inputList= new HashMap<>();
        inputList.put("Text",text);
        ArrayList<HashMap<String,String>> temp=new ArrayList<>();
        temp.add(inputList);
        translationRequestDTO.setInputList(temp);
        HttpEntity< ArrayList<HashMap<String,String>>> httpEntity = new HttpEntity<>(temp, headers);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<TranslationResponseDTO[]> responseEntity= restTemplate.postForEntity(url,httpEntity, TranslationResponseDTO[].class);
        TranslationResponseDTO[] translationResponseDTO= responseEntity.getBody();
        return translationResponseDTO[0].getTranslations().get(0).getText();
    }
}
