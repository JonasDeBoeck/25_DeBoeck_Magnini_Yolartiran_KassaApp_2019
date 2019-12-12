package model;

import java.util.List;
import java.util.Stack;

public class Betaald implements State{
    private Winkelwagentje winkelwagentje;

    public Betaald(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public void maakLeeg(List<Artikel> artikels) {
        artikels.clear();
    }

    @Override
    public String toString() {
        return "State: Betaald";
    }
}
