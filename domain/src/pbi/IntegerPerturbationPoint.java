package pbi;


import services.engine.IIntegerPerturbationPoint;
import services.engine.IPerturbationEngine;
import services.engine.IPerturbationModel;
import services.interpolator.Interpolator;
import services.utils.IServiceProvider;

public class IntegerPerturbationPoint extends PerturbationPoint implements IIntegerPerturbationPoint {

    int step = 0;

    IServiceProvider serviceProvider;

    public IntegerPerturbationPoint( String location, int index, String originalExpression, IServiceProvider provider) {
        super(location, index, originalExpression, provider);

        this.serviceProvider = _serviceProvider;

        if(provider.getPerturbationEngine().isPoneEnabled())
            provider.getPerturbationEngine().addPbi(this);
    }

    public int getValue(int value){

        this.setTime(this.getCurrentTime() + 1);

        return (perturbAt == this.getCurrentTime() - 1? step: 0) + value;
    }

    @Override
    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public void perturb(IPerturbationModel model, int time) {
        this.perturbAt = time;
        model.accept(this);
    }

    @Override
    public void reset(IPerturbationModel model) {
        this.setTime(0);
        this.perturbAt = -2;
        model.reset(this);
    }

}
