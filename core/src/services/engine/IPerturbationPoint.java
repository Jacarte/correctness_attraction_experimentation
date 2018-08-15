package services.engine;

import services.interpolator.Interpolator;

public interface IPerturbationPoint {

    void reset();

    boolean canPerturb(IPerturbationEngine engine);

    String getName();

    int getLine();

    int getCol();

    void next();

    Object getPerturbationValue();

    Interpolator getInterpolator();

    int getIndex();

    String getOriginalExpression();
}
