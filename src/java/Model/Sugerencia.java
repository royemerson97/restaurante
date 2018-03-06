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
public class Sugerencia {
    private int id;
    private int id_usuario;
    private String sugerencia;
    private int publicada;

    public Sugerencia(int id, int id_usuario, String sugerencia, int publicada) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.sugerencia = sugerencia;
        this.publicada = publicada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getSugerencia() {
        return sugerencia;
    }

    public void setSugerencia(String sugerencia) {
        this.sugerencia = sugerencia;
    }

    public int getPublicada() {
        return publicada;
    }

    public void setPublicada(int publicada) {
        this.publicada = publicada;
    }
    
    
}
