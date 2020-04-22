/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author User
 */
public class FrejaEidLatencyGraphPanel {
    private XYChart chart;
    private XChartPanel chartPanel;
    private List<Double> latencies;
    private List<Double> totalCount;
    
    public FrejaEidLatencyGraphPanel(String title, String xAxisName, String yAxisName) {
        double[] zeroArray = new double[1];
        zeroArray[0] = 0;
        chart = QuickChart.getChart(title, xAxisName, yAxisName,"Latency", zeroArray, zeroArray);
        setGraphStyle(Color.orange);
        chartPanel = new XChartPanel(chart);
        resetLists();
    }
    
    private void resetLists(){
        latencies = new CopyOnWriteArrayList<>();
        latencies.add((double)0);
        totalCount = new CopyOnWriteArrayList<>();
        totalCount.add((double)0);
    }
    
    private void setGraphStyle(Color color){
        chart.getSeriesMap().get("Latency").setLineColor(color);
        chart.getSeriesMap().get("Latency").setMarker(new org.knowm.xchart.style.markers.None());
    }
    
    private void updateGraph() {
        chart.updateXYSeries("Latency", totalCount, latencies, null);
        chartPanel.repaint();
    }
    
    public void add(double ping){
        latencies.add(ping);
        double previousTotalCount = totalCount.get(totalCount.size()-1);
        totalCount.add(previousTotalCount + 1);
        updateGraph();
    }
    
    public void clear() {
        resetLists();
        updateGraph();
        chartPanel.repaint();
    }
    
    public XChartPanel getPanel(){
        return chartPanel;
    }
}
