package api.interpolator;

public interface Interpolator<Tr, Tvalue> {

    Tr interpolate(Tvalue value);


}
