import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class BarCode {
    private ArrayList<Data> tokens;

    public ArrayList<Data> getTokens() {
        return this.tokens;
    }

    public void setTokens(ArrayList<Data> tokens) {
        this.tokens = tokens;
    }

    BarCode(File dataFile) throws FileNotFoundException {
        tokens = new ArrayList<>();
        readDataFromFile(dataFile);
    }

    public void readDataFromFile(File dataFile) throws FileNotFoundException {
        if (dataFile.canRead()) {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNext()) {
                Data data = new Data(Integer.parseInt(reader.nextLine()),
                        reader.nextLine(), reader.nextLine(), reader.nextLine()
                        ,Integer.parseInt(reader.nextLine()), reader.nextLine());
                tokens.add(data);
            }
            System.out.println("File read successfully");
            reader.close();
        } else {
            System.out.println("Failed to read file");
            tokens = null;
        }
    }

    public int FindWithGeneratedCode(int number) {
        for (int i = 0; i < this.tokens.size(); i++) {
            if (this.tokens.get(i).getGeneratedNumber() == number) {
                return i;
            }
        }
        return -1;
    }

    public void addToList(Data data) {
        this.tokens.add(data);
    }
}
