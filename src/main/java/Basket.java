import org.json.simple.JSONObject;

import java.io.*;
import java.util.Arrays;


public class Basket implements Serializable {
    private final int[] price;
    private final String[] product;
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
        try (BufferedWriter out = new BufferedWriter(new FileWriter(textFile))) {
            out.write(Arrays.toString(basket) + "\n");
            out.write(Arrays.toString(product) + "\n");
            out.write(Arrays.toString(price) + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveJson(File jsonFile) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("basket", Arrays.toString(basket));
        try (FileWriter file = new FileWriter(jsonFile)) {
            file.write(obj.toJSONString());
        }
    }

    public static Basket loadFromJsonFile(String jsonFile) throws IOException {
        File file = new File(String.valueOf(jsonFile));
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String[] number = br.readLine().replaceAll("\\D+", " ").split(" ");
                basket = new int[number.length - 1];
                for (int i = 0; i < basket.length; i++) {
                    basket[i] = Integer.parseInt(number[i + 1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Basket loadFromTxtFile(String textFile) {
        File file = new File(String.valueOf(textFile));
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String[] number = br.readLine().replaceAll("\\D+", " ").split(" ");
                basket = new int[number.length - 1];
                for (int i = 0; i < basket.length; i++) {
                    basket[i] = Integer.parseInt(number[i + 1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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