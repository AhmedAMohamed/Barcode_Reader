/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodereader;

/**
 *
 * @author Osama Nasr
 */
public class Data {
 
        private int generatedNumber;
        private String packageData;
        private String dataSent;
        private String recievedData;
        private int IDNumber;
        private String choice;
        public Data(int generatedNumber,String packageData,String dataSent,String recievedData,int iDNumber,String choice){
            this.generatedNumber = generatedNumber;
            this.packageData = packageData;
            this.dataSent = dataSent;
            this.recievedData = recievedData;
            this.IDNumber = iDNumber;
            this.choice = choice;

        }
        public int getGeneratedNumber() {
                return generatedNumber;
        }
        public void setGeneratedNumber(int generatedNumber) {
                this.generatedNumber = generatedNumber;
        }
        public String getPackageData() {
                return packageData;
        }
        public void setPackageData(String packageData) {
                this.packageData = packageData;
        }
        public String getDataSent() {
                return dataSent;
        }
        public void setDataSent(String dataSent) {
                this.dataSent = dataSent;
        }
        public String getRecievedData() {
                return recievedData;
        }
        public void setRecievedData(String recievedData) {
                this.recievedData = recievedData;
        }
        public int getIDNumber() {
                return IDNumber;
        }
        public void setIDNumber(int iDNumber) {
                this.IDNumber = iDNumber;
        }
        public String getChoice() {
                return choice;
        }
        public void setChoice(String choice) {
                this.choice = choice;
        }
}
