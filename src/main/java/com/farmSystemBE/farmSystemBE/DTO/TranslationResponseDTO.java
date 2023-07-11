package com.farmSystemBE.farmSystemBE.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TranslationResponseDTO {
    @JsonProperty("translations")
    private List<TranslationDTO> translations;

    public List<TranslationDTO> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationDTO> translations) {
        this.translations = translations;
    }

    public static class TranslationDTO {

        @JsonProperty("text")
        private String text;

        @JsonProperty("to")
        private String to;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}

