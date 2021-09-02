package com.university.cca.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import com.university.cca.entities.MessageStatistics;

/**
 * Holds utility methods to create a bar chart with the message statistics.
 * 
 * @author Konstantin Rusev
 * @version 1.0
 */
public class MessageStatsBarChart {

	private static final String TITLE = "Message Statistics";
	private static final String LEGEND_TITLE = "Ambient Names";
	private static final String MESSAGE_COUNT_TITLE = "Count";
	private static final boolean IS_LEGEND_ENABLED = true;
	private static final boolean IS_TOOLTIP_ENABLED = true;
	private static final boolean IS_URLS_ENABLED = false;
	private static final boolean IS_BORDER_VISIBLE = true;
	
	private static final String SENT_MESSAGES_LABEL = "Sent Messages";
	private static final String RECEIVED_MESSAGES_LABEL = "Received Messages";

	private MessageStatsBarChart() {
		// Prevent creating an object of class MessageStatsBarChart
	}
	
	/**
	 * Creates and returns a bar chart with provided message statistics.
	 */
	public static JFreeChart createChart(List<MessageStatistics> statistics) {
		CategoryDataset dataset = createDataset(statistics);
		
		JFreeChart chart = ChartFactory.createBarChart(
			TITLE,           
			LEGEND_TITLE,            
			MESSAGE_COUNT_TITLE,            
	    	dataset,          
	    	PlotOrientation.VERTICAL,           
	    	IS_LEGEND_ENABLED, 
	    	IS_TOOLTIP_ENABLED, 
	    	IS_URLS_ENABLED
    	);
		
		// set border of the bar chart
		chart.setBorderPaint(Color.BLACK);
	    chart.setBorderStroke(new BasicStroke(1.0f));
	    chart.setBorderVisible(IS_BORDER_VISIBLE);
	    
	    // remove border of the legend
	    chart.getLegend().setFrame(BlockBorder.NONE);
	    
	    // top, left, bottom, right
	    chart.setPadding(new RectangleInsets(0, 0, 5, 0));
        
		return chart;
	}
	
	/**
	 * Prepares and returns a dataset required to create the bar chart.
	 */
	private static CategoryDataset createDataset(List<MessageStatistics> statistics) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		
		for (MessageStatistics stat : statistics) {
			String ambientName = stat.getAmbientName();
			
			dataset.addValue(stat.getSentCount(), ambientName, SENT_MESSAGES_LABEL);        
			dataset.addValue(stat.getReceivedCount(), ambientName, RECEIVED_MESSAGES_LABEL);        
		}
		
		return dataset;
	}
}
