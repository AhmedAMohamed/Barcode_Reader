package mainwindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
 
 
public class BarCode {
        public static int genetratedCode=0;
        private ArrayList<Data> tokens;
       //return items saved in array list
        public ArrayList<Data> getTokens() {
			return tokens;
		}
// set the array list
		public void setTokens(ArrayList<Data> tokens) {
			this.tokens = tokens;
		}
           
		BarCode(File input) throws FileNotFoundException{
                tokens = new ArrayList<Data>();
                loadFromFile(input);
                System.out.println("the data already in the list");
        }
     //load data from file
        public void loadFromFile(File inputFile) throws FileNotFoundException{
                if(inputFile.canRead()){
                        Scanner reader = new Scanner(inputFile);
                        while(reader.hasNext()){
                                Data temp = new Data();
                               genetratedCode=Integer.parseInt(reader.nextLine());
                                temp.setGeneratedNumber(genetratedCode);
                                temp.setPackageData(reader.nextLine());
                                temp.setDataSent(reader.nextLine());
                                temp.setRecievedData(reader.nextLine());
                                temp.setIDNumber(Integer.parseInt(reader.nextLine()));
                                temp.setChoice(reader.nextLine());
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
        //Search by ID 
        public int FindWithID(int number){
                int i = 0;
                while(i < tokens.size() ){
                        if(tokens.get(i).getIDNumber()== number){
                               return i;
                        }
                        i++;
                }
                return -1;
        }
        // add a new package info in the list
        public void addToList(Data temp){
    		tokens.add(temp);
    	}
        
}