package experiment;

import services.engine.IBooleanPerturbationPoint;
import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationModel;
import services.engine.IPerturbationPoint;

public class POneModel implements IPerturbationModel {


    @Override
    public void accept(IIntegerPerturbationPoint pbi) {

        pbi.setStep(1);
    }

    @Override
    public void accept(IBooleanPerturbationPoint pbi) {

    }

    @Override
    public void reset(IBooleanPerturbationPoint pbi) {

    }

    @Override
    public void reset(IIntegerPerturbationPoint pbi) {

        pbi.setStep(0);
    }
}
