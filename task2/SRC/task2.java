package com.performance.tasks;

import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class task2 {
    public static void main(String[] args) {
        if(!Files.exists(Paths.get(args[0])))
            System.out.println("File not found");
        try {
            String inputLine = Files.readAllLines(Paths.get(args[0])).get(0);
            double[] center = new double[3],
                    point1 = new double[3],
                    point2 = new double[3];
            double radius = 0.0;
            String[] str = inputLine.replaceAll("[^\\-\\s\\.a-zA-Z0-9]", "").split(" ");
            int i = 0;
            while (i < str.length) {
                switch (str[i]) {
                    case "center":
                        center = new double[]{Double.parseDouble(str[i + 1]),
                                Double.parseDouble(str[i + 2]),
                                Double.parseDouble(str[i + 3])};
                        i += 4;
                        break;
                    case "radius":
                        radius = Double.parseDouble(str[i + 1]);
                        i += 2;
                        break;
                    case "line":
                        point1 = new double[]{Double.parseDouble(str[i + 1]),
                                Double.parseDouble(str[i + 2]),
                                Double.parseDouble(str[i + 3])};
                        point2 = new double[]{Double.parseDouble(str[i + 4]),
                            Double.parseDouble(str[i + 5]),
                            Double.parseDouble(str[i + 6])};
                        i += 7;
                        break;
                    default:
                        i++;
                        break;
                }
            }

            double A = pow(point1[0] - center[0], 2) + pow(point1[1] - center[1], 2) + pow(point1[2] - center[2], 2) - radius*radius;
            double C = pow(point1[0] - point2[0], 2) + pow(point1[1] - point2[1], 2) + pow(point1[2] - point2[2], 2);
            double B = pow(point2[0] - center[0], 2) + pow(point2[1] - center[1], 2) + pow(point2[2] - center[2], 2) - A - C - radius*radius;

            double D = B * B - 4 * A * C;
            if (D < 0) {
                System.out.println("Коллизий не найдено");
                System.exit(0);
            }
            double t = (-B - sqrt(D)) / (2.0 * A);
            double[] solution1 = {point1[0] * (1 - t) + t*point2[0],
                    point1[1] * (1 - t) + t*point2[1],
                    point1[2] * (1 - t) + t*point2[2]};
            System.out.println(Arrays.toString(solution1));
            if (D == 0)
                System.exit(0);

            t = (-B + sqrt(D)) / (2.0 * A);
            double[] solution2 = {point1[0] * (1 - t) + t*point2[0],
                    point1[1] * (1 - t) + t*point2[1],
                    point1[2] * (1 - t) + t*point2[2]};
            System.out.println(Arrays.toString(solution2));
        }
        catch (IOException e){
            System.out.println("IOException");
            System.exit(-1);
        }
    }
}
