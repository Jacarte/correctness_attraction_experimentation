package services.engine;

public interface IPerturbationModel {

    void accept(IIntegerPerturbationPoint pbi);

    void accept(IBooleanPerturbationPoint pbi);

    void reset(IBooleanPerturbationPoint pbi);

    void reset(IIntegerPerturbationPoint pbi);
}
