import org.junit.Test;
import reodata.ReoTempData;
import wolframbridge.WolframBridge;
import wolframreo.SignalRadialData;
import wolframreo.TimeStampRadialData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class OptimizatorManagerTest {

    @Test
    public void optimize(){
        String name = "Artem";
        double[] equalSphere = new double[]{ 15, -6, 52 };
        TimeStampRadialData radialData = WolframBridge.w_GetRadialPoint(name);
        SignalRadialData signalData = WolframBridge.w_SignalRadialData(name);

//        OptimizatorManager mng = new OptimizatorManager(OptVariator.VariatorType.TEST);
        OptimizatorManager mng = new OptimizatorManager();
        mng.setHeartParameters(WolframBridge.w_AtrialPoints(name) ,WolframBridge.w_ElectrodSystemPoints(name));
        mng.setExperimentalData(signalData);
        mng.setEqualSphere(equalSphere);
        mng.setModelParameters(WolframBridge.w_NewGetParam(name), new double[]{4.2,4.2,4.2,4.2,4.2}, 1.35, 0.03);
        mng.useZOptimum = true;

        System.out.println("GET SIGNAL");
        List<List<OptimizeElement>> listOfList = mng.optimize();
//
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        try {
            Files.write(
                    Paths.get(name + " " + simpleDateFormat.format(new Date())+".txt"),
                    (Iterable<String>)listOfList.stream()
                            .flatMap(s-> Stream.concat(Stream.of("New CYCLE:"),s.stream().map(OptimizeElement::toLog)))::iterator,
                    CREATE, WRITE
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void optimizeAlex(){
        String name = "Alex";
        double[] equalSphere = ReoTempData.getEqualSphereAlex();
        SignalRadialData signalData = WolframBridge.w_SignalRadialData(name);
//        OptimizatorManager mng = new OptimizatorManager(OptVariator.VariatorType.TEST);
        OptimizatorManager mng = new OptimizatorManager();
        mng.setHeartParameters(WolframBridge.w_AtrialPoints(name) ,WolframBridge.w_ElectrodSystemPoints(name));
        mng.setExperimentalData(signalData);
        mng.setEqualSphere(equalSphere);
        mng.setModelParameters(WolframBridge.w_NewGetParam(name), new double[]{4.2,4.2,4.2,4.2,4.2}, 1.35, 0.037);
        mng.useZOptimum = true;
//
        System.out.println("GET SIGNAL");
        List<List<OptimizeElement>> listOfList = mng.optimize();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        try {
            Files.write(
                    Paths.get(name + " " + simpleDateFormat.format(new Date())+".txt"),
                    (Iterable<String>)listOfList.stream()
                            .flatMap(s-> Stream.concat(Stream.of("New CYCLE:"),s.stream().map(OptimizeElement::toLog)))::iterator,
                    CREATE, WRITE
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void optimizeIvan(){
        String name = "Ivan";
        double[] equalSphere = ReoTempData.getEqualSphereIvan();
        SignalRadialData signalData = WolframBridge.w_SignalRadialData(name);
//        OptimizatorManager mng = new OptimizatorManager(OptVariator.VariatorType.TEST);
        OptimizatorManager mng = new OptimizatorManager();
        mng.setHeartParameters(WolframBridge.w_AtrialPoints(name) ,WolframBridge.w_ElectrodSystemPoints(name));
        mng.setExperimentalData(signalData);
        mng.setEqualSphere(equalSphere);
        mng.setModelParameters(WolframBridge.w_NewGetParam(name), new double[]{3.37145, 3.30732, 4.18836, 3.76647, 4.06796}, 1.35, 0.025);
        mng.useZOptimum = true;

        System.out.println("GET SIGNAL");
        List<List<OptimizeElement>> listOfList = mng.optimize();
//
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        try {
            Files.write(
                    Paths.get(name + " " + simpleDateFormat.format(new Date())+".txt"),
                    (Iterable<String>)listOfList.stream()
                            .flatMap(s-> Stream.concat(Stream.of("New CYCLE:"),s.stream().map(OptimizeElement::toLog)))::iterator,
                    CREATE, WRITE
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}