package pbi;


import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationEngine;
import services.interpolator.Interpolator;
import services.utils.IServiceProvider;

public class IntegerPerturbationPoint extends PerturbationPoint implements IIntegerPerturbationPoint {

    int step = 0;

    public IntegerPerturbationPoint(String location, int index, String originalExpression, IServiceProvider provider) {
        super(location, index, originalExpression, provider);

        if(provider.getPerturbationEngine().isPoneEnabled())
            provider.getPerturbationEngine().addPbi(this);
    }

    public int getValue(int value){
        return step + value;
    }

    @Override
    public void setStep(int step) {
        this.step = step;
    }


    @Override
    public void reset() {
        this.step = 0;
        getInterpolator().reset();
    }

    @Override
    public boolean canPerturb(IPerturbationEngine engine) {
        return getInterpolator().canNext();
    }

    @Override
    public void next() {

        this.step = (int)getInterpolator().getNext();
    }

    @Override
    public Object getPerturbationValue() {
        return step;
    }


    Interpolator interpolator;

    @Override
    public Interpolator getInterpolator() {

        if(interpolator == null)
            interpolator = _serviceProvider.getIntegerInterpolator();

        return interpolator;
    }

}
