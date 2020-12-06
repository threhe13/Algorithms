package HomeWork_5thWeek;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {
    public static class point{
        double x;
        double y;

        public point(double x, double y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return this.x+" "+this.y;
        }
    }

    public static double distancePoint(point p1, point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2)+Math.pow(p1.y - p2.y, 2));
    }

    public static double bruteForce(point[] points){
        double temp = Double.MAX_VALUE;

        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                double distance = distancePoint(points[i], points[j]);

                if(temp > distance){
                    temp = distance;
                }
            }
        }

        return temp;
    }

    public static double closest(point[] points){
        if(points.length <= 3){
            return bruteForce(points);
        }

        int mid = points.length/2;

        point[] leftArr = Arrays.copyOfRange(points, 0, mid);
        point[] rightArr = Arrays.copyOfRange(points, mid, points.length);

        double leftDistance = closest(leftArr);
        double rightDistance = closest(rightArr);
        double distance = Math.min(leftDistance, rightDistance);

        point[] xPoint = new point[points.length];
        int j = 0;
        point midPoint = points[mid];
        for(int i = 0; i < points.length; i++){
            if((points[i].x - midPoint.x) <= distance){
                xPoint[j++] = new point(points[i].x, points[i].y);
            }
        }

        double result = Y_closest(xPoint, distance);

        return result;
    }

    public static double Y_closest(point[] points, double distance){
        Arrays.sort(points, new Comparator<point>() {
            @Override
            public int compare(point p1, point p2) {
                if(p1.y > p2.y){
                    return 1;
                }
                else if(p1.y < p2.y){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        });

        double temp = 0.0;

        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                if((points[i].y - points[j].y) < distance){
                    temp = distancePoint(points[i], points[j]);
                    if(distance > temp){
                        distance = temp;
                    }
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) throws IOException {
        //double[][] input_data = {{1.23, 12.3}, {1.0, 2.0}, {3.1, 21.2}, {5.2, 10.0}};

        String path = ClosestPair.class.getResource("").getPath();
        List<String> list = Files.readAllLines(Paths.get(path+"data05_closest.txt"));

        double[][] input_data = new double[list.size()][2];
        for(int i = 0; i < list.size(); i++){
            String[] str = list.get(i).split(",");

            input_data[i][0] = Double.parseDouble(str[0]);
            input_data[i][1] = Double.parseDouble(str[1]);
        }

        point[] points = new point[input_data.length];
        for(int i = 0; i < input_data.length; i++){
            points[i] = new point(input_data[i][0], input_data[i][1]);
        }

        double result = closest(points);
        System.out.println(result);
    }
}
