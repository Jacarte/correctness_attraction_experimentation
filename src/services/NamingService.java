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
        return "PerturbationEngine.getInstance().pInt";
    }

    @Override
    public String getPBoolMethodName() {
        return "PerturbationEngine.getInstance().pBool";
    }

    @Override
    public String getNextPerturbationName() {
        return "L_" + perturbationIndex++;
    }

    @Override
    public String getPerturbationNamespace() {
        return "api";
    }

    @Override
    public String getIntegerPerturbationClassName() {
        return "IntegerPerturbationPoint";
    }

    @Override
    public String getBooleanPerturbationClassName() {
        return "BooleanPerturbationPoint";
    }

    @Override
    public int getPerturbationInstanceModifiers() {
        return 9; // public static
    }

    @Override
    public String getInstrumentationSuffix() {
        return "Intr";
    }

}
