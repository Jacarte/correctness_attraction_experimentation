package pbi;

import services.engine.IPerturbationPoint;
import services.utils.IServiceProvider;

public abstract class PerturbationPoint implements IPerturbationPoint {

    protected String location;
    protected int index;

    protected IServiceProvider _serviceProvider;

    public PerturbationPoint(String location, int index, IServiceProvider provider){
        _serviceProvider = provider;
        this.location = location;
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PerturbationPoint))
            return false;

        return index == ((PerturbationPoint)obj).index;
    }
}
