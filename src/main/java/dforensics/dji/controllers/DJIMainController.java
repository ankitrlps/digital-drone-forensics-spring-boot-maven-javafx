package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.entity.DjiParameters;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class DJIMainController {

	@FXML private HeaderController 	headerController;
	@FXML private OverviewTabController overViewTabController;
	@FXML private MapTabController mapTabController;
	@FXML private FlightControlController flightControlController;
	@FXML private BatteryTabController batteryTabController;
	@FXML private SpeedTabController speedTabController;
	@FXML private DroneRollPitchYawController rpyDroneTabController;
	@FXML private AltitudeTabController altitudeTabController;
	@FXML private RollPitchYawGimbalController rpyGimbalController;
	@FXML private SatellitesNumberController satelliteNumberController;
	@FXML private ModelRotationController tdRotationController;
	@FXML private MetaDataController metaDataController;

	@FXML private ImageView view;

	@FXML
	TabPane tabPane;

	@FXML
	Tab flightControlTab;

	@FXML
	Tab altitudeControlTab;

	@FXML
	Tab satellitesTab;

	@FXML
	Tab speedControlTab;

	@FXML
	Tab gPRYTab;

	@FXML
	Tab batteryControlTab;

	@FXML
	Tab overviewTab;

	@FXML
	Tab dronePRYTab;

	@FXML
	BorderPane borderPaneMain;

	@FXML AnchorPane bottomAnchorMain;

	@FXML AnchorPane leftAnchor;

	private int flightControlTabcount = 0;
	private int altitudeTabCount = 0;
	private int dronePRYTabCount = 0;
	private int satellitesTabCount = 0;
	private int speedTabCount = 0;
	private int gPRYTabCount = 0;
	private int batteryControlTabCount = 0;

	@FXML private void initialize() {
		//headerController.injectHeader(this);
		Image image = new Image("/DJI_Image.png");
		view.setImage(image);
		borderPaneMain.sceneProperty().addListener(observable -> {
			if (borderPaneMain.getScene() != null){
				borderPaneMain.getScene().widthProperty().addListener((observable1, oldValue, newValue) -> {
					if(newValue != null){
						System.out.println("Width " + newValue.doubleValue());
						headerController.setWidthDimensions(newValue.doubleValue());
						mapTabController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						overViewTabController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						flightControlController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						batteryTabController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						altitudeTabController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						rpyDroneTabController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						rpyGimbalController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						tdRotationController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						satelliteNumberController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						speedTabController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
						metaDataController.setWidthDimensions(newValue.doubleValue() - getLeftAreaWidth());
					}
				});

				/* - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight())*/
				borderPaneMain.getScene().heightProperty().addListener((observable1, oldValue, newValue) -> {
					if(newValue != null){
						System.out.println("Height: " + newValue.doubleValue());
						mapTabController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						overViewTabController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						flightControlController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						batteryTabController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						altitudeTabController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						rpyDroneTabController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						rpyGimbalController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						tdRotationController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						satelliteNumberController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						speedTabController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
						metaDataController.setHeightDimensions(newValue.doubleValue() - (headerController.getPrefHeight() + bottomAnchorMain.getPrefHeight()));
					}
				});
			}
		});

		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if (newValue == flightControlTab){
					flightControlTabcount++;
					if(flightControlTabcount == 1){
						log.info("Tab selected: Flight Control Tab");
						loadFlightController();
					}
				}else if(newValue == altitudeControlTab){
					altitudeTabCount++;
					if(altitudeTabCount == 1){
                        log.info("Tab selected: Altitude Tab");
						loadAltitudeController();
					}
				}else if(newValue == dronePRYTab){
					dronePRYTabCount++;
					if(dronePRYTabCount == 1){
						log.info("Tab selected: Drone PRY Tab");
						loadDronePRYController();
					}
				}else if(newValue == satellitesTab){
					satellitesTabCount++;
					if(satellitesTabCount == 1){
						log.info("Tab Selected: Number of Satellites Tab");
						loadSatellitesController();
					}
				}else if(newValue == speedControlTab){
					speedTabCount++;
					if(speedTabCount == 1){
						log.info("Tab Selected: Speed Tab");
						loadSpeedController();
					}
				}else if(newValue == gPRYTab){
					gPRYTabCount++;
					if(gPRYTabCount == 1){
						log.info("Tab Selected: Gimbal PRY Tab");
						loadGimablPRYController();
					}
				}else if(newValue == batteryControlTab){
					batteryControlTabCount++;
					if(batteryControlTabCount == 1){
						log.info("Tab Selected: Battery Tab");
						loadBatteryController();
					}
				}
			}
		});
	}

	private double getLeftAreaWidth(){
		return this.leftAnchor.getPrefWidth();
	}

	private void loadFlightController(){
		flightControlController.showFlightControl();
		log.info("Data has been loaded for Flight Control Tab");
	}

	private void loadAltitudeController(){
		altitudeTabController.displayAltitude();
		log.info("Data has been loaded for Altitude Tab");
	}

	private void loadDronePRYController(){
		rpyDroneTabController.dislayDroneRPY();
		log.info("Data has been loaded for Drone PRY Tab");
	}

	private void loadSatellitesController(){
		satelliteNumberController.displaySatellites();
		log.info("Data has been loaded for Satellites Tab");
	}

	private void loadSpeedController(){
		speedTabController.dislaySpeed();
		log.info("Data has been loaded for Speed Tab");
	}

	private void loadGimablPRYController(){
		rpyGimbalController.dislayGimbalRPY();
		log.info("Data has been fetched for Gimabl PRY Tab");
	}
	
	public void getMap(String kmlPath) {
		mapTabController.openMaps(kmlPath);
	}
	
	public void getListForOverview(){
		log.info("Entered to Overview Tab");
		overViewTabController.displayOverview();
	}

	private void loadBatteryController() {
		batteryTabController.displayBattery();
		log.info("Data has been fetched for Battery Tab");
	}
}