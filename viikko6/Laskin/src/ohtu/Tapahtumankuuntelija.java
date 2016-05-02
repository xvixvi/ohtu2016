package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Tapahtumankuuntelija implements ActionListener {

    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private HashMap<JButton, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Plus(sovellus, syotekentta, tuloskentta));
        komennot.put(miinus, new Miinus(sovellus, syotekentta, tuloskentta));
        komennot.put(nollaa, new Nollaa(sovellus, syotekentta, tuloskentta));
        edellinen = null;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Komento komento = komennot.get(ae.getSource());
        if (komento != null) {
            komento.suorita();
            edellinen = komento;
        } else {
            // toiminto oli undo
            edellinen.peru();
            edellinen = null;
        }

        int laskunTulos = sovellus.tulos();

        nollaa.setEnabled(laskunTulos != 0);
        undo.setEnabled(edellinen != null);
    }

    private class Miinus extends Komento {

        private Miinus(Sovelluslogiikka sovellus, JTextField syotekentta, JTextField tulosField) {
            super(sovellus, syotekentta, tulosField);
        }

        @Override
        public void suorita() {
            setEdellinenTulos(sovellus.tulos());
            sovellus.miinus(arvo());
            super.suorita();
        }
    }

    private class Nollaa extends Komento {

        private Nollaa(Sovelluslogiikka sovellus, JTextField syotekentta, JTextField field) {
            super(sovellus, syotekentta, field);
        }

        @Override
        public void suorita() {
            setEdellinenTulos(sovellus.tulos());

            sovellus.nollaa();
            super.suorita();
        }
    }

    private class Plus extends Komento {

        public Plus(Sovelluslogiikka sovellus, JTextField field, JTextField field1) {
            super(sovellus, field, field1);
        }

        @Override
        public void suorita() {
            setEdellinenTulos(sovellus.tulos());

            sovellus.plus(arvo());
            super.suorita();
        }
    }
}
