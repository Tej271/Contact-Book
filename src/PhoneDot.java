import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PhoneDot {

	private JFrame frame;
	private JTextField textField_name;
	private JTextField textField_phone;
	private JTextField textField_sr_no;
	private JTable table;
	
	static HashMap<Integer, Contact> dir = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneDot window = new PhoneDot();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static TableModel toTableModel(Map<Integer, Contact> map) {
	     
	     DefaultTableModel model1 = new DefaultTableModel(new Object[] {"Sr.No.","Name","Phone No."}, 0);
	  
	     for(Map. Entry<Integer, Contact> entry : map.entrySet()) {
	    	    model1.addRow(new Object[]{entry.getKey(), entry.getValue().getName(), entry.getValue().getPhone()});
	     }
	  return model1;
	 }

	/**
	 * Create the application.
	 */
	public PhoneDot() {
		initialize();
		table_load();
	}
	
	void table_load() {
		try {
			table.setModel(toTableModel(dir));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(405, 38, 189, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel Contact = new JPanel();
		Contact.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Contact.setBounds(32, 104, 338, 175);
		frame.getContentPane().add(Contact);
		Contact.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(25, 39, 45, 13);
		Contact.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ph. No.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(25, 100, 53, 13);
		Contact.add(lblNewLabel_2);
		
		textField_name = new JTextField();
		textField_name.setBounds(107, 33, 191, 29);
		Contact.add(textField_name);
		textField_name.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(107, 87, 191, 29);
		Contact.add(textField_phone);
		
		JButton btnNewButton = new JButton("Add");
				btnNewButton.setBounds(32, 294, 96, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(152, 294, 96, 42);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_name.setText("");
				textField_phone.setText("");
				textField_name.requestFocus(); 
			}
		});
		btnNewButton_1_1.setBounds(270, 294, 96, 42);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JPanel Serach = new JPanel();
		Serach.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Serach.setBounds(32, 367, 338, 116);
		frame.getContentPane().add(Serach);
		Serach.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sr. No.");
		lblNewLabel_1_1.setBounds(29, 26, 59, 22);
		Serach.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField_sr_no = new JTextField();
		textField_sr_no.setBounds(98, 25, 191, 29);
		Serach.add(textField_sr_no);
		textField_sr_no.setColumns(10);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.setEnabled(false);
		btnNewButton_1_2.setBounds(207, 64, 82, 42);
		Serach.add(btnNewButton_1_2);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sr_no;
				Integer key;
				sr_no = textField_sr_no.getText();
				key = Integer.parseInt(sr_no);
				dir.remove(key);
				table_load();
				if(dir.size()>0) {
					btnNewButton_1_2.setEnabled(true);
				}else {
					btnNewButton_1_2.setEnabled(false);
				}
				
				textField_name.setText("");
				textField_phone.setText("");
				textField_name.requestFocus(); 
				textField_sr_no.setText("");
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(223, 126, 91, 39);
		btnUpdate.setEnabled(false);
		Contact.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,phone;
				
				name = textField_name.getText();
				phone = textField_phone.getText();
				String sr_no;
				Integer key;
				sr_no = textField_sr_no.getText();
				key = Integer.parseInt(sr_no);
								
				try {
					dir.put(key, new Contact(name,phone));
					table_load();
					textField_name.setText("");
					textField_phone.setText("");
					textField_name.requestFocus();
					JOptionPane.showMessageDialog(null,"Record Update!!!!!");
					btnNewButton.setEnabled(true);
					btnUpdate.setEnabled(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				textField_sr_no.setText("");

				
				
			}
		});
		
		JButton btnNewButton_1_2_1 = new JButton("Select");
		btnNewButton_1_2_1.setEnabled(false);
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				btnUpdate.setEnabled(true);
				String sr_no;
				Integer key;
				sr_no = textField_sr_no.getText();
				key = Integer.parseInt(sr_no);
				textField_name.setText(dir.get(key).getName());
				textField_phone.setText(dir.get(key).getPhone());
				if(dir.size()>0) {
					btnNewButton_1_2_1.setEnabled(true);
				}else {
					btnNewButton_1_2_1.setEnabled(false);
				}
				textField_sr_no.setText("");

			}
		});
		
		btnNewButton_1_2_1.setBounds(39, 64, 82, 42);
		Serach.add(btnNewButton_1_2_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name, number;

				name = textField_name.getText();
				number = textField_phone.getText();
				try {
					dir.put(dir.size()+1,new Contact(name,number));
					table_load();
					textField_name.setText("");
					textField_phone.setText("");
					textField_name.requestFocus();
					JOptionPane.showMessageDialog(null,"Record Addedddd!!!!!");
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				if(dir.size()>0) {
					btnNewButton_1_2_1.setEnabled(true);
					btnNewButton_1_2.setEnabled(true);
				}else {
					btnNewButton_1_2_1.setEnabled(false);
					btnNewButton_1_2.setEnabled(false);
				}
			}
		});

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(454, 103, 520, 380);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
