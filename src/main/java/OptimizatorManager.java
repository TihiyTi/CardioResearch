import wolframreo.CycleRadialData;
import wolframreo.SignalRadialData;
import wolframreo.TimeStampRadialData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptimizatorManager {
    OneSphereOptimizator opt = new OneSphereOptimizator();
    SignalRadialData data;
    boolean useZOptimum = false;

    public OptimizatorManager(){
        opt.setVariator(new OptVariator());
    }
    public OptimizatorManager(OptVariator.VariatorType type){
        opt.setVariator(new OptVariator(type));
    }

    public void setHeartParameters(double[][] atrialPoints, double[][] electrodSystemPoints){
        opt.setHeartParameters(atrialPoints, electrodSystemPoints);
    }

    public void setExperimentalData(SignalRadialData data){
        this.data = data;
    }

    public void setModelParameters(double[][] parametersByParam, double[] ro1, double ro2, double h){
        opt.setParameters(parametersByParam, ro1, ro2, h);
    }

    //TODO 26.12.2018 Alexey: сделать расчет через точки предсердий и желудочков
    public void setEqualSphere(double[] equalSphere){
        opt.setEqualSphereXYR(equalSphere);
    }


    public List<List<OptimizeElement>> optimize(){
        return data.getCycleRadialData().stream().map(this::optimizeByCycle).collect(Collectors.toList());
    }


    public List<OptimizeElement> optimizeByCycle(CycleRadialData cycleRadialData){
        return IntStream.range(0,25).mapToObj(x -> optimizeByTimeStamp(cycleRadialData.getDataAtTime(x)))
                .collect(Collectors.toList());
    }
    public List<OptimizeElement> optimizeByCycleForEach(CycleRadialData cycleRadialData){
        List<OptimizeElement> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            list.add(optimizeByTimeStamp(cycleRadialData.getDataAtTime(i)));
        }
        return list;
    }

    public OptimizeElement optimizeByTimeStamp(TimeStampRadialData timeStampRadialData){
        opt.setTimeStampRadialData(timeStampRadialData);
        return useZOptimum ? opt.zOptimize() : opt.optimize();
    }


}
