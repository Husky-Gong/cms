package com.zx.common;

import java.util.List;


public class EchartsData {
	/**
	 * data name
	 */
	private List<String> legendData;
	/**
	 * x axis data
	 */
	private List<Object> xaxisData;
	/**
	 * y axis data
	 */
	private List<Object> yaxisData;
	/**
	 * chart data
	 */
	private List<Object> seriesData;
	
	public List<String> getLegendData() {
		return legendData;
	}
	public void setLegendData(List<String> legendData) {
		this.legendData = legendData;
	}
	public List<Object> getXaxisData() {
		return xaxisData;
	}
	public void setXaxisData(List<Object> xaxisData) {
		this.xaxisData = xaxisData;
	}
	public List<Object> getYaxisData() {
		return yaxisData;
	}
	public void setYaxisData(List<Object> yaxisData) {
		this.yaxisData = yaxisData;
	}
	public List<Object> getSeriesData() {
		return seriesData;
	}
	public void setSeriesData(List<Object> seriesData) {
		this.seriesData = seriesData;
	}
}
