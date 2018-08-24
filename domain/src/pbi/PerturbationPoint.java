package pbi;

import services.engine.IPerturbationModel;
import services.engine.IPerturbationPoint;
import services.utils.IServiceProvider;

public abstract class PerturbationPoint implements IPerturbationPoint {

    protected String location;
    protected int index;

    protected IServiceProvider _serviceProvider;
    protected String originalExpression;

    protected int currentTime = 0;
    protected int perturbAt = -2;

    public PerturbationPoint(String location, int index, String originalExpression, IServiceProvider provider){
        _serviceProvider = provider;
        this.location = location;
        this.index = index;

        this.originalExpression = originalExpression;
    }


    @Override
    public String getName() {
        return location;
    }

    @Override
    public int getLine() {
        return index;
    }

    @Override
    public int getCol() {
        return index;
    }


    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PerturbationPoint))
            return false;

        return index == ((PerturbationPoint)obj).index;
    }

    @Override
    public String getOriginalExpression() {
        return originalExpression;
    }

    @Override
    public int compareTo(IPerturbationPoint o) {
        return index - o.getIndex() ;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void setTime(int time) {
        this.currentTime = time;
    }

    @Override
    public int getCurrentTime() {
        return currentTime;
    }

    @Override
    public void reset() {
        currentTime = 0;
    }
}
