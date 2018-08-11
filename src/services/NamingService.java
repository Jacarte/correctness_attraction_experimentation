package services;

public class NamingService implements INamingService {


    int perturbationIndex = 0;

    public NamingService(){

    }

    @Override
    public int getPbiIndex() {
        return perturbationIndex;
    }

    @Override
    public String getPIntMethodName() {
        return "pInt";
    }

    @Override
    public String getPBoolMethodName() {
        return "pBool";
    }

    @Override
    public String getNextPerturbationName() {
        return "L_" + perturbationIndex++;
    }

    @Override
    public String getPerturbationNamespace() {
        return "";
    }

    @Override
    public String getPerturbationClassName() {
        return "PerturbationPoint";
    }

    @Override
    public int getPerturbationInstanceModifiers() {
        return 9; // public static
    }

}
