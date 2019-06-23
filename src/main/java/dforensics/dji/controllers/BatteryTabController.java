package dforensics.dji.controllers;

import java.util.List;
import java.util.Objects;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.service.SmartBatteryService;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BatteryTabController {

	@Autowired
	SmartBatteryService smartBatteryService;

	@FXML
	LineChart<String, Number> batteryLineChart;
	@FXML
	CategoryAxis xAxis;
	@FXML
	NumberAxis yAxis;

	@FXML
	AnchorPane batteryAnchorMain;

	public void setWidthDimensions(double width){
		this.batteryAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.batteryAnchorMain.setPrefWidth(width / 1.1d);
				this.batteryLineChart.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.batteryAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.batteryAnchorMain.setPrefHeight(height / 1.1d);
				this.batteryLineChart.setLayoutY(height / 8.22d);
				this.batteryLineChart.setPrefHeight(height / 1.4d);
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void displayBattery() {
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Battery Usage Over Time");

		smartBatteryService.getBatteryWithTime().stream().skip(10).filter(Objects::nonNull)
				.forEach(battery -> {
			String timeDate = battery.getTimestamp();
			String[] split = timeDate.split(" ");
			int batteryValue = Integer.parseInt(battery.getColumn());
			series1.getData().add(new XYChart.Data<>(split[1], batteryValue));
		});
		batteryLineChart.getData().addAll(series1);
	}
}
