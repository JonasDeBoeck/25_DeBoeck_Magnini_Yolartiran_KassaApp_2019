package model.state;

import model.Artikel;
import model.Winkelwagentje;

import java.util.List;

public class Geannuleerd implements State{
    private Winkelwagentje winkelwagentje;

    public Geannuleerd(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public void maakLeeg(List<Artikel> artikels) {
        artikels.clear();
    }

    @Override
    public String toString() {
        return "State: geannuleerd";
    }
}
