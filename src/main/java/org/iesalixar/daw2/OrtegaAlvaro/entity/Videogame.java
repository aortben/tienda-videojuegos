package org.iesalixar.daw2.OrtegaAlvaro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videogame {
    private int id;
    private String title;
    private String genre;
    private String platform;
    private double price;

    public Videogame(String title, String genre, String platform, double price) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
    }
}


