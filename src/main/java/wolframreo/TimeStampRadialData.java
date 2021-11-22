package wolframreo;

import java.util.Arrays;

public class TimeStampRadialData {
    double flBase;
    double[] zBase;
    double dZfl;
    double[] dZ;

    public TimeStampRadialData (double[] zBase,double[] dZ, double flBase, double dZfl){
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

    public double getFlBase() {
        return flBase;
    }

    public double[] getzBase() {
        return zBase;
    }

    public double getdZfl() {
        return dZfl;
    }

    public double[] getdZ() {
        return dZ;
    }

}
