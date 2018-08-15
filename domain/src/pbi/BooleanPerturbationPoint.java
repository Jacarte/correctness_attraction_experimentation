package pbi;

import services.engine.IBooleanPerturbationPoint;
import services.engine.IPerturbationEngine;
import services.interpolator.IBooleanInterpolator;
import services.interpolator.Interpolator;
import services.utils.IServiceProvider;
import services.utils.StaticUtils;

public class BooleanPerturbationPoint extends PerturbationPoint implements IBooleanPerturbationPoint {


    public BooleanPerturbationPoint(String location, int index, String originalExpression, IServiceProvider provider) {
        super(location, index, originalExpression, provider);

        provider.getPerturbationEngine().addPbi(this);
    }

    @Override
    public void reset() {
        interpolator.reset();
    }

    @Override
    public boolean canPerturb(IPerturbationEngine engine) {

        return interpolator.canNext();
    }

    @Override
    public void next() {
        interpolator.incrementInSpace();
    }

    @Override
    public Object getPerturbationValue() {
        return interpolator.getPerturbationOperation();
    }

    IBooleanInterpolator interpolator = StaticUtils.serviceProvider.getBooleanInterpolator();

    @Override
    public Interpolator getInterpolator() {
        return interpolator;
    }

    @Override
    public boolean getValue(boolean original) {
        return interpolator.getValue(original);
    }
}
