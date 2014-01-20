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

	private static final String URL = "http://www.decom.ufop.br/imobilis/johnnatan/sensors.xml";
	private static final String charset = "UTF-8";

	public static List<SensoresItem> getSensors() throws IOException {
		String url = URL;
		String xml;
		xml = HttpHelper.doGet(url, charset);
		List<SensoresItem> sensores = parserXML(xml);
		return sensores;
	}

	private static List<SensoresItem> parserXML(String xml) {
		Element root = XMLUtils.getRoot(xml, charset);
		List<SensoresItem> sensores = new ArrayList<SensoresItem>();
		List<Node> nodeSensores = XMLUtils.getChildren(root, "sensor");

		for (Node node : nodeSensores) {
			SensoresItem sensor = new SensoresItem();
			Address address = new Address();

			sensor.setHumidity(XMLUtils.getText(node, "humid"));
			sensor.setLuminosity(XMLUtils.getText(node, "light"));
			sensor.setLatitude(XMLUtils.getText(node, "latitude"));
			sensor.setLongitude(XMLUtils.getText(node, "longitude"));
			sensor.setTemperature(XMLUtils.getText(node, "temperature"));
			sensor.setInformation(XMLUtils.getText(node, "info"));

			Node nodeAddress = XMLUtils.getChild(node, "address");

			address.setStreet(XMLUtils.getText(nodeAddress, "street"));
			address.setNumber(XMLUtils.getText(nodeAddress, "number"));
			address.setNeighborhood(XMLUtils.getText(nodeAddress,
					"neighborhood"));
			address.setCity(XMLUtils.getText(nodeAddress, "city"));
			address.setState(XMLUtils.getText(nodeAddress, "state"));
			address.setCountry(XMLUtils.getText(nodeAddress, "country"));

			sensor.setAddress(address);

			sensores.add(sensor);
		}
		return sensores;
	}

}
