package dforensics.dji.service.impl;

import dforensics.dji.domain.TimeAndColumn;
import dforensics.dji.entity.CustomAndOsdColumnsForKML;
import dforensics.dji.entity.DjiParameters;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class KmlCreation {

    public void buildKMLFile(List<CustomAndOsdColumnsForKML> columnsForKMLS, String fileName, String filePath){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Drone-Flight-Path.kml");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("KML files", "*.kml"));
        File fileSave = fileChooser.showSaveDialog(null);

        if(fileSave == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Destination");
            alert.setHeaderText("Please select the destination");
            alert.setContentText("No destination selected for saving the file.");
            alert.show();
            return;
        }

        DocumentBuilderFactory docFactory;
        DocumentBuilder docbuilder;
        TransformerFactory transformFac;
        Transformer transform;
        DOMSource source;
        StreamResult result;
        try {
            String firstLineTimeElement = null;
            String firstLineLatitude = null;
            String firstLineLongitude = null;
            String firstLineAltitudeElement = null;
            String lastLineTimeElement = null;
            String lastLineAltitudeElement = null;
            String lastLineLatitudeElement = null;
            String lastLineLongitudeElement = null;
            int sizeOfRows = columnsForKMLS.size();
            List<CustomAndOsdColumnsForKML> photoList = new ArrayList<>();
            List<CustomAndOsdColumnsForKML> videoList = new ArrayList<>();

            if (columnsForKMLS != null && !columnsForKMLS.isEmpty()) {
                firstLineTimeElement = columnsForKMLS.get(0).getTimestamp();
                firstLineLatitude = columnsForKMLS.get(0).getLatitude();
                firstLineLongitude = columnsForKMLS.get(0).getLongitude();
                firstLineAltitudeElement = columnsForKMLS.get(0).getAltitude();
                lastLineTimeElement = columnsForKMLS.get(sizeOfRows - 1).getTimestamp();
                lastLineAltitudeElement = columnsForKMLS.get(sizeOfRows - 1).getAltitude();
                lastLineLatitudeElement = columnsForKMLS.get(sizeOfRows - 1).getLatitude();
                lastLineLongitudeElement = columnsForKMLS.get(sizeOfRows - 1).getLongitude();
                System.out.println("Here it is: " + lastLineAltitudeElement + "  " + lastLineLatitudeElement + "  "
                        + lastLineLongitudeElement);
                photoList = columnsForKMLS.stream().filter(row -> row.getIsPhoto().equalsIgnoreCase("Yes")).collect(Collectors.toList());
                videoList = columnsForKMLS.stream().filter(row -> row.getIsVideo().equalsIgnoreCase("Recording")).collect(Collectors.toList());
            }
            String[] firstDateTimeDivide = firstLineTimeElement.split(" ");
            String firstDate = firstDateTimeDivide[0];
            String firstTime = firstDateTimeDivide[1];

            String[] lastDateTimeDivide = lastLineTimeElement.split(" ");
            String lastDate = lastDateTimeDivide[0];
            String lastTime = lastDateTimeDivide[1];

            docFactory = DocumentBuilderFactory.newInstance();
            docbuilder = docFactory.newDocumentBuilder();
            Document doc = docbuilder.newDocument();
            Element rootElement = doc.createElement("kml");
            doc.appendChild(rootElement);

            Attr attribute1 = doc.createAttribute("xmlns");
            attribute1.setValue("http://www.opengis.net/kml/2.2");
            rootElement.setAttributeNode(attribute1);

            Attr attribute2 = doc.createAttribute("xmlns:gx");
            attribute2.setValue("http://www.google.com/kml/ext/2.2");
            rootElement.setAttributeNode(attribute2);

            Element document = doc.createElement("Document");
            rootElement.appendChild(document);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode("FlightLog evaluation for DJI Phantom copter"));
            document.appendChild(name);

            Element desc = doc.createElement("description");
            desc.appendChild(doc.createTextNode(firstLineTimeElement + " - " + fileName));
            document.appendChild(desc);

            /* ~~~Style 1~~~ */

            Element style1 = doc.createElement("Style");
            document.appendChild(style1);

            Attr style1Attr = doc.createAttribute("id");
            style1Attr.setValue("Flightpath");
            style1.setAttributeNode(style1Attr);

            Element linestyle1 = doc.createElement("LineStyle");
            style1.appendChild(linestyle1);

            Element color1 = doc.createElement("color");
            color1.appendChild(doc.createTextNode("ff00ff00"));
            linestyle1.appendChild(color1);

            Element width1 = doc.createElement("width");
            width1.appendChild(doc.createTextNode("2"));
            linestyle1.appendChild(width1);

            Element polyStyle = doc.createElement("PolyStyle");
            style1.appendChild(polyStyle);

            Element colorPoly = doc.createElement("color");
            colorPoly.appendChild(doc.createTextNode("7f00ff00"));
            polyStyle.appendChild(colorPoly);

            /* ~~~Style 2~~~ */

            Element style2 = doc.createElement("Style");
            Attr style2Attr = doc.createAttribute("id");
            style2Attr.setValue("GrndStn");
            style2.setAttributeNode(style2Attr);
            document.appendChild(style2);

            Element linestyle2 = doc.createElement("LineStyle");
            style2.appendChild(linestyle2);

            Element color2 = doc.createElement("color");
            color2.appendChild(doc.createTextNode("FF000000"));
            linestyle2.appendChild(color2);

            Element width2 = doc.createElement("width");
            width2.appendChild(doc.createTextNode("2"));
            linestyle2.appendChild(width2);

            /* ~~~Style 3~~~ */

            Element style3 = doc.createElement("Style");
            Attr style3Attr = doc.createAttribute("id");
            style3Attr.setValue("starting");
            style3.setAttributeNode(style3Attr);
            document.appendChild(style3);

            Element iconStyle3 = doc.createElement("IconStyle");
            Element icon3 = doc.createElement("Icon");
            Element href3 = doc.createElement("href");
            href3.appendChild(doc.createTextNode("http://maps.google.com/mapfiles/dir_0.png"));
            icon3.appendChild(href3);
            iconStyle3.appendChild(icon3);
            style3.appendChild(iconStyle3);

            /* ~~~Style 4~~~ */

            Element style4 = doc.createElement("Style");
            Attr style4Attr = doc.createAttribute("id");
            style4Attr.setValue("landing");
            style4.setAttributeNode(style4Attr);
            document.appendChild(style4);

            Element iconStyle4 = doc.createElement("IconStyle");
            Element icon4 = doc.createElement("Icon");
            Element href4 = doc.createElement("href");
            href4.appendChild(doc.createTextNode("http://maps.google.com/mapfiles/dir_walk_60.png"));
            icon4.appendChild(href4);
            iconStyle4.appendChild(icon4);
            style4.appendChild(iconStyle4);

            /* ~~~ Style 5 ~~~*/

            Element style5 = doc.createElement("Style");
            Attr style5Attr = doc.createAttribute("id");
            style5Attr.setValue("image");
            style5.setAttributeNode(style5Attr);
            document.appendChild(style5);

            Element iconStyle5 = doc.createElement("IconStyle");
            Element icon5 = doc.createElement("Icon");
            Element href5 = doc.createElement("href");
            href5.appendChild(doc.createTextNode("https://img.icons8.com/color/48/000000/picture.png"));
            icon5.appendChild(href5);
            iconStyle5.appendChild(icon5);
            style5.appendChild(iconStyle5);

            /* ~~~ Style 6 ~~~ */

            Element style6 = doc.createElement("Style");
            document.appendChild(style6);

            Attr style6Attr = doc.createAttribute("id");
            style6Attr.setValue("Videopath");
            style6.setAttributeNode(style6Attr);

            Element linestyle6 = doc.createElement("LineStyle");
            style6.appendChild(linestyle6);

            Element color6 = doc.createElement("color");
            color6.appendChild(doc.createTextNode("ff0000ff"));
            linestyle6.appendChild(color6);

            Element width6 = doc.createElement("width");
            width6.appendChild(doc.createTextNode("8"));
            linestyle6.appendChild(width6);

            Element polyStyleVideo = doc.createElement("PolyStyle");
            style6.appendChild(polyStyleVideo);

            Element colorPolyVideo = doc.createElement("color");
            colorPolyVideo.appendChild(doc.createTextNode("7f00ff00"));
            polyStyleVideo.appendChild(colorPolyVideo);

            /* ~~~ Placemark 1 ~~~ */

            Element placemark1 = doc.createElement("Placemark");
            Element timeStamp1 = doc.createElement("TimeStamp");
            Element when1 = doc.createElement("when");
            when1.appendChild(doc.createTextNode(firstDate + "T" + firstTime + "Z"));
            timeStamp1.appendChild(when1);
            placemark1.appendChild(timeStamp1);
            document.appendChild(placemark1);
            Element styleURLStarting = doc.createElement("styleUrl");
            styleURLStarting.appendChild(doc.createTextNode("#starting"));
            placemark1.appendChild(styleURLStarting);
            Element point1 = doc.createElement("Point");
            Element coordinates1 = doc.createElement("coordinates");
            coordinates1.appendChild(
                    doc.createTextNode(firstLineLongitude + "," + firstLineLatitude + "," + firstLineAltitudeElement));
            point1.appendChild(coordinates1);
            placemark1.appendChild(point1);

            /* ~~~ Photo Placemark ~~~ */

            if(photoList != null && !photoList.isEmpty()){

                photoList.stream().forEach(photo -> {
                    Element placemark4 = doc.createElement("Placemark");
                    document.appendChild(placemark4);
                    String[] timeDateSplit = photo.getTimestamp().split(" ");

                    Element nameForPhoto = doc.createElement("name");
                    nameForPhoto.appendChild(doc.createTextNode("Image"));
                    placemark4.appendChild(nameForPhoto);
                    Element descForPhoto = doc.createElement("description");
                    descForPhoto.appendChild(doc.createTextNode("Latitude: " + photo.getLatitude() + "\n" + "Longitude: " +
                            photo.getLongitude() + "\n" + "Altitude: " + photo.getAltitude() + "\n" + "Date: " + timeDateSplit[0] + "\n" + "Time: " + timeDateSplit[1]));
                    placemark4.appendChild(descForPhoto);

/*                    Element timeStamp4 = doc.createElement("TimeStamp");
                    Element when4 = doc.createElement("when");
                    when4.appendChild(doc.createTextNode( timeDateSplit[0] + "T" + timeDateSplit[1] + "Z"));
                    timeStamp4.appendChild(when4);
                    placemark4.appendChild(timeStamp4);*/

                    Element styleURLPhoto = doc.createElement("styleUrl");
                    styleURLPhoto.appendChild(doc.createTextNode("#image"));
                    placemark4.appendChild(styleURLPhoto);

                    Element point4 = doc.createElement("Point");
                    Element altMode = doc.createElement("altitudeMode");
                    altMode.appendChild(doc.createTextNode("relativeToGround"));
                    Element coordinates4 = doc.createElement("coordinates");
                    coordinates4.appendChild(
                            doc.createTextNode(photo.getLongitude() + "," + photo.getLatitude() + "," + photo.getAltitude()));
                    point4.appendChild(coordinates4);
                    point4.appendChild(altMode);
                    placemark4.appendChild(point4);

                });
            }

            /* ~~~ Video Placemark ~~~ */

            if(videoList != null && !videoList.isEmpty()){
                    Element videoPlacemark = doc.createElement("Placemark");
                    document.appendChild(videoPlacemark);

                    Element videoName = doc.createElement("name");
                    videoName.appendChild(doc.createTextNode("DJI Phantom 4 Video Path"));
                    videoPlacemark.appendChild(videoName);

                    Element desc1 = doc.createElement("description");
                    desc1.appendChild(doc.createTextNode("Video has been recorded here"));
                    videoPlacemark.appendChild(desc1);

                    Element styleURLVideoPath = doc.createElement("styleUrl");
                    styleURLVideoPath.appendChild(doc.createTextNode("#Videopath"));
                    videoPlacemark.appendChild(styleURLVideoPath);

                    Element lineString = doc.createElement("LineString");
                    videoPlacemark.appendChild(lineString);
                    Element altOffset = doc.createElement("gx:altitudeOffset");
                    altOffset.appendChild(doc.createTextNode("190.0"));
                    lineString.appendChild(altOffset);
                    Element altMode = doc.createElement("altitudeMode");
                    altMode.appendChild(doc.createTextNode("absolute"));
                    lineString.appendChild(altMode);
                    Element videoCoordinates = doc.createElement("coordinates");

                    videoList.stream().forEach(video ->
                        videoCoordinates.appendChild(doc.createTextNode("\n" + video.getLongitude() + ","
                                + video.getLatitude() + "," + video.getAltitude() + "\n")));
                    lineString.appendChild(videoCoordinates);
            }

            /* ~~~ Placemark 2 ~~~ */

            Element placemark2 = doc.createElement("Placemark");
            document.appendChild(placemark2);
            Element name1 = doc.createElement("name");
            name1.appendChild(doc.createTextNode("DJI Phantom 4 Flight Path"));
            placemark2.appendChild(name1);
            Element desc1 = doc.createElement("description");
            desc1.appendChild(doc.createTextNode(fileName));
            placemark2.appendChild(desc1);
            Element styleURLFlightPath = doc.createElement("styleUrl");
            styleURLFlightPath.appendChild(doc.createTextNode("#Flightpath"));
            placemark2.appendChild(styleURLFlightPath);
            Element lineString = doc.createElement("LineString");
            placemark2.appendChild(lineString);
            Element altOffset = doc.createElement("gx:altitudeOffset");
            altOffset.appendChild(doc.createTextNode("190.0"));
            lineString.appendChild(altOffset);
            Element altMode = doc.createElement("altitudeMode");
            altMode.appendChild(doc.createTextNode("absolute"));
            lineString.appendChild(altMode);
            Element coordinates2 = doc.createElement("coordinates");
            for (CustomAndOsdColumnsForKML rowValues : columnsForKMLS) {

                coordinates2.appendChild(doc.createTextNode("\n" + rowValues.getLongitude() + ","
                        + rowValues.getLatitude() + "," + rowValues.getAltitude() + "\n"));
            }
            lineString.appendChild(coordinates2);

            /* ~~~ Placemark 3 ~~~ */
            lineString.appendChild(coordinates2);
            Element placemark3 = doc.createElement("Placemark");
            document.appendChild(placemark3);
            Element timeStamp2 = doc.createElement("TimeStamp");
            placemark3.appendChild(timeStamp2);
            Element when2 = doc.createElement("when");
            when2.appendChild(doc.createTextNode(lastDate + "T" + lastTime + "Z"));
            timeStamp2.appendChild(when2);
            Element styleURLLanding = doc.createElement("styleUrl");
            styleURLLanding.appendChild(doc.createTextNode("#landing"));
            placemark3.appendChild(styleURLLanding);
            Element point2 = doc.createElement("Point");
            placemark3.appendChild(point2);
            Element coordinates3 = doc.createElement("coordinates");
            coordinates3.appendChild(doc.createTextNode(
                    lastLineLongitudeElement + "," + lastLineLatitudeElement + "," + lastLineAltitudeElement));
            point2.appendChild(coordinates3);

            transformFac = TransformerFactory.newInstance();
            try {
                transform = transformFac.newTransformer();
                transform.setOutputProperty(OutputKeys.INDENT, "yes");
                transform.setOutputProperty(OutputKeys.METHOD, "xml");
                transform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                source = new DOMSource(doc);
                result = new StreamResult(new File(fileSave.getAbsolutePath()));

                transform.transform(source, result);

                Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
                successAlert.setTitle("KML File Conversion Status");
                successAlert.setHeaderText("Success!");
                successAlert.setContentText("KML File saved at: " + filePath);
                successAlert.show();

                log.info("Saved at location: " + fileSave.getAbsolutePath());
            } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException ex){
            Alert nullAlert = new Alert(Alert.AlertType.ERROR);
            nullAlert.setTitle("Got a Null Value!!");
            nullAlert.setHeaderText("Important field in CSV file has NULL value(s)." + "\n" + "Failed to read CSV file.");
            nullAlert.setContentText("Could not able to create KML file.");
            nullAlert.show();
            ex.printStackTrace();
        }
    }
}
