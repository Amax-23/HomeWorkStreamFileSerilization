import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static String xmlFile = "shop.xml";
    public static String logTxt = "log.txt";

    public static void main(String[] args) throws Exception {
        XMLReader.readXml(xmlFile); // чтение XML по созданному мной методу
        //XMLReader.readXml2(xmlFile); // чтение XML по методу Филиппа Воронова
        ClientLog clientLog = new ClientLog();
        Basket basketObjekt = new Basket(new int[]{14, 80, 50, 90, 300}, new String[]{"Хлеб", "Гречневая крупа", "Молоко", "Сливки", "Кофе"});
        if (Files.exists(Path.of(XMLReader.getLoadFileName())) && XMLReader.getIsLoadBoolen() == true) {
            Basket.loadFromFile(XMLReader.getLoadFileName());
            basketObjekt.printCart();
            System.out.println("ПРОДОЛЖАЙТЕ:\n");
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            basketObjekt.printList();
            System.out.println("Выберите через пробел товар и количество или введите end для завершения: ");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                basketObjekt.printCart();
                if (XMLReader.getIsSaveBoolen() == true && XMLReader.getSaveFormat().equals("json")) {
                    basketObjekt.saveJson(new File(XMLReader.getSaveFileName()));
                }
                if (XMLReader.getIsSaveBoolen() == true && XMLReader.getSaveFormat().equals("txt")) {
                    basketObjekt.saveTxt(new File(XMLReader.getSaveFileName()));
                }
                if (XMLReader.getIsLogBoolen() == true) {
                    clientLog.exportAsCSV(new File(logTxt));
                }
                scanner.close();
                break;
            }
            String[] inputString = input.split(" ");
            int productNum = Integer.parseInt(inputString[0]) - 1;
            int productAmount = Integer.parseInt(inputString[1]);
            basketObjekt.addToCart(productAmount, productNum);
            clientLog.log(productAmount, productNum + 1);
        }
    }
}