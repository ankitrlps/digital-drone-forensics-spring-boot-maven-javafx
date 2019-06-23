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
public class DroneRollPitchYawController {

	@Autowired
	OSDColumnService osdColumnService;

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;
	@FXML
	AnchorPane pryAnchorMain;

	public void setWidthDimensions(double width){
		this.pryAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.pryAnchorMain.setPrefWidth(width / 1.1d);
				this.lineChart.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.pryAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.pryAnchorMain.setPrefHeight(height / 1.1d);
				this.lineChart.setLayoutY(height / 8.22d);
				this.lineChart.setPrefHeight(height / 1.4d);
			}
		});
	}

	@SuppressWarnings("unchecked")
public void dislayDroneRPY() {
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Pitch");
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Roll");
		XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		series3.setName("Yaw");

		osdColumnService.getPitchRollYawWithTime().stream().forEach(columns -> {
			String timeDate = columns.getTimestamp();
			String[] split = timeDate.split(" ");
			float pitch = Float.parseFloat(columns.getColumn1());
			float roll = Float.parseFloat(columns.getColumn2());
			float yaw = Float.parseFloat(columns.getColumn3());

			series1.getData().add(new XYChart.Data<>(split[1], pitch));
			series2.getData().add(new XYChart.Data<>(split[1], roll));
			series3.getData().add(new XYChart.Data<>(split[1], yaw));
		});
		lineChart.getData().addAll(series1, series2, series3);
	}
}
