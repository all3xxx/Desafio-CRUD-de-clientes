package com.devsuperior.Desafio3.dtos.errors;

public record FieldMessage(
        String fieldName,
        String message
) {
}
