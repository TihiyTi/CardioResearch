import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReoMathUtils {

//   0   double ro1, double ro2, double a,
//   3   double b, double r, double h,
//   6   double x, double y
    public static double[] paramXYRModifyCln(double [] params, double dx, double dy, double dr){
        double[] clone = params.clone();
        clone[6] = clone[6] + dx;
        clone[7] = clone[7] + dy;
        clone[4] = clone[4] + dr;
        return clone;
    }
    public static double[] paramXYZRModifyCln(double [] params, double dx, double dy, double dr, double dz){
        double[] clone = params.clone();
        clone[6] = clone[6] + dx;
        clone[7] = clone[7] + dy;
        clone[4] = clone[4] + dr;
        clone[5] = clone[5] - dr - dz;
        return clone;
    }

    public static double[] paramRo1Modify(double [] params, double dro1){
        params[0] = params[0] + dro1;
        return params;
    }

    private static double[] paramXModify(double [] params, double dx){
        params[6] = params[6] + dx;
        return params;
    }
    private static double[] paramYModify(double [] params, double dy){
        params[7] = params[7] + dy;
        return params;
    }
    private static double[] paramRModify(double [] params, double dr){
        params[4] = params[4] + dr;
        return params;
    }


    public static List<double[]> arrayVariants(int xNOfStep, double xStep, boolean xBidirectional,
                              int yNOfStep, double yStep, boolean yBidirectional,
                              int zNOfStep, double zStep, boolean zBidirectional){
        Stream<Stream<Stream<double[]>>> stream =
                IntStream.iterate(xBidirectional ? (-(int)(xNOfStep/xStep)) : 0, i->i+1).limit(xBidirectional ? (2*(long)(xNOfStep/xStep)+1) : (long)(xNOfStep/xStep)+1)
                        .mapToObj(x -> IntStream.iterate(yBidirectional ? (-(int)(yNOfStep/yStep)) : 0, i->i+1).limit(yBidirectional ? (2*(long)(yNOfStep/yStep)+1) : (long)(yNOfStep/yStep)+1)
                                .mapToObj(y -> IntStream.iterate(zBidirectional ? (-(int)(zNOfStep/zStep)) : 0, i->i+1).limit(zBidirectional ? (2*(long)(zNOfStep/zStep)+1) : (long)(zNOfStep/zStep)+1)
                                        .mapToObj(z-> new double[]{x*xStep,y*yStep,z*zStep})));
        List<double[]> list = stream.flatMap(Function.identity()).flatMap(Function.identity()).collect(Collectors.toList());
        return list;
    }


}
