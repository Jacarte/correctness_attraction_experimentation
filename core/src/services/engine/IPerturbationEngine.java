package services.engine;

import java.util.ArrayList;
import java.util.List;

public interface IPerturbationEngine {

        void makeSpace();

        int pInt(IIntegerPerturbationPoint pbi, int value);
        boolean pBool(IBooleanPerturbationPoint pbi, boolean value);

}
