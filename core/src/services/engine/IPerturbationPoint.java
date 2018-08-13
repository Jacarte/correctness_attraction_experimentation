package services.engine;

import services.interpolator.Interpolator;

public interface IPerturbationPoint {

    void reset();

    boolean canPerturb(IPerturbationEngine engine);

    void next();

    Interpolator getInterpolator();
}
