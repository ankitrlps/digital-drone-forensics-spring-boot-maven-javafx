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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class AltitudeTabController {

	@Autowired
	private OSDColumnService osdColumnService;
	
	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;

/*	public void dislayDroneAltitude(List<DjiParameters> list) {
		log.info("Here are the results: " + customColumnRepo.findAll());
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Altitude");

		for (DjiParameters values : list) {
			String timeDate = values.getUpdateTime();
			String[] split = timeDate.split(" ");
			int altitude = Integer.parseInt(values.getOsdAltitude());

			series.getData().add(new XYChart.Data<>(split[1].trim(), altitude));
		}
		lineChart.getData().add(series);
	}*/

	public void displayAltitude(){
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Altitude");
		log.info("entered");

		osdColumnService.getAltitudeWithTime().stream().forEach(osdColumn -> {
			String timeDate = osdColumn.getTimestamp();
			String[] split = timeDate.split(" ");
			int altitude = Integer.parseInt(osdColumn.getColumn());

			series.getData().add(new XYChart.Data<>(split[1], altitude));
		});

		lineChart.getData().add(series);
	}
}
