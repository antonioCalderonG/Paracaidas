/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paracaidas;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author anton
 */
public class Paracaidas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Paracaidas");
        RealTimeChart rtcp = new RealTimeChart("Velocidad", "Velocidad", "Velocidad");
        frame.getContentPane().add(rtcp, new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        (new Thread(rtcp)).start();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                System.exit(0);
            }

        });
        /*
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

        while ((int)tiempo != (int)velocidad) {
            tiempo++;
            velocidad = ((gravedad * peso) / coeficiente) * (1 - Math.pow(Math.E, -(coeficiente / peso) * tiempo));
            System.out.println("Velocidad: " + velocidad);
            System.out.println("Tiempo: " + tiempo);
        }

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("firefox", new double[][]{{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017}, {25, 29.1, 32.1, 32.9, 31.9, 25.5, 20.1, 18.4, 15.3, 11.4, 9.5}});
        dataset.addSeries("ie", new double[][]{{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017}, {67.7, 63.1, 60.2, 50.6, 41.1, 31.8, 27.6, 20.4, 17.3, 12.3, 8.1}});
        dataset.addSeries("chrome", new double[][]{{2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017}, {0.2, 6.4, 14.6, 25.3, 30.1, 34.3, 43.2, 47.3, 58.4}});

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Browser Quota", "Year", "Quota", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, 100);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(600, 400);
        ImageIO.write(image, "png", new File("xy-chart.png"));*/
    }

}
