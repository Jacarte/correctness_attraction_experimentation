package api;

import api.interpolator.SequencialInterpolator;

import java.util.*;

public class PerturbationEngine {

    static PerturbationEngine instance;

    List<PerturbationPoint> pbis = new ArrayList<>();

    public static PerturbationEngine getInstance(){
        if(instance == null) instance = new PerturbationEngine();

        return instance;
    }

    public void makeSpace(){
        
    }

    public int pInt(IntegerPerturbationPoint pbi, int value){
        return pbi.getValue(value);
    }

    public boolean pBool(BooleanPerturbationPoint pbi, boolean value){
        return value;
    }
}
