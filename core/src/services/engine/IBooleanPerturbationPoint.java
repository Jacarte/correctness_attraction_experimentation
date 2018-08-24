package services.engine;

public interface IBooleanPerturbationPoint extends IPerturbationPoint {


    boolean getValue(boolean original);

    void setAction(IBooleanPertubationAction action);

    interface IBooleanPertubationAction{
        boolean perturb(boolean original);
    }
}
