package org.iesalixar.daw2.OrtegaAlvaro.entity;

public class DLC {
    private int id;
    private String name;
    private double price;
    private int videogameId; // Relación con Videogame

    public DLC() {}

    public DLC(String name, double price, int videogameId) {
        this.name = name;
        this.price = price;
        this.videogameId = videogameId;
    }

    public DLC(int id, String name, double price, int videogameId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.videogameId = videogameId;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getVideogameId() { return videogameId; }
    public void setVideogameId(int videogameId) { this.videogameId = videogameId; }
}

