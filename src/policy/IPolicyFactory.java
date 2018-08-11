package policy;

public interface IPolicyFactory {
     <T> IPolicy<T> getPolicy(Object type);
}
