package com.trabalhodeback.locadoradigital.dto;

import com.trabalhodeback.locadoradigital.model.UserRole;

public record RegisterDTO(String email, String password, String nome, UserRole role) {
}
