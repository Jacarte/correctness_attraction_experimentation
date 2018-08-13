package services.engine;

public interface IIntegerPerturbationPoint extends IPerturbationPoint {

    int getValue(int original);

    void setStep(int step);
}
