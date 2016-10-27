/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Andreu Luna Font
 */
public class Alumnos {
    private String nomAl;
    private String nomTut;
    private String nomAss;

    public Alumnos(String nomAl, String nomTut, String nomAss) {
        this.nomAl = nomAl;
        this.nomTut = nomTut;
        this.nomAss = nomAss;
    }

    public String getNomAl() {
        return nomAl;
    }

    public void setNomAl(String nomAl) {
        this.nomAl = nomAl;
    }

    public String getNomTut() {
        return nomTut;
    }

    public void setNomTut(String nomTut) {
        this.nomTut = nomTut;
    }

    public String getNomAss() {
        return nomAss;
    }

    public void setNomAss(String nomAss) {
        this.nomAss = nomAss;
    }
    

    
}
