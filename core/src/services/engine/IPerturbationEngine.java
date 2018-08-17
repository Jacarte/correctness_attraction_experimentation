package services.engine;

import java.util.ArrayList;
import java.util.List;

public interface IPerturbationEngine {

        void makeSpace(ISpaceExplorer.ICallback callback, ISpaceExplorer.IAnswerChecker checker, ISpaceExplorer.IExpectedProvider provider, ISpaceExplorer.IInputProvider inputProvider);

        void makeSpace(ISpaceExplorer.IManager manager, ISpaceExplorer.IInputProvider inputProvider);


        ISpaceExplorer getExplorer();

        int pInt(IIntegerPerturbationPoint pbi, int value);

        boolean pBool(IBooleanPerturbationPoint pbi, boolean value);

        void addPbi(IPerturbationPoint pbi);

        boolean isPoneEnabled();

        boolean isPBoolEnabled();

        int getExecutionTimes(IPerturbationPoint pbi);

        int getExecutionTimeout();

        void watchThread(Thread t, OnInterruptCallback callback);

        interface OnInterruptCallback{
                void action();
        }

}
