package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.service.OSDColumnService;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SatellitesNumberController {

	@Autowired
	OSDColumnService osdColumnService;

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;

	public void displaySatellites() {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Satellites");

		osdColumnService.getSatellitesWithTime().stream()
				.forEach(satellite -> {
					String timeDate = satellite.getTimestamp();
					String[] split = timeDate.split(" ");
					int sat = Integer.parseInt(satellite.getColumn());

					series.getData().add(new XYChart.Data<>(split[1], sat));
				});

		lineChart.getData().add(series);
	}
}
