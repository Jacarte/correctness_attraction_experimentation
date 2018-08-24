package experiment;

import services.engine.IBooleanPerturbationPoint;
import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationModel;

public class PBoolModel implements IPerturbationModel {
    @Override
    public void accept(IIntegerPerturbationPoint pbi) {

    }

    @Override
    public void accept(IBooleanPerturbationPoint pbi) {
        pbi.setAction(o -> !o);
    }

    @Override
    public void reset(IBooleanPerturbationPoint pbi) {
        pbi.setAction(null);
    }

    @Override
    public void reset(IIntegerPerturbationPoint pbi) {

    }
}
