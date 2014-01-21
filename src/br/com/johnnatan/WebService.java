package br.com.johnnatan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import util.HttpHelper;
import util.XMLUtils;
import br.com.johnnatan.sensores.widget.Address;
import br.com.johnnatan.sensores.widget.SensoresItem;

public class WebService {

	private static String URL = "http://192.168.1.26:8080/EJBSensorCity/rest/";
	private static final String charset = "UTF-8";

	public static List<SensoresItem> getSensors() throws IOException {
		String url = URL + "sensor/get/sensor";
		String xml = HttpHelper.doGet(url, charset);
		List<SensoresItem> sensors = parserXML(xml);
		return sensors;
	}

	private static List<SensoresItem> parserXML(String xml) throws IOException {
		Element root = XMLUtils.getRoot(xml, charset);
		List<SensoresItem> sensors = new ArrayList<SensoresItem>();
		List<Node> nodeSensores = XMLUtils.getChildren(root, "sensor");

		for (Node node : nodeSensores) {
			SensoresItem sensor = new SensoresItem();
			Address address = new Address();

			Node nodeAddress = XMLUtils.getChild(node, "address");
			address.setCity(XMLUtils.getText(nodeAddress, "city"));
			address.setCountry(XMLUtils.getText(nodeAddress, "country"));
			address.setId(XMLUtils.getText(nodeAddress, "id"));
			address.setNeighborhood(XMLUtils.getText(nodeAddress, "neighbour"));
			address.setNumber(XMLUtils.getText(nodeAddress, "num"));
			address.setState(XMLUtils.getText(nodeAddress, "state"));
			address.setStreet(XMLUtils.getText(nodeAddress, "street"));
			address.setZip(XMLUtils.getText(nodeAddress, "zip"));

			sensor.setAddress(address);
			String idSensor = XMLUtils.getText(node, "id");
			sensor.setId(idSensor);
			sensor.setName(XMLUtils.getText(node, "name"));
			sensor.setTimestamp(XMLUtils.getText(node, "timestamp"));

			sensor.setLuminosity(parserLuminosity(idSensor));
			sensor.setAudio(parserAudio(idSensor));
			sensor.setTemperature(parserTemperature(idSensor));
			sensor.setHumidity(parserHumidity(idSensor));
			String[] geo = parserGPS(idSensor);
			sensor.setLatitude(geo[0]);
			sensor.setLongitude(geo[1]);
			sensor.setAtmPressure(parserAtmPressure(idSensor));

			sensors.add(sensor);
		}
		return sensors;
	}

	public static String parserLuminosity(String idSensor) throws IOException {
		String url = URL + "sensor/type/luminosity/get?id=" + idSensor;
		String xml = HttpHelper.doGet(url, charset);
		Element root = XMLUtils.getRoot(xml, charset);
		return XMLUtils.getText(root, "value");
	}

	public static String parserAudio(String idSensor) throws IOException {
		String url = URL + "sensor/type/audio/get?id=" + idSensor;
		String xml = HttpHelper.doGet(url, charset);
		Element root = XMLUtils.getRoot(xml, charset);
		return XMLUtils.getText(root, "value");
	}

	public static String parserTemperature(String idSensor) throws IOException {
		String url = URL + "sensor/type/temperature/get?id=" + idSensor;
		String xml = HttpHelper.doGet(url, charset);
		Element root = XMLUtils.getRoot(xml, charset);
		return XMLUtils.getText(root, "value");
	}

	public static String parserHumidity(String idSensor) throws IOException {
		String url = URL + "sensor/type/humidity/get?id=" + idSensor;
		String xml = HttpHelper.doGet(url, charset);
		Element root = XMLUtils.getRoot(xml, charset);
		return XMLUtils.getText(root, "value");
	}

	public static String[] parserGPS(String idSensor) throws IOException {
		String url = URL + "sensor/type/gps/get?id=" + idSensor;
		String xml = HttpHelper.doGet(url, charset);
		Element root = XMLUtils.getRoot(xml, charset);
		String[] geo = new String[2];
		geo[0] = XMLUtils.getText(root, "latitude");
		geo[1] = XMLUtils.getText(root, "longitude");
		return geo;
	}

	public static String parserAtmPressure(String idSensor) throws IOException {
		String url = URL + "sensor/type/atmPressure/get?id=" + idSensor;
		String xml = HttpHelper.doGet(url, charset);
		Element root = XMLUtils.getRoot(xml, charset);
		return XMLUtils.getText(root, "value");
	}

}
