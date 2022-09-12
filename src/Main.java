import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File textBinFile = new File(("basket.bin"));
        Basket basketObjekt = new Basket(new int[]{14, 80, 50, 90, 300}, new String[]{"Хлеб", "Гречневая крупа", "Молоко", "Сливки", "Кофе"});
        if (Files.exists(Path.of(String.valueOf(textBinFile)))) {
            basketObjekt = Basket.loadFromBinFile(textBinFile);
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
                basketObjekt.saveBin(new File(String.valueOf(textBinFile)));
                scanner.close();
                break;
            }
            String[] inputString = input.split(" ");
            int productNum = Integer.parseInt(inputString[0]) - 1;
            int productAmount = Integer.parseInt(inputString[1]);
            basketObjekt.addToCart(productAmount, productNum);
        }
    }
}