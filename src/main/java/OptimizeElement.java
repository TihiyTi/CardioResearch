import java.util.Locale;

public class OptimizeElement{
    double delta;
    double atrialDistance2;
    double dx;
    double dy;
    double dz;
    double dr;

    double x;
    double y;
    double z;
    double r;


    public OptimizeElement(double dx, double dy, double dr) {
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;
    }
    public OptimizeElement(double dx, double dy, double dr, double dz) {
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;
        this.dz = dz;
    }
//    public OptimizeElement()

    @Override
    public String toString(){
        return "dX: "+dx+"  dY: "+dy+"  dZ: "+dz+ "  dR: "+dr+ "  Atr: " + atrialDistance2 + "   Delta: "+ delta + "\n";
    }
    public String toLog(){
        return String.format(Locale.ENGLISH, "%.1f" ,dx)+" "
                +String.format(Locale.ENGLISH, "%.1f" ,dy)+" "
                +String.format(Locale.ENGLISH, "%.1f" ,dz)+ " "
                +String.format(Locale.ENGLISH, "%.1f" ,dr)+ " "
                + String.format(Locale.ENGLISH, "%.1f" ,atrialDistance2 )+ " "
                + String.format(Locale.ENGLISH, "%.1f" ,delta);
    }
    public String toStringXYZR(double[] sphere){
        return "X: "+(sphere[0]+dx)+" Y : "+(sphere[1]+dy)+"  Z: "+(dz)+ "  R: "+(sphere[2]-dr)+ "  Atr: " + atrialDistance2 + "   Delta: "+ delta + "\n";
    }

    public OptimizeElement setAtrialDistance2(double distance2){
        atrialDistance2 = distance2;
        return this;
    }
    public double[] getAsArray(){
        return new double[]{dx,dy,dz,dr};
    }
}
