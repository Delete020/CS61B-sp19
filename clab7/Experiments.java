import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int x = 0; x < 5000; x += 1) {
            bst.add(r.nextInt());
            xValues.add(bst.size());
            yValues.add(bst.optimalAverageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("x + random(0, 10)", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int M = 1000;
        for (int x = 0; x < 5000; x += 1) {
            bst.add(r.nextInt());
        }
        for (int i = 0; i < M; i++) {
            ExperimentHelper.randomDeleteAdd(bst);
            xValues.add(i);
            yValues.add(bst.optimalAverageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("x + random(0, 10)", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int M = 10000;
        for (int x = 0; x < 5000; x += 1) {
            bst.add(r.nextInt());
        }
        for (int i = 0; i < M; i++) {
            ExperimentHelper.randomDeleteAdd2(bst);
            xValues.add(i);
            yValues.add(bst.optimalAverageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("x + random(0, 10)", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment2();
    }
}
