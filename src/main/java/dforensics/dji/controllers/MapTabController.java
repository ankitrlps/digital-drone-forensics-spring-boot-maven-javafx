package dforensics.dji.controllers;

import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Controller;

@Controller
public class MapTabController {

	@FXML WebView webView;

	@FXML AnchorPane mapAnchorMain;
	/*
	DJIMainController djiMainController;*/
	
	private final static String htmlFile = "/kmlMap.html";

	public void setWidthDimensions(double width){
		mapAnchorMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.mapAnchorMain.setPrefWidth(width / 1.1d);
				this.webView.setPrefWidth(width / 1.1d);
			}
		});
	}

	public void setHeightDimensions(double height){
		mapAnchorMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.mapAnchorMain.setPrefHeight(height / 1.1d);
				this.webView.setPrefHeight(height / 1.2d);
			}
		});
	}
	
	public void openMaps(String kmlFilePath) {
		WebEngine engine = webView.getEngine();
		URL localURL = getClass().getResource(htmlFile);
		
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				if(newValue == State.SUCCEEDED){
					//engine.executeScript("initMap('"+kmlFilePath+"')");
					JSObject win = (JSObject) engine.executeScript("window");
					win.setMember("javaObj", new JsObjectBridge(engine, kmlFilePath));
					engine.executeScript("javaObj.start()");
				}
			};
		});
		engine.load(localURL.toString());
	}
	
/*	public void injectMapTab(DJIMainController mainController) {
		this.djiMainController = mainController;
	}*/
}
