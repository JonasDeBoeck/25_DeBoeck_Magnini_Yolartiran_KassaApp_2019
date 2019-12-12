package model;

public class OnHold implements State {

    private Winkelwagentje winkelwagentje;
    public OnHold(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public void zetOffHold() {
        winkelwagentje.setState(this.winkelwagentje.getActief());
    }

    @Override
    public void verwijderKarOnHold() {
        winkelwagentje.setState(this.winkelwagentje.getGeannuleerd());
    }

    @Override
    public String toString() {
        return "State: OnHold";
    }
}
