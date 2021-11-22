package wolframreo;

import java.util.Arrays;
import java.util.stream.Stream;

public class CycleRadialData {

    double flBase;
    double[] zBase;
    double[] dZfl;
    double[][] dZ;

    public CycleRadialData(double[] flAndRadialBase, double[][] flAndRadialDz) {
        this.flBase = flAndRadialBase[0];
        this.zBase = Arrays.copyOfRange(flAndRadialBase, 1, 6);
        this.dZfl = flAndRadialDz[0];
        this.dZ = Arrays.copyOfRange(flAndRadialDz, 1, 6);
    }
    public CycleRadialData(double[] zBase,double[][] dZ, double flBase, double[] dZfl){
        this.flBase = flBase;
        this.dZfl = dZfl;
        this.dZ = dZ;
        this.zBase = zBase;
    }

    @Override
    public String toString(){
        return  " dZ: " + Arrays.toString(dZ) +
                " zase: " + Arrays.toString(zBase) +
                " dzFl: " + dZfl +
                " flBase: " + flBase;
    }

    public double[] getzBase() {
        return zBase;
    }

    public double[][] getdZ() {
        return dZ;
    }

    public double getFlBase() {
        return flBase;
    }

    public double[] getdZfl() {
        return dZfl;
    }

    public TimeStampRadialData getDataAtTime(int numOfTimeStamp){
        double[] dzAtTime = Stream.of(dZ).mapToDouble(x -> x[numOfTimeStamp]).toArray();
        return new TimeStampRadialData(zBase, dzAtTime, flBase, dZfl[numOfTimeStamp]);
    }
}
