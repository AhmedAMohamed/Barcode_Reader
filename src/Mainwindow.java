import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class Mainwindow extends JFrame {
	private JTextField barCodeText;
	private int newrakam;
	private BufferedImage image;
	private JFrame parent;
	private JPanel panel;
	private JPanel panel_3;

	/*
	 * User input fields
	 */
	private JTextField tfPackage;
	private JTextField tfDataSent;
	private JTextField tfDataReceived;
	private JTextField tfIDNumber;
	
	private JLabel label;
	private JRadioButton rdbtnStepA;
	private JRadioButton rdbtnStepB;
	private JRadioButton rdbtnStepC;
	private JLabel view_package;
	private JLabel view_datasent;
	private JLabel view_recieved;
	private JLabel view_id;
    private JLabel lblNewLabel;
    private JLabel lblDataSent;
    private JLabel lblDataReceived;
    private JLabel lblIdNumber;
    private JLabel lblPackage;

	/*
	 * Thread waiting for input from barcode reader
	 */
	private Thread th;

	private BarCode barCode;
	private File storedData;
	private boolean fileNotGenerated;

	public static void main(String[] args) throws FileNotFoundException {
		new Mainwindow();
	}

	Mainwindow() throws FileNotFoundException {
		storedData = null;
		setSize(532, 462);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		parent = this;

		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 265, 424);
		getContentPane().add(panel_3);
		panel_3.setVisible(false);

		tfIDNumber = new JTextField();
		tfIDNumber.setBounds(92, 173, 163, 20);
		tfIDNumber.setColumns(10);

		tfDataReceived = new JTextField();
		tfDataReceived.setBounds(92, 142, 163, 20);
		tfDataReceived.setColumns(10);

        tfDataSent = new JTextField();
        tfDataSent.setBounds(92, 111, 163, 20);
        tfDataSent.setColumns(10);

		tfPackage = new JTextField();
		tfPackage.setBounds(92, 80, 163, 20);
		tfPackage.setColumns(10);


		panel_3.setLayout(null);
		panel_3.add(tfIDNumber);
		panel_3.add(tfDataReceived);
		panel_3.add(tfPackage);
		panel_3.add(tfDataSent);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
            panel_3.setVisible(false);
            panel.setVisible(true);
            label.setIcon(null);
            tfIDNumber.setText("");
            tfDataReceived.setText("");
            tfPackage.setText("");
            tfDataSent.setText("");
            rdbtnStepA.setSelected(false);
            rdbtnStepB.setSelected(false);
            rdbtnStepC.setSelected(false);
            view_datasent.setText("");
            view_id.setText("");
            view_package.setText("");
            view_recieved.setText("");
        });
		btnBack.setBounds(75, 25, 89, 23);
		panel_3.add(btnBack);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> {
            add();
            search(newrakam);
            panel.setVisible(true);
            panel_3.setVisible(false);
            label.setIcon(null);
            tfIDNumber.setText("");
            tfDataReceived.setText("");
            tfPackage.setText("");
            tfDataSent.setText("");
            rdbtnStepA.setSelected(false);
            rdbtnStepB.setSelected(false);
            rdbtnStepC.setSelected(false);
            view_datasent.setText("");
            view_id.setText("");
            view_package.setText("");
            view_recieved.setText("");
        });
		btnSubmit.setBounds(75, 345, 89, 23);
		panel_3.add(btnSubmit);

		ButtonGroup group = new ButtonGroup();
		
		rdbtnStepA = new JRadioButton("Step A");
		rdbtnStepA.setBounds(55, 225, 109, 23);
		panel_3.add(rdbtnStepA);
		
		rdbtnStepB = new JRadioButton("Step B");
		rdbtnStepB.setBounds(55, 247, 109, 23);
		panel_3.add(rdbtnStepB);

		rdbtnStepC = new JRadioButton("Step C");
		rdbtnStepC.setBounds(55, 273, 109, 23);
		panel_3.add(rdbtnStepC);

		group.add(rdbtnStepA);
		group.add(rdbtnStepB);
		group.add(rdbtnStepC);
		
		lblNewLabel = new JLabel("Package");
		lblNewLabel.setBounds(10, 83, 72, 14);
		panel_3.add(lblNewLabel);

		lblDataSent = new JLabel("Data sent");
		lblDataSent.setBounds(10, 114, 72, 14);
		panel_3.add(lblDataSent);

		lblDataReceived = new JLabel("Data received");
		lblDataReceived.setBounds(10, 145, 72, 14);
		panel_3.add(lblDataReceived);

		lblIdNumber = new JLabel("ID Number");
		lblIdNumber.setBounds(10, 176, 72, 14);
		panel_3.add(lblIdNumber);
		panel = new JPanel();
		panel.setBounds(0, 0, 265, 424);
		getContentPane().add(panel);
		panel.setLayout(null);

		barCode = null;
		// //////////////////////////////////////////////////////////////
		storedData = new File("data.txt");
		if(!storedData.exists())
			try {
				storedData.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		barCode = new BarCode(storedData);
		if (barCode == null) {
			fileNotGenerated = true;
		}
		// /////////////////////////////////////////////////////////////
		JButton btnNewBarcode = new JButton("New Barcode");
		btnNewBarcode.addActionListener(e -> {
            panel.setVisible(false);
            panel_3.setVisible(true);
        });
		btnNewBarcode.setBounds(42, 42, 177, 23);
		panel.add(btnNewBarcode);
		barCodeText = new JTextField();
		barCodeText.setBounds(42, 184, 177, 20);
		panel.add(barCodeText);
		barCodeText.setColumns(10);

		JButton searchButton = new JButton("Enter Barcode");
		searchButton.addActionListener(new ActionListener() {
			String valueOfBarCodeText = null;

			public void actionPerformed(ActionEvent e) {
				valueOfBarCodeText = barCodeText.getText();
				if (valueOfBarCodeText != null) {
					try {
						newrakam = Integer.parseInt(valueOfBarCodeText);
						search(newrakam);
						barCodeText.setText("");
					} catch (NumberFormatException nfex) {
					    if(nfex.getMessage().contains("\"\"")){
                            JOptionPane.showMessageDialog(panel, "Please input a number", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(panel, "Invalid format, input was not a number", "Error", JOptionPane.ERROR_MESSAGE);
                        }
						barCodeText.setText("");
					}

				}
			}
		});

		searchButton.setBounds(42, 215, 177, 23);
		panel.add(searchButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> {
            parent.dispose();
            System.exit(0);
        });
		btnExit.setBounds(42, 328, 177, 23);
		panel.add(btnExit);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(264, 0, 252, 424);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		// this thread waits for input
		th = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                // must check if not in adding case or program is not in
                // error case
                newrakam = sc.nextInt();
                search(newrakam);
            }
        });
		th.start();

		label = new JLabel();
		label.setBounds(52, 47, 150, 80);
		panel_1.add(label);
		
		lblPackage = new JLabel("Package");
		lblPackage.setBounds(10, 193, 60, 14);
		panel_1.add(lblPackage);
		
		lblDataSent = new JLabel("Data sent");
		lblDataSent.setBounds(10, 218, 72, 14);
		panel_1.add(lblDataSent);
		
		lblDataReceived = new JLabel("Data received");
		lblDataReceived.setBounds(10, 243, 99, 14);
		panel_1.add(lblDataReceived);
		
		lblIdNumber = new JLabel("ID Number");
		lblIdNumber.setBounds(10, 268, 72, 14);
		panel_1.add(lblIdNumber);
		
		view_package = new JLabel("");
		view_package.setBounds(114, 193, 128, 14);
		panel_1.add(view_package);
		
		view_datasent = new JLabel("");
		view_datasent.setBounds(114, 218, 128, 14);
		panel_1.add(view_datasent);
		
		view_recieved = new JLabel("");
		view_recieved.setBounds(114, 243, 128, 14);
		panel_1.add(view_recieved);
		
		view_id = new JLabel("");
		view_id.setBounds(114, 268, 128, 14);
		panel_1.add(view_id);
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> save()));
		setVisible(true);
	}

	/**
	 * This function views the barcode image for a given barcode.
	 * 
	 * @param barcode
	 */
	void make_bar_code(int barcode) {

		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new Code128Writer().encode(String.valueOf(barcode),
					BarcodeFormat.CODE_128, 150, 80, null);
			image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		label.setIcon(new ImageIcon(image));
	}

	/**
	 * This function search for barcode in files If found an element view it
	 * else open a window to register new element
	 * 
	 * @param barcode
	 * @throws FileNotFoundException
	 */
	private void search(int barcode) {
		boolean found = false;
		int result = barCode.FindWithGeneratedCode(barcode);
		if (result == -1) {
			found = false;
		} else {
			found = true;
		}
		if (found) {
			view(result);
		} else {
			view_datasent.setText("");
			view_id.setText("");
			view_package.setText("");
			view_recieved.setText("");
			label.setIcon(null);
			make_bar_code(barcode);
			panel.setVisible(false);
			panel_3.setVisible(true);
		}
	}

	private void add() {
		System.out.println(newrakam);
		Data data = new Data(newrakam, tfPackage.getText(), tfDataSent.getText(),
				tfDataReceived.getText(), Integer.parseInt(tfIDNumber.getText()), getSelectedButton());
		barCode.addToList(data);
	}

	private String getSelectedButton() {
		String value = null;
		if (rdbtnStepA.isSelected()) {
			value = "Step A";
		} else if (rdbtnStepB.isSelected()) {
			value = "Step B";
		} else if (rdbtnStepC.isSelected()) {
			value = "Step C";
		}
		return value;
	}
	
	void save(){
		ArrayList<Data> allData = barCode.getTokens();
		try {
			PrintWriter writer = new PrintWriter(storedData);
			for(int i = 0; i < allData.size(); i++){
				writer.println(allData.get(i).getGeneratedNumber());
				writer.println(allData.get(i).getPackageData());
				writer.println(allData.get(i).getDataSent());
				writer.println(allData.get(i).getReceivedData());
				writer.println(allData.get(i).getIDNumber());
				writer.println(allData.get(i).getChoice());
			}
			writer.flush();writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * This function initialzie the view panel with the element data of the
	 * given barcode.
	 * 
	 * @param barcode
	 */
	private void view(int res) {
		make_bar_code(barCode.getTokens().get(res).getGeneratedNumber());
		view_package.setText(barCode.getTokens().get(res).getPackageData());
		view_id.setText(String.valueOf(barCode.getTokens().get(res).getIDNumber()));
		view_datasent.setText(barCode.getTokens().get(res).getDataSent());
		view_recieved.setText(barCode.getTokens().get(res).getReceivedData());
	}
}
