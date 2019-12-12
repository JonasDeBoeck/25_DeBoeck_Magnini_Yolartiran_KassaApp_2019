package model;

public class Geannuleerd implements State{
    private Winkelwagentje winkelwagentje;

    public Geannuleerd(Winkelwagentje winkelwagentje){
        this.winkelwagentje = winkelwagentje;
    }

    @Override
    public String toString() {
        return "dood";
    }
}
