package wolframreo;

import java.util.ArrayList;
import java.util.List;

public class SignalRadialData {

    List<CycleRadialData> cycleRadialData = new ArrayList<>();

    public SignalRadialData(List<CycleRadialData> list){
        cycleRadialData = list;
    }

    public List<CycleRadialData> getCycleRadialData() {
        return cycleRadialData;
    }
}
