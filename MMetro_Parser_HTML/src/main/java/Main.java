import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.Document;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static final String htmlFile = "src\\main\\resources\\code.html";
    private static final String jsonFile = "src\\main\\resources\\map.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        try {

            Document document = Jsoup.parse(new File(htmlFile), "UTF-8");
            List<Station> stations = getListStations(document);
            List<MetroLine> lines = getListLines(document);
            getAllStations(document);


            Map<String, List<String>> metro = new HashMap<>();
            for (int i = 0; i < lines.size(); i++) {
                List<String> stationToLine = new ArrayList<>();
                MetroLine line = (MetroLine) lines.get(i);
                for (int j = 0; j < stations.size(); j++) {
                    Station station = (Station) stations.get(j);
                    if (station.getName().contains(line.getNum())) {
                        stationToLine.add(station.getLine());
                    }
                }
                metro.put(line.getName(), stationToLine);
            }
            JSONObject metroLine = new JSONObject();
            metroLine.put("lines:", lines);
            metroLine.put("stations:", metro);

            String gsonMetro = GSON.toJson(metroLine);
            File file = new File(jsonFile);
            PrintWriter fileJson = new PrintWriter(file);
            fileJson.println(gsonMetro);

            fileJson.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getAllStations(Document document){
        Elements listStation = document.select(".js-metro-stations");
        for (Element lines : listStation) {
            List<String> parts = Arrays.asList(lines.select(".js-metro-stations").text()
            .replaceAll("\\d+.", "")
            .trim().split("\\s\\s"));
            System.out.println("Номер линии " + lines.attr("data-line") + " - " + parts.size() + " сумма станций");
        }
    }


    public static List<Station> getListStations(Document document) {
        String lineNumber;
        Elements elementsNameStations = document.select(".js-metro-stations");
        List<Station> stations = new ArrayList<>();
        for (Element elementsNameStation : elementsNameStations) {
            lineNumber = elementsNameStation.attr("data-line");
            Elements elementsObjects = elementsNameStation.select("span.name");
            for (Element elementsObject : elementsObjects) {
                stations.add(new Station(elementsObject.text(), lineNumber));
            }
        }
        return stations;
    }
    public static List<MetroLine> getListLines(Document document) {
        Elements elementsNameLines = document.select(".js-metro-line");
        List<MetroLine> lines = new ArrayList<>();
        for (Element elementsNameLine : elementsNameLines) {
            lines.add(new MetroLine(elementsNameLine.attr("data-line"), elementsNameLine.text()));
        }
        return lines;
    }

}
