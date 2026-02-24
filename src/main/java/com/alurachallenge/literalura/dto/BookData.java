package com.alurachallenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        String title,
        List<AutorData> authors,
        List<String> languages,
        Integer download_count
) {
}
