package dforensics.dji.controllers;

import java.text.DecimalFormat;
import java.util.List;

import dforensics.dji.domain.SingleColumn;
import dforensics.dji.domain.TimeAndTwoColumn;
import dforensics.dji.entity.AppGpsColumn;
import dforensics.dji.entity.DetailsColumn;
import dforensics.dji.entity.OSDColumn;
import dforensics.dji.service.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OverviewTabController {

	@Autowired
	DJIMainController djiMainController;

	@Autowired
	SmartBatteryService smartBatteryService;

	@Autowired
	DetailsColumnService detailsColumnService;

	@Autowired
	HomeColumnService homeColumnService;

	@Autowired
	AppGpsColumnService appGpsColumnService;

	@Autowired
	CenterBatteryColumnService centerBatteryColumnService;

	@Autowired
	CustomColumnService customColumnService;

	@Autowired
	OSDColumnService osdColumnService;

	@FXML
	AnchorPane overviewAnchorMain;

	@FXML
	Text distanceTravelled, flightDate, flightLength, maxAlt, droneType, crtlBy, batteryPercent, batterySerial,
			batteryType, volConsumption, batteryProductDate, flightLoc, aircraftName, aircraftSerialNo, photosTaken,
			videoTime, maxSatellites, homeLoc, djiAppLoc, djiAppOsVersion, rcSerialNo, cameraSerialNo, maxSpeed;

	public void setWidthDimensions(double width){
		this.overviewAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.overviewAnchorMain.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.overviewAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.overviewAnchorMain.setPrefHeight(height / 1.1d);
			}
		});
	}

	protected void displayOverview() {

		List<OSDColumn> osdColumns = osdColumnService.getAllDetails();

		SingleColumn distanceTravel = customColumnService.getDistanceTravelled();

		DetailsColumn detailsColumns = detailsColumnService.getDetails();

		TimeAndTwoColumn homeLatLong = homeColumnService.fetchLatAndLongHomeDetails();

		AppGpsColumn appGpsColumn = appGpsColumnService.getAppGpsDetails();

		List<TimeAndTwoColumn> batteryAndVoltage = smartBatteryService.getBatteryAndVoltage();

		SingleColumn productDate = centerBatteryColumnService.getProductDate();

		int lastIndex = osdColumns.size() - 1;
		DecimalFormat df = new DecimalFormat("###.##");
		String dateTime = osdColumns.get(0).getTimestamp();
		String[] dateTimeSplit = dateTime.split(" ");
		flightDate.setText(dateTimeSplit[0]);

		String flyTime = df.format((Double.parseDouble(osdColumns.get(lastIndex).getFlyTime()) / 60));
		flightLength.setText("" + flyTime + " mins");

		distanceTravelled.setText(Double.parseDouble("" + distanceTravel.getColumn()) + " meters");

		// --------brute force---------
		double altitude = 0.0d;
		for (int i = 0; i <= lastIndex; i++) {
			if (Double.parseDouble(osdColumns.get(i).getAltitude()) > altitude) {
				altitude = Double.parseDouble(osdColumns.get(i).getAltitude());
			}
		}
		maxAlt.setText(altitude + " meters");
		// ----------------------------

		flightLoc.setText(detailsColumns.getDCity() + ", " + detailsColumns.getDArea());

		aircraftName.setText(detailsColumns.getDAircraftName());

		aircraftSerialNo.setText(detailsColumns.getDAirSnBytes());

		photosTaken.setText(detailsColumns.getDPhotoNum());

		videoTime.setText(detailsColumns.getDVideoTime() + " seconds");
		// ----------Brute Force---------
		int maxSat = 0;
		for (int i = 0; i <= lastIndex; i++) {
			if (Integer.parseInt(osdColumns.get(i).getGpsNum()) > maxSat) {
				maxSat = Integer.parseInt(osdColumns.get(i).getGpsNum());
			}
		}
		maxSatellites.setText("" + maxSat);
		// ------------------------------

		homeLoc.setText(homeLatLong.getColumn1() + ", " + homeLatLong.getColumn2());

		djiAppLoc.setText(appGpsColumn.getAgLat() + " , " + appGpsColumn.getAgLong());

		djiAppOsVersion.setText(detailsColumns.getDAppType() + " & " + detailsColumns.getDAppVersion());

		rcSerialNo.setText(detailsColumns.getDRcSn());

		cameraSerialNo.setText(detailsColumns.getDCameraSn());

		droneType.setText(osdColumns.get(1).getDroneType() + " - DJI Phantom 4");

		crtlBy.setText(osdColumns.get(1).getCtrlDevice().trim() + " (Radio Controller)");

		batterySerial.setText(detailsColumns.getDBatterySn());

		batteryType.setText(osdColumns.get(0).getBatteryType());

		batteryPercent.setText(batteryAndVoltage.get(0).getColumn1() + "%" + " - " + batteryAndVoltage.get(1).getColumn1() + "%");

		volConsumption.setText(batteryAndVoltage.get(0).getColumn2() + "%" + " - " + batteryAndVoltage.get(1).getColumn2() + "%");

		batteryProductDate.setText(productDate.getColumn());
		
		maxSpeed.setText(detailsColumns.getDMaxHSpeed() + " m/s");
		
	/*	String timeDate = list.get(0).getUpdateTime();
		System.out.println("time dte " + timeDate);
		String[] split = timeDate.split(" ");
		System.out.println(split[0] + " | " + split[1]);
		String test = split[1].substring(0, 2);
		int i = Integer.parseInt(test);
		String 
		System.out.println(i + 8);*/
	}
}
