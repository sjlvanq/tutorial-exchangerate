package dto;

// Record para manejar un Json del tipo "{nombre: 'elquino', anio: 2025}"
// Record es una clase inmutable a la que no es necesario
// declararle explícitamente setters y getters.
public record Compa (String nombre, int anio) { }
