package pbi;

import services.engine.IBooleanPerturbationPoint;
import services.engine.IPerturbationEngine;
import services.interpolator.Interpolator;
import services.utils.IServiceProvider;

public class BooleanPerturbationPoint extends PerturbationPoint implements IBooleanPerturbationPoint {


    public BooleanPerturbationPoint(String location, int index, IServiceProvider provider) {
        super(location, index, provider);

        provider.getPerturbationEngine().addPbi(this);
    }

    public void initSpace(boolean initial){

    }

    @Override
    public void reset() {

    }

    @Override
    public boolean canPerturb(IPerturbationEngine engine) {
        return true;
    }

    @Override
    public void next() {

    }

    @Override
    public Interpolator getInterpolator() {
        return null;
    }
}
