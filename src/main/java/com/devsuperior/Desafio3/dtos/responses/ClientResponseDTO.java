package com.devsuperior.Desafio3.dtos.responses;

import com.devsuperior.Desafio3.entities.Client;

import java.time.LocalDate;

public record ClientResponseDTO(
        Long id,
        String name,
        String cpf,
        Double income,
        LocalDate birthDate,
        Integer children
) {

    public ClientResponseDTO(Client entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getIncome(),
                entity.getBirthDate(),
                entity.getChildren()
        );
    }

}
