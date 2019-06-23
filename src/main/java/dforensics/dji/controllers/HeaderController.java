package dforensics.dji.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import dforensics.dji.entity.*;
import dforensics.dji.repository.CustomColumnRepo;
import dforensics.dji.service.*;
import dforensics.dji.service.impl.KmlCreation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

@Slf4j
@Controller
public class HeaderController {

	@Autowired
	DJIMainController djiMainController;

	@Autowired
	CustomColumnService customColumnService;

	@Autowired
	OSDColumnService osdColumnService;

	@Autowired
	GimbalColumnService gimbalColumnService;

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

	@FXML private Text fileReadNotice;
	@FXML TextField kmlFilePath;
	@FXML Button uploadFileButton;
	@FXML AnchorPane headerAnchorPane;
	@FXML AnchorPane headerAnchorPaneMain;
	@FXML SplitPane splitPane;
	@FXML Button convertToKMLButton;
	@FXML Button goButton;

	String filePath;
	String fileName;
	String kmlPath;

	List<DjiParameters> paramValues = new ArrayList<>();
	List<CustomColumn> customColumns = new ArrayList<>();
	List<OSDColumn> osdColumns = new ArrayList<>();
	List<GimbalColumn> gimbalColumns = new ArrayList<>();
	List<SmartBatteryColumn> smartBatteryColumns = new ArrayList<>();

	public void injectHeader(DJIMainController mainController) {
		if(uploadFileButton.isPressed()) this.djiMainController = mainController;
	}

	public double getPrefHeight(){
		return headerAnchorPaneMain.getPrefHeight();
	}

	public void setWidthDimensions(double width){
		this.headerAnchorPaneMain.getScene().widthProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				this.headerAnchorPaneMain.setPrefWidth(width);
				this.headerAnchorPane.setPrefWidth(width);
				this.splitPane.setPrefWidth(width);
				this.kmlFilePath.setPrefWidth(width/2.5d);
				this.convertToKMLButton.setLayoutX(width / 1.3d);
				this.goButton.setLayoutX(width / 1.3d);
				System.out.println("Layout X: " + convertToKMLButton.getLayoutX());
			}
		});
		System.out.println("Pref width: " + headerAnchorPaneMain.getPrefWidth());
	}
	@FXML
	private void uploadFile(ActionEvent event) {

		// ToDo: When uploaded again then the current tab will not load the new values. Need Work.
		if(!customColumns.isEmpty() || !osdColumns.isEmpty() || !gimbalColumns.isEmpty() || !smartBatteryColumns.isEmpty()){
			customColumnService.delete();
			osdColumnService.delete();
			gimbalColumnService.delete();
			smartBatteryService.delete();
		}

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose a CSV Log File");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			filePath = file.getAbsolutePath();
			fileName = file.getName();
			CSVReader reader;
			DetailsColumn detailsColumn = null;
			HomeColumn homeColumn = null;
			AppGpsColumn appGpsColumn = null;
			CenterBatteryColumn centerBatteryColumn = null;
			try {
				reader = new CSVReader(new FileReader(filePath), ',', '\'', 2);
				String[] column;
				while ((column = reader.readNext()) != null) {

					/**
					 * Reading for Custom Column
					 */
					CustomColumn customColumn = new CustomColumn(column[0], column[1], column[2], column[3], column[4],
							column[5], column[6], column[7], column[8]);
					customColumns.add(customColumn);

					/**
					 * Reading for OSD Column
					 */
					OSDColumn osdColumn = new OSDColumn(column[0], column[9], column[10], column[11], column[12],
							column[13], column[14], column[15], column[16], column[17], column[18], column[19],
							column[20], column[21], column[22], column[23], column[24], column[25], column[26],
							column[27], column[28], column[29], column[30], column[31], column[32], column[33],
							column[34], column[35], column[36], column[37], column[38], column[39], column[40],
							column[41], column[42], column[43], column[44], column[45], column[46], column[47],
							column[48], column[49], column[50], column[51], column[52], column[53], column[54],
							column[55], column[56], column[57], column[58], column[59], column[60], column[61],
							column[62], column[63], column[64], column[65]);
					osdColumns.add(osdColumn);

					/**
					 * Reading for Gimbal Column
					 */
					GimbalColumn gimbalColumn = new GimbalColumn(column[0], column[66], column[67], column[68],
							column[69], column[70], column[71], column[72], column[73], column[74], column[75],
							column[76], column[77], column[78], column[79], column[80], column[81], column[82],
							column[83], column[84], column[85]);
					gimbalColumns.add(gimbalColumn);

					SmartBatteryColumn smartBatteryColumn = new SmartBatteryColumn(column[0], column[124],
							column[125], column[126], column[127], column[128], column[129], column[130], column[131],
							column[132], column[133], column[134], column[135], column[136], column[137], column[138],
							column[139], column[140], column[141], column[142]);
					smartBatteryColumns.add(smartBatteryColumn);

					detailsColumn = new DetailsColumn(column[0], column[210], column[211], column[212], column[213], column[214], column[215],
					column[216], column[217], column[218], column[219], column[220], column[221], column[222],
					column[223], column[224], column[225], column[226], column[227], column[228], column[229],
					column[230], column[231], column[232], column[233], column[234], column[235], column[236],
					column[237]);

					homeColumn = new HomeColumn(column[0], column[157], column[158], column[159],
							column[160], column[161], column[162], column[163], column[164], column[165], column[166],
							column[167], column[168], column[169], column[170], column[171], column[172], column[173],
							column[174], column[175], column[176], column[177], column[178], column[179], column[180],
							column[181], column[182], column[183], column[184], column[185], column[186], column[187],
							column[188], column[189], column[190], column[191], column[192], column[193], column[194],
							column[195], column[196], column[197], column[198]);

					appGpsColumn = new AppGpsColumn(column[0], column[238], column[239], column[240]);

					centerBatteryColumn = new CenterBatteryColumn(column[0], column[102], column[103],
							column[104], column[105], column[106], column[107], column[108], column[109], column[110],
							column[111], column[112], column[113], column[114], column[115], column[116], column[117],
							column[118], column[119], column[120], column[121], column[122], column[123]);
/*
					DjiParameters params = new DjiParameters(column[0], column[1], column[2], column[3], column[4],
							column[5], column[6], column[7], column[8], column[9], column[10], column[11], column[12],
							column[13], column[14], column[15], column[16], column[17], column[18], column[19],
							column[20], column[21], column[22], column[23], column[24], column[25], column[26],
							column[27], column[28], column[29], column[30], column[31], column[32], column[33],
							column[34], column[35], column[36], column[37], column[38], column[39], column[40],
							column[41], column[42], column[43], column[44], column[45], column[46], column[47],
							column[48], column[49], column[50], column[51], column[52], column[53], column[54],
							column[55], column[56], column[57], column[58], column[59], column[60], column[61],
							column[62], column[63], column[64], column[65], column[66], column[67], column[68],
							column[69], column[70], column[71], column[72], column[73], column[74], column[75],
							column[76], column[77], column[78], column[79], column[80], column[81], column[82],
							column[83], column[84], column[85], column[86], column[87], column[88], column[89],
							column[90], column[91], column[92], column[93], column[94], column[95], column[96],
							column[97], column[98], column[99], column[100], column[101], column[102], column[103],
							column[104], column[105], column[106], column[107], column[108], column[109], column[110],
							column[111], column[112], column[113], column[114], column[115], column[116], column[117],
							column[118], column[119], column[120], column[121], column[122], column[123], column[124],
							column[125], column[126], column[127], column[128], column[129], column[130], column[131],
							column[132], column[133], column[134], column[135], column[136], column[137], column[138],
							column[139], column[140], column[141], column[142], column[143], column[144], column[145],
							column[146], column[147], column[148], column[149], column[150], column[151], column[152],
							column[153], column[154], column[155], column[156], column[157], column[158], column[159],
							column[160], column[161], column[162], column[163], column[164], column[165], column[166],
							column[167], column[168], column[169], column[170], column[171], column[172], column[173],
							column[174], column[175], column[176], column[177], column[178], column[179], column[180],
							column[181], column[182], column[183], column[184], column[185], column[186], column[187],
							column[188], column[189], column[190], column[191], column[192], column[193], column[194],
							column[195], column[196], column[197], column[198], column[199], column[200], column[201],
							column[202], column[203], column[204], column[205], column[206], column[207], column[208],
							column[209], column[210], column[211], column[212], column[213], column[214], column[215],
							column[216], column[217], column[218], column[219], column[220], column[221], column[222],
							column[223], column[224], column[225], column[226], column[227], column[228], column[229],
							column[230], column[231], column[232], column[233], column[234], column[235], column[236],
							column[237], column[238], column[239], column[240], column[241], column[242]);
					paramValues.add(params);
*/
				}
				fileReadNotice.setText("File Read Success");

				if(customColumns != null && !customColumns.isEmpty()) customColumnService.save(customColumns);
				if(osdColumns != null && !osdColumns.isEmpty()) osdColumnService.save(osdColumns);
				if(gimbalColumns != null && !gimbalColumns.isEmpty()) gimbalColumnService.save(gimbalColumns);
				if(smartBatteryColumns != null && !smartBatteryColumns.isEmpty()) smartBatteryService.save(smartBatteryColumns);
				if(detailsColumn != null) detailsColumnService.save(detailsColumn);
				if(homeColumn != null) homeColumnService.save(homeColumn);
				if(appGpsColumn != null) appGpsColumnService.save(appGpsColumn);
				if(centerBatteryColumn != null) centerBatteryColumnService.save(centerBatteryColumn);

				djiMainController.getListForOverview();
				
			} catch (IOException io) {
				Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, io);
			}
		} else {
			fileReadNotice.setText("File Read Failed");
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error");
			errorAlert.setHeaderText("Error Information");
			errorAlert.setContentText("Error while selection");
			errorAlert.show();
		}

	}
	
	@FXML
	private void kmlFileLoc(ActionEvent event) {
		kmlPath = kmlFilePath.getText();
		djiMainController.getMap(kmlPath);
	}

	@FXML
	private void convertCSVToKML(ActionEvent event) {

		List<CustomAndOsdColumnsForKML> columnsForKMLS = new ArrayList<>();

		if(osdColumns != null && !osdColumns.isEmpty() && customColumns != null && !customColumns.isEmpty()){
			int i = 0;
			while(i < osdColumns.size()){
				CustomAndOsdColumnsForKML columnsForKML = new CustomAndOsdColumnsForKML(customColumns.get(i).getUpdateTime(), customColumns.get(i).getIsPhoto(),
						customColumns.get(i).getIsVideo(), osdColumns.get(i).getLatitude(), osdColumns.get(i).getLongitude(), osdColumns.get(i).getAltitude());
				columnsForKMLS.add(columnsForKML);
				i++;
			}
			KmlCreation kmlCreation = new KmlCreation();
			kmlCreation.buildKMLFile(columnsForKMLS, fileName, filePath);
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("File Not Found");
			alert.setHeaderText("CSV log file not uploaded");
			alert.setContentText("Please upload the CSV log file to convert it to KML file.");
			alert.show();
			return;
		}
	}
}
