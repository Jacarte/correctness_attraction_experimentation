package pbi;

import services.engine.IBooleanPerturbationPoint;
import services.engine.IPerturbationEngine;
import services.engine.IPerturbationModel;
import services.interpolator.IBooleanInterpolator;
import services.interpolator.Interpolator;
import services.utils.IServiceProvider;
import services.utils.StaticUtils;

public class BooleanPerturbationPoint extends PerturbationPoint implements IBooleanPerturbationPoint {


    IBooleanPertubationAction action;

    public BooleanPerturbationPoint(String location, int index, String originalExpression, IServiceProvider provider) {
        super(location, index, originalExpression, provider);

        if (provider.getPerturbationEngine().isPBoolEnabled())
            provider.getPerturbationEngine().addPbi(this);
    }

    @Override
    public boolean getValue(boolean original) {

        if(action != null)
            return action.perturb(original);

        return original;
    }

    @Override
    public void setAction(IBooleanPertubationAction action) {
        this.action = action;
    }

    @Override
    public void perturb(IPerturbationModel model, int time) {
        model.accept(this);
    }

    @Override
    public void reset(IPerturbationModel model) {
        model.reset(this);
    }
}

