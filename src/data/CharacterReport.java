package data;

import lombok.Data;

import java.util.HashMap;

/**
 * Created by 조은지 on 2017-06-16.
 */

@Data
public class CharacterReport {
    String name;
    String path;
    double ax;
    double maxDx;
    double brakingForce;
    HashMap<String, Integer> percent;
    HashMap<String, Double> gradeSpeed;
}
