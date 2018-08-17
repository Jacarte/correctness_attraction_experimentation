package services;

import pbi.BooleanPerturbationPoint;
import pbi.IntegerPerturbationPoint;
import pbi.PerturbationPoint;
import services.engine.*;
import services.interpolator.IntegerArrayInputProvider;
import services.interpolator.SinglePerturbationExplorar;
import services.utils.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class PerturbationEngine implements IPerturbationEngine {


    List<IPerturbationPoint> pbis = new ArrayList<>();

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
        return new SinglePerturbationExplorar();
    }

    public int pInt(IIntegerPerturbationPoint pbi, int value){

        return pbi.getValue(value);
    }

    public boolean pBool(IBooleanPerturbationPoint pbi, boolean value){

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
    public int getExecutionTimes(IPerturbationPoint pbi) {
        return 150;
    }

    @Override
    public int getExecutionTimeout() {
        return 10000;
    }

    @Override
    public void watchThread(Thread t, OnInterruptCallback callback) {
        new Thread(){
            @Override
            public void run() {

                long t0 = System.currentTimeMillis();

                while(true){

                    long delta = System.currentTimeMillis() - t0;

                    if(delta > getExecutionTimeout()){
                        if(t.isAlive()) {

                            t.stop();
                            break;
                        }

                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
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

}
