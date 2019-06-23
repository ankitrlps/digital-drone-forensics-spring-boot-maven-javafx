package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.service.CustomColumnService;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SpeedTabController {

	@Autowired
	CustomColumnService customColumnService;

	@FXML
	AnchorPane speedAnchorMain;

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;

	public void setWidthDimensions(double width){
		this.speedAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.speedAnchorMain.setPrefWidth(width / 1.1d);
				this.lineChart.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.speedAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.speedAnchorMain.setPrefHeight(height / 1.1d);
				this.lineChart.setLayoutY(height / 8.22d);
				this.lineChart.setPrefHeight(height / 1.4d);
			}
		});
	}
	
	public void dislaySpeed() {
		
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Speed");

		customColumnService.getHSpeedWithTime().stream()
				.forEach(speed -> {
					String timeDate = speed.getTimestamp();
					String[] split = timeDate.split(" ");
					float hSpeed = Float.parseFloat(speed.getColumn());

					series.getData().add(new XYChart.Data<>(split[1], (hSpeed * 10)));
				});
		lineChart.getData().add(series);
	}
}
