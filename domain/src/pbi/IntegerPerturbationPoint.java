package pbi;


import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationEngine;

public class IntegerPerturbationPoint extends PerturbationPoint implements IIntegerPerturbationPoint {



    public IntegerPerturbationPoint(String location, int index){
        this.index = index;
        this.location = location;

        //IPerturbationEngine.getInstance().pbis.add(this);

    }

    int step = 0;

    public int getValue(int value){
        return step + value;
    }

}
