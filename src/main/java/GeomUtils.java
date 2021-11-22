import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class GeomUtils {
    // Возвращает расстояние от окружности до точки
    public static double pointToCircleDistance(double xPoint, double yPoint, double xCenter, double yCenter, double radius){
        return Math.abs(radius-Math.sqrt((xPoint - xCenter)*(xPoint - xCenter) + (yPoint - yCenter)*(yPoint - yCenter)));
    }

    // Возвращает сумму квадратов расстояний от окружности до набора точек
    public static double pointsToCircleAvDistance(double[][] points, double xCenter, double yCenter, double raduis){
        return Arrays.stream(points).mapToDouble(p -> Math.pow(pointToCircleDistance(p[0],p[1],xCenter,yCenter,raduis),2)).sum();
    }
    public static double pointsToCircleAvDistance(double[][] points, double[] sphereXYR){

        return Arrays.stream(points).mapToDouble(p -> Math.pow(pointToCircleDistance(p[0],p[1],sphereXYR[0],sphereXYR[1],sphereXYR[2]),2)).sum();
    }

    public static double[] sphereModify(double[] sphereXYR, double[] modifyDxDyDr){
        return new double[]{sphereXYR[0] + modifyDxDyDr[0], sphereXYR[1] + modifyDxDyDr[1], sphereXYR[2] - modifyDxDyDr[2]};
    }

}
