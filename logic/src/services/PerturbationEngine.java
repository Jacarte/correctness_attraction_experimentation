package services;

import services.engine.*;
import services.interpolator.SinglePerturbationExplorer;
import services.utils.CommandLineParser;
import services.utils.IServiceProvider;
import services.utils.ServiceProvider;
import services.utils.StaticUtils;

import java.util.*;

public class PerturbationEngine implements IPerturbationEngine {


    List<IPerturbationPoint> pbis = new ArrayList<>();

    Map<IPerturbationPoint, Integer> accessCount = new TreeMap<>();

    Random r = new Random(0);



    @Override
    public void makeSpace(ISpaceExplorer.ICallback callback, ISpaceExplorer.IAnswerChecker checker, ISpaceExplorer.IExpectedProvider provider, ISpaceExplorer.IInputProvider inputProvider) {

        getExplorer().makeSpace(pbis, callback, this, checker, provider, inputProvider, ServiceProvider.getProvider().getLogger());
    }

    @Override
    public void makeSpace(ISpaceExplorer.IManager manager, ISpaceExplorer.IInputProvider inputProvider) {
        makeSpace(manager, manager, manager, inputProvider);
    }

    @Override
    public ISpaceExplorer getExplorer() {
        return new SinglePerturbationExplorer();
    }

    public int pInt(IIntegerPerturbationPoint pbi, int value){

        registerAccess(pbi);

        return pbi.getValue(value);
    }


    private void registerAccess(IPerturbationPoint pbi){

        if(StaticUtils.serviceProvider.getExecutionPolicy() == IServiceProvider.ExectionPolicy.REGISTER_ACCESS)
            if(accessCount.containsKey(pbi))
                accessCount.put(pbi, accessCount.get(pbi) + 1);
            else
                accessCount.put(pbi, 1);

    }

    public boolean pBool(IBooleanPerturbationPoint pbi, boolean value){

        registerAccess(pbi);

        boolean val = pbi.getValue(value);

        return val;
    }

    @Override
    public void addPbi(IPerturbationPoint pbi) {
        this.pbis.add(pbi);
    }

    @Override
    public boolean isPoneEnabled() {
        return true;
    }

    @Override
    public boolean isPBoolEnabled() {
        return false;
    }

    @Override
    public int getExecutionTimeout() {
        return Integer.parseInt(CommandLineParser.CONFIG.get("time"));
    }

    @Override
    public void watchThread(Thread t, OnInterruptCallback callback) {

    }


    String fileName;

    @Override
    public String getFilename() {
        return fileName;
    }

    @Override
    public void setFileName(String value) {
        fileName = value;
    }

    @Override
    public void reset() {
        pbis.clear();
    }

    @Override
    public void resetAccessCount() {
        accessCount.clear();
    }

    @Override
    public Map<IPerturbationPoint, Integer> getAccessCount() {
        return accessCount;
    }

}
