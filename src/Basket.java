import java.io.*;
import java.util.Arrays;

public class Basket {
    private int[] price;
    private String[] product;
    private static int[] basket;

    public Basket(int[] price, String[] product) {
        this.price = price;
        this.product = product;
        if (basket == null) {
            basket = new int[product.length];
        }
    }

    public void printList() {
        System.out.println("Список товаров в магазине:");
        for (int i = 0; i < product.length; i++) {
            System.out.println(i + 1 + ". " + product[i] + " по " + price[i] + " руб.");
        }
    }

    public void addToCart(int amount, int productNum) {
        basket[productNum] += amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина: ");
        int sumProduct = 0;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] > 0) {
                System.out.println(product[i] + " " + basket[i] + " шт. по " + price[i] +
                        " руб/шт." + " на " + (price[i] * basket[i]) + " руб. в сумме");
                sumProduct += (basket[i] * price[i]);
            }
        }
        System.out.println("Итого товаров в корзине на сумму: " + sumProduct + " руб.");
    }

    public void saveTxt(File textFile) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
            out.write(Arrays.toString(basket) + "\n");
            out.write(Arrays.toString(product) + "\n");
            out.write(Arrays.toString(price) + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadFromTxtFile() {
        File file = new File("basket.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader("basket.txt"))) {
                String line = br.readLine();
                String strEnd = line.replaceAll("\\D+", " ");
                String[] number = strEnd.split(" ");
                basket = new int[number.length - 1];
                for (int i = 0; i < basket.length; i++) {
                    basket[i] = Integer.parseInt(number[i + 1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Basket{" +
                "price=" + Arrays.toString(price) +
                ", product=" + Arrays.toString(product) +
                ", basket=" + Arrays.toString(basket) +
                '}';
    }
}
