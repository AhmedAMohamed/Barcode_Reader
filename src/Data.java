
public class Data {
 
        private int generatedNumber;
        private String packageData;
        private String dataSent;
        private String receivedData;
        private int IDNumber;
        private String choice;

        public Data(int generatedNumber, String packageData, String dataSent, String receivedData, int IDNumber, String choice){
            this.generatedNumber = generatedNumber;
            this.packageData = packageData;
            this.dataSent = dataSent;
            this.receivedData = receivedData;
            this.IDNumber = IDNumber;
            this.choice = choice;
        }
       
        public int getGeneratedNumber() {
                return this.generatedNumber;
        }
        public void setGeneratedNumber(int generatedNumber) {
                this.generatedNumber = generatedNumber;
        }
        public String getPackageData() {
                return this.packageData;
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
        public String getReceivedData() {
                return receivedData;
        }
        public void setReceivedData(String receivedData) {
                this.receivedData = receivedData;
        }
        public int getIDNumber() {
                return this.IDNumber;
        }
        public void setIDNumber(int iDNumber) {
                this.IDNumber = iDNumber;
        }
        public String getChoice() {
                return this.choice;
        }
        public void setChoice(String choice) {
                this.choice = choice;
        }
}
