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
public class Comida {
    private int id;
    private int id_tipo_comida;
    private String comida;
    private float precio;

    public Comida(int id, int id_tipo_comida, String comida, float precio) {
        this.id = id;
        this.id_tipo_comida = id_tipo_comida;
        this.comida = comida;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tipo_comida() {
        return id_tipo_comida;
    }

    public void setId_tipo_comida(int id_tipo_comida) {
        this.id_tipo_comida = id_tipo_comida;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
