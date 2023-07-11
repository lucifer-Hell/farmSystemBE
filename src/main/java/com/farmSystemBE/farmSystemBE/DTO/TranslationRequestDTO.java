package com.farmSystemBE.farmSystemBE.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class TranslationRequestDTO {

    private ArrayList<HashMap<String,String>> inputList;

    public ArrayList<HashMap<String,String>> getInputList() {
        return inputList;
    }
    public void setInputList(ArrayList<HashMap<String,String>> list) {
        this.inputList=list;
    }

}
