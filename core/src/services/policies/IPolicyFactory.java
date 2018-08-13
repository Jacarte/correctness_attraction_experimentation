package services.policies;

public interface IPolicyFactory {
     <T> IPolicy<T> getPolicy(Object type);
}
