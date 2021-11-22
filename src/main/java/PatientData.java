import javax.vecmath.Vector2d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class PatientData {

    List<Vector2d> alexChannelPoints = Arrays.asList(new Vector2d(-40, -10), new Vector2d(-16, -63), new Vector2d(42, -63), new Vector2d(65, -40), new Vector2d(42, 10));



    public static List<Vector2d> getChannelPoints(Names name){
        switch (name){
            case ALEX:
                return Arrays.asList(new Vector2d(-40, -10), new Vector2d(-16, -63), new Vector2d(42, -63), new Vector2d(65, -40), new Vector2d(42, 10));
            case IVAN:
                return Arrays.asList(new Vector2d(-32, -24), new Vector2d(11, -51), new Vector2d(58, -37), new Vector2d(63, -11), new Vector2d(36, 27));
            case ARTEM:
                return Arrays.asList(new Vector2d(-37, -27), new Vector2d(12, -47), new Vector2d(67, -42), new Vector2d(76, -11), new Vector2d(41, 29));
            default:
                System.out.println("Use one of names:" + Arrays.toString(Names.values()));
                return null;
        }
    }

    // {{xParams}, {yParams}}
    public static double[][] getXYOneSphere(Names name){
        switch (name){
            case ARTEM:
                return new double[][]{{0.014, 0.013, 0.003, -0.004, -0.014},{0.044, 0.046, 0.03, 0.061, 0.031}};
            case IVAN:
                return new double[][]{{0.016, 0.012, 0., -0.007, -0.016},{0.042, 0.026, 0.044, 0.054, 0.023}};
            case ALEX:
                return new double[][]{{0.024, 0.015, -0.004, -0.014, -0.024},{0.006, 0.041, 0.05, 0.056, 0.038}};
            default:
                System.out.println("Use one of names:" + Arrays.toString(Names.values()));
                return null;
        }
    }

    enum Names {
        ALEX, ARTEM, IVAN
    }

}
