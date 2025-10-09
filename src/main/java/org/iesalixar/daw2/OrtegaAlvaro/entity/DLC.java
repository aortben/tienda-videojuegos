package org.iesalixar.daw2.OrtegaAlvaro.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DLC {
    // Getters y setters
    private int id;
    private String name;
    private double price;
    private int videogameId;
    private String videogameTitle; // Nuevo campo

    public DLC() {}

    // Constructor para insert/update
    public DLC(int id, String name, double price, int videogameId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.videogameId = videogameId;
    }

    public DLC(String name, double price, int videogameId) {
        this.name = name;
        this.price = price;
        this.videogameId = videogameId;
    }

}

