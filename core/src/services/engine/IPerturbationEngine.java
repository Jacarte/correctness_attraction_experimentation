package services.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IPerturbationEngine {

        void makeSpace(ISpaceExplorer.ICallback callback, ISpaceExplorer.IAnswerChecker checker, ISpaceExplorer.IExpectedProvider provider, ISpaceExplorer.IInputProvider inputProvider);

        <Tin, Tout> void makeSpace(ISpaceExplorer.IManager<Tin, Tout> manager, ISpaceExplorer.IInputProvider<Tin> inputProvider);


        ISpaceExplorer getExplorer();

        int pInt(IIntegerPerturbationPoint pbi, int value);

        boolean pBool(IBooleanPerturbationPoint pbi, boolean value);

        void addPbi(IPerturbationPoint pbi);

        boolean isPoneEnabled();

        boolean isPBoolEnabled();

        int getExecutionTimeout();

        void watchThread(Thread t, OnInterruptCallback callback);

        interface OnInterruptCallback{
                void action();
        }

        String getFilename();

        void setFileName(String value);

        void reset();

        void resetAccessCount();

        Map<IPerturbationPoint, Integer> getAccessCount();


}
