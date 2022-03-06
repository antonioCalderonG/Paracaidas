/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paracaidas;

/**
 *
 * @author anton
 */
//RealTimeChart .java   
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.DefaultXYDataset;

public class RealTimeChart extends ChartPanel implements Runnable {

    private static TimeSeries timeSeries;
    private long value = 0;

    public RealTimeChart(String chartContent, String title, String yaxisName) {
        super(createChart(chartContent, title, yaxisName));
    }

    private static JFreeChart createChart(String chartContent, String title, String yaxisName) {
        // Crear un objeto de diagrama de tiempos  
        timeSeries = new TimeSeries(chartContent);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "Tiempo", yaxisName, timeseriescollection, true, true, false);
        XYPlot xyplot = jfreechart.getXYPlot();
        // Configuración de coordenadas verticales   
        ValueAxis valueaxis = xyplot.getDomainAxis();
        // Establecer el rango de datos del eje de datos automáticamente   
        valueaxis.setAutoRange(true);
        // Eje de datos rango de datos fijo 30s   
        valueaxis.setFixedAutoRange(30000D);

        valueaxis = xyplot.getRangeAxis();
        //valueaxis.setRange(0.0D,200D);   

        return jfreechart;
    }

    public void run() {
        double gravedad = 9.8;
        double tiempo = 4;
        double velocidad;
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese peso del paracaidista: ");
        //double peso = scan.nextDouble();
        double peso = 68.1;
        System.out.println("Ingrese coeficiente: ");
        //double coeficiente = scan.nextDouble();
        double coeficiente = 12.5;
        //68.1
        //12.5
        System.out.println("Peso del paracaidista: " + peso);
        System.out.println("Coeficiente de resistencia del aire: " + coeficiente);

        velocidad = ((gravedad * peso) / coeficiente) * (1 - Math.pow(Math.E, -(coeficiente / peso) * tiempo));
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Tiempo: " + tiempo);

        while ((int) tiempo != (int) velocidad) {
            timeSeries.add(new Millisecond(), velocidad);
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(RealTimeChart.class.getName()).log(Level.SEVERE, null, ex);
            }
            tiempo++;
            velocidad = ((gravedad * peso) / coeficiente) * (1 - Math.pow(Math.E, -(coeficiente / peso) * tiempo));
            System.out.println("Velocidad: " + velocidad);
            System.out.println("Tiempo: " + tiempo);
        }
        
        DefaultXYDataset dataset = new DefaultXYDataset();
        //Asigna valores a la grafica
        dataset.addSeries("Velocidad", new double[][]{{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017}, {25, 29.1, 32.1, 32.9, 31.9, 25.5, 20.1, 18.4, 15.3, 11.4, 9.5}});

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesStroke(0, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Browser Quota", "Tiempo", "Velocidad", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, 100);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#"));
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("Grafica.png"));
        } catch (IOException ex) {
            Logger.getLogger(RealTimeChart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private long randomNum() {
        System.out.println((Math.random() * 20 + 80));
        return (long) (Math.random() * 20 + 80);
    }
}
