import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
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

    public static void readXml(String xmlFile) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlFile));
        Node root = document.getDocumentElement();
        //System.out.println("Корневой элемент: " + root.getNodeName());
        System.out.println("Начало загрузки параметров из файла shop.xml:");
        read(root);
        //readXml2(xmlFile); // метод считывания по Филиппу Воронову
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
    // метод от Филиппа Воронова по чтению из XML файла, гораздо короче (не проверял)
    public static void readXml2(String xmlFile) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlFile));
        XPath xPath = XPathFactory.newInstance().newXPath();
        loadBoolen =  Boolean.parseBoolean(xPath
                .compile("/config/load/enabled")// указываем путь в XML до нужного значения
                .evaluate(document));// ответ будет: loadBoolen = "true"
        System.out.println("Значение loadBoolen = " + loadBoolen);
        loadFileName = xPath
                .compile("/config/load/fileName")// указываем путь в XML до нужного значения
                .evaluate(document);// ответ будет: loadFileName = "basket.json"
        System.out.println("Значение loadFileName = " + loadFileName);
        loadFormatFile = xPath
                .compile("/config/load/format")// указываем путь в XML до нужного значения
                .evaluate(document);// ответ будет: loadFormatFile = "json"
        System.out.println("Значение loadFormatFile = " + loadFormatFile);
        // и так далее берем по пути необходимые значения для наших переменных
        saveBoolen = Boolean.parseBoolean(xPath
                .compile("/config/save/enabled")// указываем путь в XML до нужного значения
                .evaluate(document));// ответ будет: saveBoolen = "true"
        System.out.println("Значение saveBoolen = " + saveBoolen);
        saveFileName = xPath
                .compile("/config/save/fileName")// указываем путь в XML до нужного значения
                .evaluate(document);// ответ будет: saveFileName = "basket.json"
        System.out.println("Значение saveFileName = " + saveFileName);
        saveFormat = xPath
                .compile("/config/save/format")// указываем путь в XML до нужного значения
                .evaluate(document);// ответ будет: saveFormat = "json"
        System.out.println("Значение saveFormat = " + saveFormat);
        logBoolen = Boolean.parseBoolean(xPath
                .compile("/config/log/enabled")// указываем путь в XML до нужного значения
                .evaluate(document));// ответ будет: logBoolen = "true"
        System.out.println("Значение logBoolen = " + logBoolen);
        logFile = xPath
                .compile("/config/log/fileName")// указываем путь в XML до нужного значения
                .evaluate(document);// ответ будет: logFile = "client.csv"
        System.out.println("Значение logFile = " + logFile);
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