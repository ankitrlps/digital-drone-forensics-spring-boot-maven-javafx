package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.service.OSDColumnService;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FlightControlController {

	@Autowired
	OSDColumnService osdColumnService;

	@FXML
	CategoryAxis xAxis;
	@FXML
	NumberAxis yAxis;
	@FXML
	LineChart<String, Number> fControlLineChart;
	@FXML
	Label helpText;

	/*
	 * final static String takeOff = "AutoTakeoff"; final static String onGpsAlt
	 * = "GPS_Atti"; final static String landing = "AutoLanding"; final static
	 * int takeOffNum = 0; final static int flyNum = 5; final static int
	 * landingNum = 10;
	 */

	@SuppressWarnings("unchecked")
	public void showFlightControl() {
		
		helpText.setText("'10' - Assisted Take Off \n'6' - Flying (GPS) \n'11' - Auto Take Off \n'12' - Auto Landing");

		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Flight Control");

		System.out.println("Size: " + osdColumnService.getSatellitesWithTime().size());

		osdColumnService.getFlyCStateWithTime().stream()
				.forEach(flyCState -> {
					String timeDate = flyCState.getTimestamp();
					String[] split = timeDate.split(" ");
					int flyState = Integer.parseInt(flyCState.getColumn());

					series1.getData().add(new XYChart.Data<>(split[1], flyState));
				});
		fControlLineChart.getData().addAll(series1);
	}
}