package api;

import api.interpolator.Interpolator;

public class IntegerPerturbationPoint extends PerturbationPoint {



    public IntegerPerturbationPoint(String location, int index){
        this.index = index;
        this.location = location;

        PerturbationEngine.getInstance().pbis.add(this);

    }

    int step = 0;

    public int getValue(int value){
        return step + value;
    }

}
