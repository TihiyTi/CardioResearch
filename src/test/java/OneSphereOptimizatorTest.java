import org.junit.Test;
import wolframbridge.WolframBridge;
import wolframreo.TimeStampRadialData;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public class OneSphereOptimizatorTest {

//    @Ignore
    @Test
    public void getOptimizeDeltaTest(){
        String name = "Artem";
        double[] equalSphere = new double[]{ 15, -6, 52 };
        TimeStampRadialData radialData = WolframBridge.w_GetRadialPoint(name);

        OneSphereOptimizator optimizator = new OneSphereOptimizator();
        optimizator.setVariator(new OptVariator());
        optimizator.setHeartParameters(WolframBridge.w_AtrialPoints(name) ,WolframBridge.w_ElectrodSystemPoints(name));
        optimizator.setTimeStampRadialData(radialData);
        optimizator.setEqualSphereXYR(equalSphere);

        optimizator.setParameters(WolframBridge.w_NewGetParam(name), new double[]{4.2,4.2,4.2,4.2,4.2}, 1.35, 0.03);
        optimizator.dro1 = 0.0142459;

        OptimizeElement optimum = optimizator.optimize();
        OptimizeElement zOptimum = optimizator.zOptimize(optimum);

//        for test
        double[][] atrialPoints = new double[][]{{-2., 35.}, {-31., 32.}, {-38., 0.}};
        double[][] parameters = new double[][]{
                {4.2, 1.35, 0.04, 0.02, /*0.037*/ 0.052, /*0.031*/0.03, 0.014, 0.044/*0.048*/},
                {4.2, 1.35, 0.04, 0.02, /*0.032*/0.052, /*0.025*/0.03, 0.013, 0.046/*0.047*/},
                {4.2, 1.35, 0.05, 0.025, /*0.047*/0.052, /*0.024*/0.03, 0.003, 0.03},
                {4.2, 1.35, 0.05, 0.025, /*0.039*/0.052, /*0.025*/0.03, -0.004, 0.061},
                {4.2, 1.35, 0.04, 0.02, /*0.037*/0.052, /*0.03*/0.03, -0.014, 0.031}};
        double[] dialtoleSphere = new double[]{15,-6,52};
//        double[][] contour = new double[][]{/*{-39, -22}, */{-37, -27}, {12, -47}, {67, -42}, {76, -11}, {41, 29}/*, {31, 32}*/};



        IntStream.range(0,3).forEach(i -> assertArrayEquals(atrialPoints[i], optimizator.atrialPoints[i], 0.1 ));
        IntStream.range(0,5).forEach(i -> assertArrayEquals(parameters[i], optimizator.parameters[i], 0.001 ));
        assertArrayEquals(dialtoleSphere, optimizator.equalSphereXYR, 0.01);
        assertArrayEquals(new double[]{-4.6, 2.4, 0, 6.6}, optimum.getAsArray(), 0.01);

//        IntStream.range(0,5).forEach(i -> assertArrayEquals(contour[i], optimizator.electrodSystemPoints[i], 0.1 ));
    }

}