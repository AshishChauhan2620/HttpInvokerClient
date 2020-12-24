package com.example.electronicshowroomUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.example.pojo.Category;
import com.example.pojo.ProductDetails;
import com.example.pojo.SubCategory;

public class JframeUI {

	private JFrame frame;
	private JTable table;
	HttpClientInvoker client = new HttpClientInvoker();
//	RestClient client = new RestClient();
	JComboBox comboBoxforCategoryId;
	JComboBox comboBoxforSubCategoryId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeUI window = new JframeUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JframeUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 778, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 731, 240);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// Add Category Window
		JButton btnAddCategory = new JButton("Add Category");
		final JTextField tf = new JTextField();
		JTextField textvalueCategory = new JTextField();
		tf.setBounds(50, 50, 150, 20);
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Add Category");// creating instance of JFrame
				JButton btnSubmitCategory = new JButton("Submit");// creating instance of JButton

				JLabel l2 = new JLabel("Category Name: ");
				l2.setBounds(30, 90, 100, 30);

				textvalueCategory.setBounds(130, 90, 100, 30);
				f.getContentPane().add(textvalueCategory);
				f.getContentPane().add(l2);
				btnSubmitCategory.setBounds(130, 140, 100, 30);// x axis, y axis, width, height
				f.getContentPane().add(btnSubmitCategory);
				f.setSize(400, 500);
				f.getContentPane().setLayout(null);// using no layout managers
				f.setVisible(true);
				btnSubmitCategory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Category category = new Category();
						category.setCategoryName(textvalueCategory.getText());
						client.addcategory(category);
					}
				});

			}

		});
		btnAddCategory.setBounds(29, 282, 152, 36);
		frame.getContentPane().add(btnAddCategory);

		// Add Sub Category Window
		JFrame subCategoryFrame = new JFrame("Sub Category");// creating instance of JFrame
		JButton btnSubmitSubCategory = new JButton("Submit");// creating instance of JButton

		JButton btnAddSubCategory;
		btnAddSubCategory = new JButton("Add Sub-Category");
		btnAddSubCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				JLabel l1 = new JLabel("Selected");
//				l1.setBounds(260, 50, 100, 40);
				JLabel l2 = new JLabel("Sub-Category Name: ");
				l2.setBounds(30, 90, 120, 30);
				JLabel l3 = new JLabel("Category ID: ");
				l3.setBounds(30, 50, 120, 50);
				JTextField textvalueSubCategory;
				subCategoryFrame.getContentPane().setLayout(new FlowLayout());
				List<Category> category = client.viewCategory();
				List<String> list = category.stream().map(Category::getCategoryName).collect(Collectors.toList());
				comboBoxforCategoryId = new JComboBox();
				ListIterator<String> listIterator = list.listIterator();
				while (listIterator.hasNext()) {
					comboBoxforCategoryId.addItem(listIterator.next());
				}

				textvalueSubCategory = new JTextField();
				textvalueSubCategory.setBounds(150, 90, 120, 30);
				comboBoxforCategoryId.setBounds(150, 50, 100, 30);
				subCategoryFrame.getContentPane().add(comboBoxforCategoryId);
				subCategoryFrame.getContentPane().add(textvalueSubCategory);
//				subCategoryFrame.getContentPane().add(l1);
				subCategoryFrame.getContentPane().add(l2);
				subCategoryFrame.getContentPane().add(l3);
				btnSubmitSubCategory.setBounds(130, 140, 100, 30);// x axis, y axis, width, height

				subCategoryFrame.getContentPane().add(btnSubmitSubCategory);// adding button in JFrame

				subCategoryFrame.setSize(400, 500);// 400 width and 500 height
				subCategoryFrame.getContentPane().setLayout(null);// using no layout managers
				subCategoryFrame.setVisible(true);// making the frame visible
//				comboBoxforCategoryId.addItemListener(new ItemListener() {
//
//					@Override
//					public void itemStateChanged(ItemEvent e) {
//						if (e.getSource() == comboBoxforCategoryId) {
//
//							l1.setText(comboBoxforCategoryId.getSelectedItem() + " selected");
//						}
//					}
//				});
				btnSubmitSubCategory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SubCategory subCategory = new SubCategory();
						Category category = new Category();
						category.setCategoryName(comboBoxforCategoryId.getSelectedItem().toString());
						subCategory.setSubCategoryName((textvalueSubCategory.getText()));
						subCategory.setCategoryId(category);
						client.addSubCategory(subCategory);
					}
				});

			}

		});
		btnAddSubCategory.setBounds(213, 282, 145, 36);
		frame.getContentPane().add(btnAddSubCategory);

		// Add Product Details Window
		JFrame productDetailsFrame = new JFrame("Product Details");// creating instance of JFrame
		JButton btnSubmitProductDetails = new JButton("Submit");// creating instance of JButton

		JButton btnAddProductDetails;
		btnAddProductDetails = new JButton("AddProductDetails");
		btnAddProductDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JLabel l7, l8, l9, l10, l11;
				l7 = new JLabel("Sub-Category ID: ");
				l7.setBounds(30, 50, 120, 30);
				l8 = new JLabel("Sub-Category Name: ");
				l8.setBounds(30, 90, 120, 30);
				l9 = new JLabel("Brand: ");
				l9.setBounds(30, 130, 100, 30);
				l10 = new JLabel("Price: ");
				l10.setBounds(30, 170, 100, 30);
				l11 = new JLabel("Year Of MFG: ");
				l11.setBounds(30, 210, 100, 30);

				List<Category> category = client.viewCategory();
				List<String> listcategory = category.stream().map(Category::getCategoryName)
						.collect(Collectors.toList());
				comboBoxforCategoryId = new JComboBox();
				ListIterator<String> listIterator1 = listcategory.listIterator();
				while (listIterator1.hasNext()) {
					comboBoxforCategoryId.addItem(listIterator1.next());
				}
				List<SubCategory> subCategory = client.viewSubCategory();
				List<String> listSubCategory = subCategory.stream().map(SubCategory::getSubCategoryName)
						.collect(Collectors.toList());
				comboBoxforSubCategoryId = new JComboBox();
				ListIterator<String> listIterator2 = listSubCategory.listIterator();
				while (listIterator2.hasNext()) {
					comboBoxforSubCategoryId.addItem(listIterator2.next());
				}

				JTextField brandField, priceField, yearOfMfgField;
				brandField = new JTextField();
				brandField.setBounds(150, 130, 120, 30);
				priceField = new JTextField();
				priceField.setBounds(150, 170, 120, 30);
				yearOfMfgField = new JTextField();
				yearOfMfgField.setBounds(150, 210, 120, 30);
				comboBoxforCategoryId.setBounds(150, 50, 100, 30);
				comboBoxforSubCategoryId.setBounds(150, 90, 100, 30);
				productDetailsFrame.add(brandField);
				productDetailsFrame.add(priceField);
				productDetailsFrame.add(yearOfMfgField);
				productDetailsFrame.getContentPane().add(comboBoxforCategoryId);
				productDetailsFrame.getContentPane().add(comboBoxforSubCategoryId);
				productDetailsFrame.add(l7);
				productDetailsFrame.add(l8);
				productDetailsFrame.add(l9);
				productDetailsFrame.add(l10);
				productDetailsFrame.add(l11);
				btnSubmitProductDetails.setBounds(130, 290, 100, 30);

				productDetailsFrame.add(btnSubmitProductDetails);

				productDetailsFrame.setSize(400, 500);
				productDetailsFrame.setLayout(null);
				productDetailsFrame.setVisible(true);

//				comboBoxforCategoryId.addItemListener(new ItemListener() {
//
//					@Override
//					public void itemStateChanged(ItemEvent e) {
//						if (e.getSource() == comboBoxforCategoryId) {
//
//							l7.setText(comboBoxforCategoryId.getSelectedItem() + " selected");
//						}
//					}
//				});
//				comboBoxforSubCategoryId.addItemListener(new ItemListener() {
//
//					@Override
//					public void itemStateChanged(ItemEvent e) {
//						if (e.getSource() == comboBoxforSubCategoryId) {
//
//							l7.setText(comboBoxforSubCategoryId.getSelectedItem() + " selected");
//						}
//					}
//				});

				btnSubmitProductDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Category category = new Category();
						SubCategory subCategory = new SubCategory();
						category.setCategoryName(comboBoxforCategoryId.getSelectedItem().toString());
//						subCategory.setSubCategoryName(comboBoxforSubCategoryId.getSelectedItem().toString());
						ProductDetails productDetails = new ProductDetails();
						productDetails.setBrand(productDetails.getBrand());
						productDetails.setPrice(productDetails.getPrice());
						productDetails.setYearOfManufacturing(productDetails.getYearOfManufacturing());

						client.addProductDetails(productDetails);
					}
				});

			}

		});
		btnAddProductDetails.setBounds(401, 282, 137, 36);
		frame.getContentPane().add(btnAddProductDetails);

		// upload data from xlsx file
		JButton btnNewButton_3 = new JButton("AddCatgeoryFromFile");
//		FileChooserUtility fileChooserDemo;
//		btnNewButton_3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				/*
//				 * JFileChooser jfc = new
//				 * JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//				 * 
//				 * int returnValue = jfc.showOpenDialog(null);
//				 * 
//				 * if (returnValue == JFileChooser.APPROVE_OPTION) { File selectedFile =
//				 * jfc.getSelectedFile(); System.out.println(selectedFile.getAbsolutePath()); }
//				 */
//				SwingUtilities.invokeLater(new Runnable() {
//					public void run() {
//						// Turn off metal's use of bold fonts
//						UIManager.put("swing.boldMetal", Boolean.FALSE);
//						FileChooserUtility.createAndShowGUI();
//					}
//				});
//			}
//		});
		btnNewButton_3.setBounds(575, 282, 137, 35);
		frame.getContentPane().add(btnNewButton_3);
		LoadData();

	}

	private void LoadData() {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("CategoryId");
		dtm.addColumn("CategoryName");
		for (Category category : client.viewCategory()) {
			dtm.addRow(new Object[] { category.getCategoryId(), category.getCategoryName() });
		}
		this.table.setModel(dtm);
	}
}