package services.engine;

import services.interpolator.Interpolator;

public interface IPerturbationPoint extends Comparable<IPerturbationPoint> {

    String getName();

    int getLine();

    int getCol();

    int getIndex();

    String getOriginalExpression();

    void perturb(IPerturbationModel model, int time);

    void reset(IPerturbationModel model);

    void setTime(int time);

    int getCurrentTime();

    void reset();
}
