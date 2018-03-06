/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Roy
 */
public class Receta {
    private int id;
    private String titulo;
    private int id_usuario;
    private String ingredientes;
    private String preparacion;
    private int publicada;

    public Receta(int id, String titulo, int id_usuario, String ingredientes, String preparacion, int publicada) {
        this.id = id;
        this.titulo = titulo;
        this.id_usuario = id_usuario;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.publicada = publicada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public int getPublicada() {
        return publicada;
    }

    public void setPublicada(int publicada) {
        this.publicada = publicada;
    }
    
    
}
