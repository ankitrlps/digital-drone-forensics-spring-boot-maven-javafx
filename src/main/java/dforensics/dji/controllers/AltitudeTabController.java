package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.repository.CustomColumnRepo;
import dforensics.dji.repository.OSDColumnRepo;
import dforensics.dji.service.OSDColumnService;
import dforensics.dji.service.impl.OSDServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class AltitudeTabController {

	@Autowired
	private OSDColumnService osdColumnService;
	
	@FXML LineChart<String, Number> altitudeLineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;
	@FXML
	AnchorPane altitudeAnchorMain;

	public void setWidthDimensions(double width){
		this.altitudeAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.altitudeAnchorMain.setPrefWidth(width / 1.1d);
				this.altitudeLineChart.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.altitudeAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.altitudeAnchorMain.setPrefHeight(height / 1.1d);
				this.altitudeLineChart.setLayoutY(height / 8.22d);
				this.altitudeLineChart.setPrefHeight(height / 1.4d);
			}
		});
	}

	public void displayAltitude(){
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Altitude Over Time Chart");

		osdColumnService.getAltitudeWithTime().stream().forEach(osdColumn -> {
			String timeDate = osdColumn.getTimestamp();
			String[] split = timeDate.split(" ");
			int altitude = Integer.parseInt(osdColumn.getColumn());

			series.getData().add(new XYChart.Data<>(split[1], altitude));
		});

		altitudeLineChart.getData().add(series);
	}
}
