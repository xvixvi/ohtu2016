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
    private HashMap<String, Komento> komennot;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<String, Komento>();
        komennot.put("plus", new Plus(sovellus));
        komennot.put("miinus", new Miinus(sovellus));
        komennot.put("nollaa", new Nollaa(sovellus));
        komennot.put("tuntematon", new Tuntematon(sovellus));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            
        }
        Komento komento = komennot.get(ae);
        if (komento == null) {
            komento = komennot.get("tuntematon");
        }
        
        komento.suorita(arvo);

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        nollaa.setEnabled(laskunTulos != 0);
        undo.setEnabled(true);
    }

    private static class Miinus implements Komento {

        private final Sovelluslogiikka sovellus;

        public Miinus(Sovelluslogiikka sovellus) {
            this.sovellus = sovellus;
        }

        @Override
        public void suorita(int arvo) {
            sovellus.miinus(arvo);
        }
    }

    private static class Nollaa implements Komento {

        private final Sovelluslogiikka sovellus;

        public Nollaa(Sovelluslogiikka sovellus) {
            this.sovellus = sovellus;
        }

        @Override
        public void suorita(int arvo) {
            sovellus.nollaa();
        }
    }

    private static class Tuntematon implements Komento {

        private final Sovelluslogiikka sovellus;

        public Tuntematon(Sovelluslogiikka sovellus) {
            this.sovellus = sovellus;
        }

        @Override
        public void suorita(int arvo) {
            System.out.println("tuntematon komento");
        }
    }

    private class Plus implements Komento {

        private final Sovelluslogiikka sovellus;

        public Plus(Sovelluslogiikka sovellus) {
            this.sovellus = sovellus;
        }

        @Override
        public void suorita(int arvo) {
            sovellus.plus(arvo);
        }
    }

}
