package com.uap.centrosaludsantaclara.citas.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Especialidad {
    CARDIOLOGIA("Cardiología"),
    DERMATOLOGIA("Dermatología"),
    ENDOCRINOLOGIA("Endocrinología"),
    GASTROENTEROLOGIA("Gastroenterología"),
    GINECOLOGIA("Ginecología"),
    NEFROLOGIA("Nefrología"),
    NEUROLOGIA("Neurología"),
    ONCOLOGIA("Oncología"),
    PEDIATRIA("Pediatría"),
    PSIQUIATRIA("Psiquiatría"),
    REUMATOLOGIA("Reumatología"),
    TRAUMATOLOGIA("Traumatología"),
    UROLOGIA("Urología");

    private final String nombre;
}
