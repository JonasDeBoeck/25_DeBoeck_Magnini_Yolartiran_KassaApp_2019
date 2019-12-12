package model;

public class Inactief implements State {

    private Winkelwagentje winkelwagentje;
    public Inactief(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public void betaal() {
        winkelwagentje.setState(this.winkelwagentje.getBetaald());
    }

    @Override
    public void annuleer() {
        winkelwagentje.setState(this.winkelwagentje.getGeannuleerd());
    }

    @Override
    public String toString() {
        return "staat op inactief";
    }
}
