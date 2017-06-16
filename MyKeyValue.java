package fileprac;

import java.util.HashMap;

/**
 * Created by 조은지 on 2017-06-14.
 */
public class MyKeyValue {
    String name;
    String path;
    double ax;
    double maxDx;
    double brakingForce;
    HashMap<String, Integer> percent;
    double gradeSpeed;

    MyKeyValue() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("name: ").append(name).append("\npath: ")
                .append(path).append("\nax").append(ax).append("\nmaxDx")
                .append(maxDx).append("\nbrakingForce").append(brakingForce)
                .append("\ngradeSpeed").append(gradeSpeed);
        return builder.toString();
    }
}
