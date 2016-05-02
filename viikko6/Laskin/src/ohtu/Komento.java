/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author xvixvi
 */
public abstract class Komento {

    private final Sovelluslogiikka sovellus;
    private final JTextField syotekentta;
    private int edellinenTulos = 0;
    private final JTextField tuloskentta;

    public Komento(Sovelluslogiikka sovellus, JTextField syotekentta, JTextField tuloskentta) {
        this.sovellus = sovellus;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
        
    }
    public void suorita() {
        syotekentta.setText("");
        tuloskentta.setText("" + sovellus.tulos());
    }

    public void setEdellinenTulos(int edellinenTulos) {
        this.edellinenTulos = edellinenTulos;
    }
    
    
    
    public int arvo() {
        int arvo = 0;
        
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            
        }
        
        return arvo;
    }
    
    public void peru() {
        tuloskentta.setText("" + edellinenTulos);
    }
}

