import org.junit.Test;

import javax.vecmath.Vector2d;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VectorTests {
    public static void main(String[] args) {
        System.out.println("Vector test");
//        Vector2d v1 = new Vector2d(-4.,-2.);
        Vector2d v1 = new Vector2d(4.,2.);
        Vector2d v2 = new Vector2d(-3.,4.);
        double scalar = v1.dot(v2);
        double cosQ = scalar/(v1.length()*v2.length());
        System.out.println(cosQ);
        System.out.println(Math.acos(cosQ));
        System.out.println(Math.acos(cosQ)/Math.PI);
        System.out.println("Two");
        System.out.println(v1.angle(v2)/Math.PI);
    }

    public static Vector2d toNewAxis(Vector2d xAxisVector, Vector2d vector){
        double length = vector.length();
        double cosQ = xAxisVector.dot(vector)/(xAxisVector.length()*vector.length());
        double angle = xAxisVector.angle(vector);
        double det = determinant(xAxisVector, vector);
        return new Vector2d(-length*cosQ, length*Math.sin(angle) * det/Math.abs(det));
    }

    public static double[] toNewAxis(double x1,double y1,double x2,double y2){
        Vector2d xAxisVector = new Vector2d(x1,y1);
        Vector2d vector = new Vector2d(x2,y2);
        Vector2d res = toNewAxis(xAxisVector, vector);
        return new double[]{res.x, res.y};
    }

    private static double determinant(Vector2d v1, Vector2d v2){
        return v1.x*v2.y - v2.x*v1.y;
    }

    public static List<Vector2d> sphereCenterMoveUpdate(List<Vector2d> electrodSystemPoints, Vector2d centerMove){
        return electrodSystemPoints.stream().map(x-> toNewAxis(x, centerMove)).collect(Collectors.toList());
    }
    public static double[][] sphereCenterMoveUpdate(double[][] electrodSystemPoints, double... centerMove){
        return Arrays.stream(electrodSystemPoints).map(x-> toNewAxis(x[0],x[1], centerMove[0], centerMove[1])).toArray(double[][]::new);
    }


}
