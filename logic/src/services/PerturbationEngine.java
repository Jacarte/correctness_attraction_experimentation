package services;

import pbi.BooleanPerturbationPoint;
import pbi.IntegerPerturbationPoint;
import pbi.PerturbationPoint;
import services.engine.IBooleanPerturbationPoint;
import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationEngine;

import java.util.ArrayList;
import java.util.List;

public class PerturbationEngine implements IPerturbationEngine {

    static PerturbationEngine instance;

    List<PerturbationPoint> pbis = new ArrayList<>();

    public static PerturbationEngine getInstance(){
        if(instance == null) instance = new PerturbationEngine();

        return instance;
    }

    public void makeSpace(){

    }

    public int pInt(IIntegerPerturbationPoint pbi, int value){
        return pbi.getValue(value);
    }

    public boolean pBool(IBooleanPerturbationPoint pbi, boolean value){
        return value;
    }
}
