package services.api;

import java.util.List;

public interface INamingService {

    int getPbiIndex();

    String getPIntMethodName();

    String getPBoolMethodName();

    String getNextPerturbationName();

    List<String> getPerturbationNamespace();

    String getIntegerPerturbationClassName();

    String getBooleanPerturbationClassName();

    int getPerturbationInstanceModifiers();

    String getInstrumentationSuffix();

    String engineName();
}
