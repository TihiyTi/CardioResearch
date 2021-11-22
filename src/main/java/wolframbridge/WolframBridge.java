package wolframbridge;

import com.wolfram.jlink.*;

import com.wolfram.jlink.Expr;
import com.wolfram.jlink.KernelLink;
import wolframreo.CycleRadialData;
import wolframreo.SignalRadialData;
import wolframreo.TimeStampRadialData;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WolframBridge {
    public static String path = "-linkmode launch -linkname 'C:/Program Files/Wolfram Research/Mathematica/12.2/MathKernel'";

    public static KernelLink wolframKernel = null;

    public static void main(String[] args) {
        if(wolframKernel == null){initKernel();}
        SignalRadialData data = w_SignalRadialData("Artem");
        IntStream.range(0,25).forEach(x->System.out.println(data.getCycleRadialData().get(0).getDataAtTime(x).toString()));
    }

    public static SignalRadialData w_SignalRadialData(String name){
        SignalRadialData signalRadialData = null;
        if(wolframKernel == null){initKernel();}
        try {
            doAndWait("SimpleExport[\""+name+"\"]");
            Expr e = wolframKernel.getExpr();
            int[] dimension = e.dimensions();
            int numOfCicle = dimension[0];
            List<CycleRadialData> listOfCycle = IntStream.range(1,numOfCicle+1).mapToObj(x -> parseCycleData(e.part(x))).collect(Collectors.toList());
            signalRadialData = new SignalRadialData(listOfCycle);
        } catch (MathLinkException e ) {
            e.printStackTrace();
        }
        return signalRadialData;

    }
    private static CycleRadialData parseCycleData(Expr e){
        try {
            double [] base = (double[])(e.part(2).asArray(Expr.REAL, 1));
            double [][] pulse = (double[][])e.part(1).asArray(Expr.REAL, 2);
            return new CycleRadialData( base, pulse);
        } catch (ExprFormatException e1) {
            e1.printStackTrace();
            return null;
        }
    }


    public static double[][] w_AtrialPoints(String name){
        try {
            doAndWait("AtrialPoints[\""+name+"\"]");
            return wolframKernel.getDoubleArray2();
        } catch (MathLinkException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static double[][] w_GetParam(String name){
        return Stream.of("a","b","R","h","x","y").map(x-> getDoubleArray("GetParam[\""+name+"\"][\""+x+"\"]")).toArray(double[][]::new);
    }

    public static double[][] w_NewGetParam(String name){
        return Stream.of("a","b","R","h","x","y").map(x-> getDoubleArray("GetNewParam[\""+name+"\"][\""+x+"\"]")).toArray(double[][]::new);
    }
    public static double[][] w_HeartContour(String name){
        try {
            doAndWait("HeartContours[\""+name+"\"]");
            return wolframKernel.getDoubleArray2();
        } catch (MathLinkException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static double[][] w_ElectrodSystemPoints(String name){
        return Arrays.stream(Objects.requireNonNull(w_HeartContour(name))).skip(1).limit(5).toArray(double[][]::new);
    }
    public static TimeStampRadialData w_GetRadialPoint(String name){
        double[] dZ = getDoubleArray("GetRadial[\""+name+"\"][\"dZRad\"]");
        double[] zBase = getDoubleArray("GetRadial[\""+name+"\"][\"zBase\"]");
        double flBase = getDouble("GetRadial[\""+name+"\"][\"flBase\"]");
        double dZfl = getDouble("GetRadial[\""+name+"\"][\"flDZ\"]");
        return new TimeStampRadialData(zBase, dZ, flBase, dZfl);
    }

    private static void doAndWait(String evaluate) throws MathLinkException {
        if(wolframKernel == null){initKernel();}
        wolframKernel.evaluate(evaluate);
        wolframKernel.waitForAnswer();
    }
    private static double[] getDoubleArray(String exp){
        if(wolframKernel == null){initKernel();}
        try {
            wolframKernel.evaluate(exp);
            wolframKernel.waitForAnswer();
            return wolframKernel.getDoubleArray1();
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    private static double getDouble(String exp){
        if(wolframKernel == null){initKernel();}
        try {
            wolframKernel.evaluate(exp);
            wolframKernel.waitForAnswer();
            return wolframKernel.getDouble();
        } catch (MathLinkException e) {
            System.out.println("Fatal error opening link: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    private static void initKernel(){
        try {
            wolframKernel = MathLinkFactory.createKernelLink(path);// подключаем ядро
            wolframKernel.discardAnswer();// дожидаемся загрузки ядра
        }
        catch (Exception e){
            System.out.println("Ядро не загружено");
            System.out.println(e.toString());
        }
    }

}
