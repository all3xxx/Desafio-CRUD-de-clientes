package com.devsuperior.Desafio3.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientRequestDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        String name,

        String cpf,
        Double income,

        @PastOrPresent(message = "Não pode ser data futura")
        LocalDate birthDate,
        Integer children
) {
}
