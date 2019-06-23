package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.service.OSDColumnService;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SatellitesNumberController {

	@Autowired
	OSDColumnService osdColumnService;

	@FXML AnchorPane satAnchorMain;

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;

	public void setWidthDimensions(double width){
		this.satAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.satAnchorMain.setPrefWidth(width / 1.1d);
				this.lineChart.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.satAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.satAnchorMain.setPrefHeight(height / 1.1d);
				this.lineChart.setLayoutY(height / 8.22d);
				this.lineChart.setPrefHeight(height / 1.4d);
			}
		});
	}

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
