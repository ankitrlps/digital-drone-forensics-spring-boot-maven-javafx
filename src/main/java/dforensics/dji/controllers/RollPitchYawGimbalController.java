package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import dforensics.dji.service.GimbalColumnService;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RollPitchYawGimbalController {

	@Autowired
	GimbalColumnService gimbalColumnService;

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;

	@SuppressWarnings("unchecked")
	public void dislayGimbalRPY() {

		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Pitch");
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Roll");
		XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		series3.setName("Yaw");

		gimbalColumnService.getGimbalPRYWithTime().stream()
				.forEach(gPRY -> {
					String timeDate = gPRY.getTimestamp();
					String[] split = timeDate.split(" ");
					float pitch = Float.parseFloat(gPRY.getColumn1());
					float roll = Float.parseFloat(gPRY.getColumn2());
					float yaw = Float.parseFloat(gPRY.getColumn3());

					series1.getData().add(new XYChart.Data<>(split[1], pitch));
					series2.getData().add(new XYChart.Data<>(split[1], roll));
					series3.getData().add(new XYChart.Data<>(split[1], yaw));
				});
		lineChart.getData().addAll(series1, series2, series3);
	}
}
