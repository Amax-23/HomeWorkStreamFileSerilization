import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClientLog {

    public void log(int productNum, int amount) throws IOException {
        File file = new File("log.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(amount + "," + productNum + "\n");
            writer.flush();
        }
    }

    public void exportAsCSV(File txtFile) throws Exception {
        FileWriter writer = null;
        File logTxt = new File(String.valueOf(txtFile));
        Scanner scan = new Scanner(txtFile);
        File logCsv = new File(XMLReader.getLogFile());
        logTxt.createNewFile();
        writer = new FileWriter(logCsv);
        writer.append("productNum,amount\n");
        while (scan.hasNext()) {
            String csv = scan.nextLine().replace("|", ",");
            writer.append(csv);
            writer.append("\n");
            writer.flush();
        }
    }
}


