import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReader {

    private static boolean loadBoolen;
    private static String loadFileName;
    private static String loadFormatFile;

    private static boolean saveBoolen;
    private static String saveFileName;
    private static String saveFormat;

    private static boolean logBoolen;
    private static String logFile;
    public static int counter = 0;

    public static void readXml(String xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlFile));
        Node root = document.getDocumentElement();
        //System.out.println("Корневой элемент: " + root.getNodeName());
        System.out.println("Начало загрузки параметров из файла shop.xml:");
        read(root);
        System.out.println("Конец загрузки параметров из файла shop.xml:\n");
    }

    private static void read(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                //System.out.println("Текущий узел: " + currentNode.getNodeName());
                Element element = (Element) currentNode;
                NamedNodeMap map = element.getAttributes();
                if (currentNode.hasAttributes()) {
                    for (int a = 0; a < map.getLength(); a++) {
                        counter++;
                        if (counter == 1) {
                            loadBoolen = Boolean.parseBoolean(map.item(a).getNodeValue());
                            System.out.println("Значение loadBoolen = " + loadBoolen);
                        }
                        if (counter == 2) {
                            loadFileName = map.item(a).getNodeValue();
                            System.out.println("Значение loadFileName = " + loadFileName);
                        }
                        if (counter == 3) {
                            loadFormatFile = map.item(a).getNodeValue();
                            System.out.println("Значение loadFormatFile = " + loadFormatFile);
                        }
                        if (counter == 4) {
                            saveBoolen = Boolean.parseBoolean(map.item(a).getNodeValue());
                            System.out.println("Значение saveBoolen = " + saveBoolen);
                        }
                        if (counter == 5) {
                            saveFileName = map.item(a).getNodeValue();
                            System.out.println("Значение saveFileName = " + saveFileName);
                        }
                        if (counter == 6) {
                            saveFormat = map.item(a).getNodeValue();
                            System.out.println("Значение saveFormat = " + saveFormat);
                        }
                        if (counter == 7) {
                            logBoolen = Boolean.parseBoolean(map.item(a).getNodeValue());
                            System.out.println("Значение logBoolen = " + logBoolen);
                        }
                        if (counter == 8) {
                            logFile = map.item(a).getNodeValue();
                            System.out.println("Значение logFile = " + logFile);
                        }
                    }
                }
            }
            read(currentNode);
        }
    }

    public static boolean getIsLoadBoolen() {
        return loadBoolen;
    }

    public static String getLoadFileName() {
        return loadFileName;
    }

    public static String getLoadFormatFile() {
        return loadFormatFile;
    }

    public static boolean getIsSaveBoolen() {
        return saveBoolen;
    }

    public static String getSaveFileName() {
        return saveFileName;
    }

    public static String getSaveFormat() {
        return saveFormat;
    }

    public static boolean getIsLogBoolen() {
        return logBoolen;
    }

    public static String getLogFile() {
        return logFile;
    }
}