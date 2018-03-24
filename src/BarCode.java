/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodereader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
 
/**
 *
 * @author Osama Nasr
 */
public class Barcode {
        private ArrayList<Data> tokens;
       
        public ArrayList<Data> getTokens() {
            return tokens;
        }

	public void setTokens(ArrayList<Data> tokens) {
            this.tokens = tokens;
	}

	Barcode(File input) throws FileNotFoundException{
            tokens = new ArrayList<Data>();
            loadFromFile(input);
            System.out.println("the data already in the list");
        }
       
        public void loadFromFile(File inputFile) throws FileNotFoundException{
            if(inputFile.canRead()){
                Scanner reader = new Scanner(inputFile);
                while(reader.hasNext()){
                    Data temp = new Data(Integer.parseInt(reader.nextLine()),reader.nextLine(),reader.nextLine(),reader.nextLine(),Integer.parseInt(reader.nextLine()),reader.nextLine());
                    tokens.add(temp);
                }
                System.out.println(1);
                reader.close();
            }
            else{
                System.out.println(0);
                tokens = null;
            }
        }
        public int FindWithGeneratedCode(int number){
            for(int i =0; i<tokens.size();i++){
                if(tokens.get(i).getGeneratedNumber() == number){
                    return i;
                }
            }
            return -1;
        }
        
        public void addToList(Data temp){
            tokens.add(temp);
    	}
}
