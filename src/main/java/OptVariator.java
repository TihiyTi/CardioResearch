public class OptVariator {
    private int xNStep = 7;
    private int yNStep = 7;
    private int rNStep = 7;
    private double xStep = 0.2;
    private double yStep = 0.2;
    private double rStep = 0.2;
    private final boolean xBidirectional = true;
    private final boolean yBidirectional = true;
    private final boolean rBidirectional = false;

    public int getxNStep() {
        return xNStep;
    }

    public int getyNStep() {
        return yNStep;
    }

    public int getrNStep() {
        return rNStep;
    }

    public double getxStep() {
        return xStep;
    }

    public double getyStep() {
        return yStep;
    }

    public double getrStep() {
        return rStep;
    }

    public boolean isxBidirectional() {
        return xBidirectional;
    }

    public boolean isyBidirectional() {
        return yBidirectional;
    }

    public boolean isrBidirectional() {
        return rBidirectional;
    }

    public OptVariator(){

    }
    public OptVariator(VariatorType type){
        if(VariatorType.TEST.equals(type)){
            xNStep = 1;
            yNStep = 1;
            rNStep = 1;
            xStep = 0.5;
            yStep = 0.5;
            rStep = 0.5;
        }
    }

    enum VariatorType{
        TEST, SIMPLE
    }
}
