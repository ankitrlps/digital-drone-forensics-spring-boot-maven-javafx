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

	@SuppressWarnings("unchecked")
	public void displayBattery() {
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Battery Over Time");

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
