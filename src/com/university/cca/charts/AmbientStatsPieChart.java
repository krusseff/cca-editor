package com.university.cca.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;

import com.university.cca.entities.AmbientStatistics;

/**
 * Holds utility methods to create a pie chart with the ambient statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class AmbientStatsPieChart {
	
	private static final String TITLE = "Ambient Statistics";
	private static final boolean IS_LEGEND_ENABLED  = true;
	private static final boolean IS_TOOLTIP_ENABLED = true;
	private static final boolean IS_URLS_ENABLED 	= false;
	private static final boolean IS_BORDER_VISIBLE  = true;
	private static final boolean IS_LABELS_ENABLED  = true;

	private AmbientStatsPieChart() {
		// Prevent creating an object of class AmbientStatsPieChart
	}

	/**
	 * Creates and returns a pie chart with provided ambient statistics.
	 */
	public static JFreeChart createChart(AmbientStatistics ambientStats) {
		PieDataset dataset = createDataset(ambientStats);
		
		JFreeChart chart = ChartFactory.createPieChart(
			TITLE,
			dataset,
			IS_LEGEND_ENABLED,
			IS_TOOLTIP_ENABLED,
			IS_URLS_ENABLED
		);
		
		// customize the pie chart
		chart.setBorderPaint(Color.BLACK);
	    chart.setBorderStroke(new BasicStroke(1.0f));
	    chart.setBorderVisible(IS_BORDER_VISIBLE);

	    // top, left, bottom, right
	    chart.setPadding(new RectangleInsets(0, 0, 5, 0));
	    
	    PiePlot plot = (PiePlot) chart.getPlot();
        PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
            "{1} ({2})",
            new DecimalFormat("0"), 
            new DecimalFormat("0%")
        );
        plot.setSimpleLabels(IS_LABELS_ENABLED);
        plot.setLabelGenerator(generator);

		return chart;
	}
	
	/**
	 * Prepares and returns a dataset required to create the pie chart.
	 */
	private static PieDataset createDataset(AmbientStatistics ambientStats) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Total", ambientStats.getTotalCount());
		dataset.setValue("Static", ambientStats.getStaticCount());
		dataset.setValue("Non-static", ambientStats.getNonStaticCount());
		dataset.setValue("Active", ambientStats.getActiveCount());
		dataset.setValue("Inactive", ambientStats.getInactiveCount());
		
		return dataset;
	}
}
