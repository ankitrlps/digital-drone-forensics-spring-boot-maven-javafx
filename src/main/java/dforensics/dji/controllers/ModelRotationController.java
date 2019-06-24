package dforensics.dji.controllers;

import java.net.URL;
import java.util.List;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.tds.TdsModelImporter;
import com.opencsv.CSVReader;

import dforensics.dji.domain.TimeAndThreeColumns;
import dforensics.dji.service.OSDColumnService;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ModelRotationController {


	@FXML
	AnchorPane tDAnchorPaneInner, tDAnchorPaneOuter, tDAnchorPaneMain, buttonAnchorPane;

	@FXML
	SplitPane splitPane;

	@FXML
	ButtonBar buttonBar;

	@FXML
	Button startButton, pauseButton, stopButton;
	FileChooser fileChooser = new FileChooser();
	String filePath;
	CSVReader reader = null;
	TdsModelImporter model = new TdsModelImporter();

	@Autowired
	OSDColumnService osdColumnService;

	private final Rotate cameraXRotate = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
	private final Rotate cameraYRotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
	private final Translate cameraPosition = new Translate(-300, -550, -700);
	private double dragStartX, dragStartY, dragStartRotateX, dragStartRotateY;

	public void setWidthDimensions(double width){
		this.tDAnchorPaneMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.tDAnchorPaneMain.setPrefWidth(width / 1.04d);
				this.splitPane.setPrefWidth(width / 1.05d);
				this.buttonAnchorPane.setPrefWidth(this.splitPane.getPrefWidth());
				this.buttonBar.setPrefWidth(this.splitPane.getPrefWidth());
				this.tDAnchorPaneOuter.setPrefWidth(width / 1.1d);
				this.tDAnchorPaneInner.setPrefWidth(width / 1.2d);
			}
		});
	}

	public void setHeightDimensions(double height){
		this.tDAnchorPaneMain.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.tDAnchorPaneMain.setPrefHeight(height/1.1d);
				this.splitPane.setPrefHeight(height/1.2d);
				this.tDAnchorPaneOuter.setPrefHeight((height - buttonAnchorPane.getPrefHeight()) / 1.3d);
				this.tDAnchorPaneInner.setPrefHeight((height - buttonAnchorPane.getPrefHeight()) / 1.4d);
			}
		});
	}

	@FXML
	private void upload(ActionEvent event) {
		showFigure(osdColumnService.getPitchRollYawWithTime());
	}

	private Group show3D() {
		try {
			URL modelUrl = this.getClass().getResource("/tds/hst.3ds");
			model.read(modelUrl);
		} catch (ImportException e) {
			System.out.println("Error importing obj model: " + e.getMessage());
		}
		final Node[] modelMesh = model.getImport();
		model.close();
		Group group = new Group();
		group.getChildren().addAll(modelMesh);

		return group;
	}

	private Group buildScene(Group modelGroup) {
		Group group1 = new Group();
		PointLight pointLight = new PointLight();
		pointLight = new PointLight(Color.ALICEBLUE);
		pointLight.setTranslateX(800);
		pointLight.setTranslateY(-900);
		pointLight.setTranslateZ(-1000);

		group1.getChildren().addAll(modelGroup, pointLight);
		return group1;
	}

	private SubScene createSubScene(Group groupScene) {
		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateX(-300);
		camera.setTranslateY(300);
		camera.setTranslateZ(300);
		camera.getTransforms().addAll(cameraXRotate, cameraYRotate, cameraPosition);
		SubScene scene = new SubScene(groupScene, 300, 200, true, SceneAntialiasing.BALANCED);
		scene.widthProperty().bind((this.tDAnchorPaneInner).widthProperty());
		scene.heightProperty().bind((this.tDAnchorPaneInner).heightProperty());
		scene.setCamera(camera);

		scene.addEventHandler(MouseEvent.ANY, (MouseEvent event) -> {
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				dragStartX = event.getSceneX();
				dragStartY = event.getSceneY();
				dragStartRotateX = cameraXRotate.getAngle();
				dragStartRotateY = cameraYRotate.getAngle();
			} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				double xDelta = event.getSceneX() - dragStartX;
				double yDelta = event.getSceneY() - dragStartY;
				cameraXRotate.setAngle(dragStartRotateX - (yDelta * 0.7));
				cameraYRotate.setAngle(dragStartRotateY + (xDelta * 0.7));
			}
		});

		return scene;
	}

	private void showFigure(List<TimeAndThreeColumns> rpyList) {
		Group modelGroup = show3D();
		Group buildModelGroup = buildScene(modelGroup);
		SubScene mainSubScene = createSubScene(buildModelGroup);
		double i = 600d;
		Timeline timeline = new Timeline();
		Node n = modelGroup;

		for (TimeAndThreeColumns listValues : rpyList) {
			double rollValue = Double.parseDouble(listValues.getColumn2());
			double pitchValue = Double.parseDouble(listValues.getColumn1());
			double yawValue = Double.parseDouble(listValues.getColumn3());
			
			double A11 = Math.cos(rollValue) * Math.cos(yawValue);
			double A12 = Math.cos(pitchValue) * Math.sin(rollValue)
					+ Math.cos(rollValue) * Math.sin(pitchValue) * Math.sin(yawValue);
			double A13 = Math.sin(rollValue) * Math.sin(pitchValue)
					- Math.cos(rollValue) * Math.cos(pitchValue) * Math.sin(yawValue);
			double A21 = -(Math.cos(yawValue) * Math.sin(rollValue));
			double A22 = Math.cos(rollValue) * Math.cos(pitchValue)
					- Math.sin(rollValue) * Math.sin(pitchValue) * Math.sin(yawValue);
			double A23 = Math.cos(rollValue) * Math.sin(pitchValue)
					+ Math.cos(pitchValue) * Math.sin(rollValue) * Math.sin(yawValue);
			double A31 = Math.sin(yawValue);
			double A32 = -(Math.cos(yawValue) * Math.sin(pitchValue));
			double A33 = Math.cos(pitchValue) * Math.cos(yawValue);
			double angle = Math.acos((A11 + A22 + A33 - 1d) / 2d);
			// an.add(angle);
			if (angle != 0d) {
				double denom = 2d * Math.sin(angle);
				Point3D p = new Point3D((A32 - A23) / denom, (A13 - A31) / denom, (A21 - A12) / denom);
				n.setRotationAxis(p);
				n.setRotate(Math.toDegrees(angle));
				timeline.getKeyFrames()
						.addAll(new KeyFrame(Duration.millis(i),
								new KeyValue(n.rotateProperty(), n.getRotate(), Interpolator.LINEAR)),
								new KeyFrame(Duration.seconds(i++/100), new KeyValue(n.rotateProperty(),
										n.getRotate() + Math.toDegrees(angle), Interpolator.LINEAR)),
								new KeyFrame(Duration.millis(500)));
			}

		}
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeline.play();	
			}
		});
		
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeline.pause();	
			}
		});
		
		stopButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeline.stop();	
			}
		});

		this.tDAnchorPaneInner.getChildren().addAll(mainSubScene);

	}

}
