package services;

public interface INamingService {

    int getPbiIndex();

    String getPIntMethodName();

    String getPBoolMethodName();

    String getNextPerturbationName();

    String getPerturbationNamespace();

    String getPerturbationClassName();

    int getPerturbationInstanceModifiers();
}
