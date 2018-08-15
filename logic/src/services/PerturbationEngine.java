package services;

import pbi.BooleanPerturbationPoint;
import pbi.IntegerPerturbationPoint;
import pbi.PerturbationPoint;
import services.engine.*;
import services.interpolator.SinglePerturbationExplorar;
import services.utils.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class PerturbationEngine implements IPerturbationEngine {


    List<IPerturbationPoint> pbis = new ArrayList<>();

    @Override
    public void makeSpace(ISpaceExplorer.ICallback callback, ISpaceExplorer.IAnswerChecker checker, ISpaceExplorer.IExpectedProvider provider) {

        getExplorer().makeSpace(pbis, callback, this, checker, provider, ServiceProvider.getProvider().getLogger());
    }

    @Override
    public ISpaceExplorer getExplorer() {
        return new SinglePerturbationExplorar();
    }

    public int pInt(IIntegerPerturbationPoint pbi, int value){

        return pbi.getValue(value);
    }

    public boolean pBool(IBooleanPerturbationPoint pbi, boolean value){

        boolean val = pbi.getValue(value);

        return val;
    }

    @Override
    public void addPbi(IPerturbationPoint pbi) {
        this.pbis.add(pbi);
    }

}
