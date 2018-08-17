package services.engine;

import services.api.INamingService;

import java.util.Arrays;
import java.util.List;

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
        return engineName() + ".pInt";
    }


    @Override
    public String engineName() {
        return "pE";
    }

    @Override
    public void reset() {
        perturbationIndex = 0;
    }

    @Override
    public String getPBoolMethodName() {
        return engineName() + ".pBool";
    }

    @Override
    public String getNextPerturbationName() {
        return "L_" + perturbationIndex++;
    }

    @Override
    public List<String> getPerturbationNamespace() {
        return Arrays.asList("services", "services.utils", "services.engine", "pbi");
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

