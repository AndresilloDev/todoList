package utez.edu.mx.Tasks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private String name;
    private String description;
    private String date;

    @JsonProperty("isPending")
    private boolean isPending;

    public Task(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.isPending = true;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public boolean isPending() {
        return isPending;
    }

    public void toggleStatus() {
        this.isPending = !this.isPending;
    }

    @Override
    public String toString() {
        return
                "Nombre: " + this.name + '\n' +
                        "Descripción: " + this.description + '\n' +
                        "Fecha: " + this.date + '\n' +
                        "Estado: " + (this.isPending ? "Pendiente" : "Hecho") + "\n\n";
    }
}
