package pbi;


import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationEngine;
import services.interpolator.Interpolator;
import services.utils.IServiceProvider;

public class IntegerPerturbationPoint extends PerturbationPoint implements IIntegerPerturbationPoint {

    int step = 0;

    public IntegerPerturbationPoint(String location, int index, IServiceProvider provider) {
        super(location, index, provider);

        provider.getPerturbationEngine().addPbi(this);
    }

    public int getValue(int value){

        System.out.println(this.location + "==================");
        System.out.println(String.valueOf(this.step));
        System.out.println(String.valueOf(value));

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

    Interpolator interpolator;

    @Override
    public Interpolator getInterpolator() {

        if(interpolator == null)
            interpolator = _serviceProvider.getIntegerInterpolator();

        return interpolator;
    }

}
