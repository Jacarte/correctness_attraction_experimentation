package services;

public interface INamingService {

    int getPbiIndex();

    String getPIntMethodName();

    String getPBoolMethodName();

    String getNextPerturbationName();

    String getPerturbationNamespace();

    String getIntegerPerturbationClassName();

    String getBooleanPerturbationClassName();

    int getPerturbationInstanceModifiers();

    String getInstrumentationSuffix();
}
