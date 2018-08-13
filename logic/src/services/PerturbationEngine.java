package services;

import pbi.BooleanPerturbationPoint;
import pbi.IntegerPerturbationPoint;
import pbi.PerturbationPoint;
import services.engine.*;
import services.interpolator.SinglePerturbationExplorar;

import java.util.ArrayList;
import java.util.List;

public class PerturbationEngine implements IPerturbationEngine {


    List<IPerturbationPoint> pbis = new ArrayList<>();

    @Override
    public void makeSpace(ISpaceExplorer.ICallback callback) {

        getExplorer().makeSpace(pbis, callback, this);
    }

    @Override
    public ISpaceExplorer getExplorer() {
        return new SinglePerturbationExplorar();
    }

    public int pInt(IIntegerPerturbationPoint pbi, int value){

        return pbi.getValue(value);
    }

    public boolean pBool(IBooleanPerturbationPoint pbi, boolean value){
        return value;
    }

    @Override
    public void addPbi(IPerturbationPoint pbi) {
        this.pbis.add(pbi);
    }

}
