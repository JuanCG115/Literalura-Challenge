package com.alurachallenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorData(
        String name,
        Integer birth_year,
        Integer death_year
) {
}
