package services.engine;

import java.util.Map;

public interface ISummariesCollector {

    WholeSummary getWholeSummary(Map<IPerturbationPoint, ISpaceExplorer.PbiSummary> summaries);

    class WholeSummary {

        public Map<IPerturbationPoint, PointSummary> proportions;

        public int totalSuccess;
        public double correctnessRatio;

    }

    class PointSummary{

        public double correctProportion;
        public double brokenProportion;
        public double errorProportion;

    }

}
