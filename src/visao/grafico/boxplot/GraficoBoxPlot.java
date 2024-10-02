package visao.grafico.boxplot;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

/**
 *
 * @author Leandro
 */
public class GraficoBoxPlot {
    private static final String ROW_KEY = "Metrica";
    private final List<Double> valores;
    
    public GraficoBoxPlot(List<Double> valores) {
        this.valores = valores;
    }
    
    public ChartPanel getChart() {
        DefaultBoxAndWhiskerCategoryDataset data = new DefaultBoxAndWhiskerCategoryDataset();
                                            data.add(this.valores, ROW_KEY, "Metrica");
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Box Splot", ROW_KEY, "Valores", data, false);
        ChartPanel panel = new ChartPanel(chart);
                   panel.setPreferredSize(new Dimension(275, 280));
                   panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                   panel.setVisible(true);
        return panel;
    }
}