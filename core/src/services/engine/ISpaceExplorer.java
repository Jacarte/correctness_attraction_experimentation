package services.engine;

import java.util.List;

public interface ISpaceExplorer {

    <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis,
                               ICallback<Tin, Tout> callback,
                               IPerturbationEngine engine,
                               IAnswerChecker<Tout> checker,
                               IExpectedProvider<Tin, Tout> provider,
                               IInputProvider<Tin> inputProvider,
                               ILogger logger);
    <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis,
                               IPerturbationEngine engine,
                               IManager<Tin, Tout> manager,
                               IInputProvider<Tin> inputProvider,
                               ILogger logger);



    interface ICallback<Tin, Tout>{
        Tout _do(Tin in);
    }

    interface IAnswerChecker<Tout>{
        boolean _do(Tout result, Tout expected);
    }

    interface IExpectedProvider<Tin, Tout>{
        Tout get(Tin in);
    }

    interface IInputProvider<Tin>{

        boolean canNext();

        Tin getIn();

        int getSize();
    }

    interface IManager<Tin, Tout> extends ICallback<Tin, Tout>, IExpectedProvider<Tin, Tout>, IAnswerChecker<Tout> {

    }

    class PbiSummary{
        public int errorCount;
        public int successCount;
        public int wrongCount;


        @Override
        public String toString() {
            return String.format("e:%s s:%s w:%s", errorCount, successCount, wrongCount);
        }
    }
}
