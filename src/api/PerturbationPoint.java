package api;

public abstract class PerturbationPoint {

    protected String location;
    protected int index;


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PerturbationPoint))
            return false;

        return index == ((PerturbationPoint)obj).index;
    }
}
