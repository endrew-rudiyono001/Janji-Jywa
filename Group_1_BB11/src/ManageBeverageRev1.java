import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class ManageBeverageRev1 extends JInternalFrame implements ActionListener, MouseListener{

//	JInternalFrame inFrame = new JInternalFrame();
	
	JPanel headerPanel = new JPanel(), 
			contentPanel = new JPanel(new GridLayout(2,0, 15, 15)),
			footerPanel = new JPanel(new GridLayout(0,2, 15,15));
	
	JPanel tablePanel = new JPanel(new BorderLayout()),
			formPanel = new JPanel(new GridLayout(0,2, 5,5));
	
	JPanel insertForm = new JPanel(new GridLayout(0,2,10,15)),
			modifyForm = new JPanel(new GridLayout(0,2,10,15));
	
	JPanel insertFooter = new JPanel(new GridLayout(2,0, 10,15)),
			modifyFooter = new JPanel(new GridLayout(2,0,10,15));
	
	JPanel modifyGroupButton = new JPanel(new GridLayout(0,2,10,15)),
			addGroupPanel = new JPanel(new GridLayout(0,3,10,15));
	
	JLabel  newIdLabel = new JLabel("New Beverage ID"),
			newNameLabel = new JLabel("New Beverage Name"),
			newTypeLabel = new JLabel("New Beverage Type"),
			newPriceLabel = new JLabel("New Beverage Price"),
			newStockLabel = new JLabel("New Beverage Stock");
	
	JLabel  headerLabel = new JLabel("Manage Beverage"),
			idLabel = new JLabel("Beverage ID"),
			nameLabel = new JLabel("Beverage Name"),
			typeLabel = new JLabel("Beverage Type"),
			priceLabel = new JLabel("Beverage Price"),
			stockLabel = new JLabel("Beverage Stock"),
			addStockLabel = new JLabel("Add Stock");
	
	JButton insertBtn = new JButton("Insert Beverage"),
			resetBtn = new JButton("Reset"),
			updateBtn = new JButton("Update Beverage"),
			deleteBtn = new JButton("Delete Beverage"),
			addStockBtn = new JButton("Add Stock");
	
	JTextField newIdField = new JTextField(),
			newNameField = new JTextField(),
			newPriceField = new JTextField();
	
	JTextField idField = new JTextField(),
			nameField = new JTextField(),
			typeField = new JTextField(),
			priceField = new JTextField(),
			stockField = new JTextField();
	
	JPanel  idLabelPanel = new JPanel(new BorderLayout()),
			idFieldPanel = new JPanel(new BorderLayout()),
			nameLabelPanel = new JPanel(new BorderLayout()),
			nameFieldPanel = new JPanel(new BorderLayout()),
			typeLabelPanel = new JPanel(new BorderLayout()),
			typeFieldPanel = new JPanel(new BorderLayout()),
			priceLabelPanel = new JPanel(new BorderLayout()),
			priceFieldPanel = new JPanel(new BorderLayout()),
			stockLabelPanel = new JPanel(new BorderLayout()),
			stockFieldPanel = new JPanel(new BorderLayout()),
			updateButtonPanel = new JPanel(new BorderLayout(20,15)),
			deleteButtonPanel = new JPanel(new BorderLayout(20,15));
	
	JPanel  newIdLabelPanel = new JPanel(new BorderLayout()),
			newIdFieldPanel = new JPanel(new BorderLayout()),
			newNameLabelPanel = new JPanel(new BorderLayout()),
			newNameFieldPanel = new JPanel(new BorderLayout()),
			newTypeLabelPanel = new JPanel(new BorderLayout()),
			newTypeFieldPanel = new JPanel(new BorderLayout()),
			newPriceLabelPanel = new JPanel(new BorderLayout()),
			newPriceFieldPanel = new JPanel(new BorderLayout()),
			newStockLabelPanel = new JPanel(new BorderLayout()),
			newStockFieldPanel = new JPanel(new BorderLayout()),
			insertButtonPanel = new JPanel(new BorderLayout(20,15)),
			resetButtonPanel = new JPanel(new BorderLayout(20,15));
	
	String column[] = {"Beverage ID" , "Beverage Name", "Beverage Type" , "Beverage Price" ,"Beverage Stock"};
	DefaultTableModel model = new DefaultTableModel(column , 0);
	JTable beverageTable = new JTable(model);
	JScrollPane scrollPane = new JScrollPane(beverageTable);
	
	String [] type = {"Boba" , "Coffee" , "Tea" , "Smoothies"};
	JComboBox<String> typeCbx = new JComboBox<String>(type);
	JComboBox<String> newTypeCbx = new JComboBox<String>(type);
	
	SpinnerNumberModel numberValue1 = new SpinnerNumberModel(0, 0, 1000000, 1),
			numberValue2  = new SpinnerNumberModel(0, 0, 1000000, 1);
	
	JSpinner newStockSpin = new JSpinner(numberValue1),
			addStockSpin = new JSpinner(numberValue2);
	
	JPanel  addStockLabelPanel = new JPanel(new BorderLayout()),
			addStockSpinPanel = new JPanel(new BorderLayout()),
			addStockButtonPanel = new JPanel(new BorderLayout(20,15));
	
	
	DatabaseConnection connection = new DatabaseConnection();
	
	public void frame() {
		setLayout(new BorderLayout());
//		inFrame.setVisible(true);
		
		headerPanel.add(headerLabel);
		tablePanel.add(scrollPane);
				
		beverageTable.setFillsViewportHeight(true);
		
		contentPanel.add(tablePanel);
		
		idField.setEditable(false);
		stockField.setEditable(false);
		
		beverageTable.addMouseListener(this);
		
		newIdLabelPanel.add(newIdLabel);
		newIdFieldPanel.add(newIdField);
		newNameLabelPanel.add(newNameLabel);
		newNameFieldPanel.add(newNameField);
		newTypeLabelPanel.add(newTypeLabel);
		newTypeFieldPanel.add(newTypeCbx);
		newPriceLabelPanel.add(newPriceLabel);
		newPriceFieldPanel.add(newPriceField);
		newStockLabelPanel.add(newStockLabel);
		newStockFieldPanel.add(newStockSpin);
		insertButtonPanel.add(insertBtn);
		resetButtonPanel.add(resetBtn);
		
		
		
	insertForm.add(newIdLabelPanel);
	insertForm.add(newIdFieldPanel);
	insertForm.add(newNameLabelPanel);
	insertForm.add(newNameFieldPanel);
	insertForm.add(newTypeLabelPanel);
	insertForm.add(newTypeFieldPanel);
	insertForm.add(newPriceLabelPanel);
	insertForm.add(newPriceFieldPanel);
	insertForm.add(newStockLabelPanel);
	insertForm.add(newStockFieldPanel);

	insertBtn.addActionListener(this);
	updateBtn.addActionListener(this);
	addStockBtn.addActionListener(this);
	deleteBtn.addActionListener(this);
	resetBtn.addActionListener(this);
	
	insertFooter.add(insertButtonPanel);
	insertFooter.add(resetButtonPanel);

	formPanel.add(insertForm);

		idLabelPanel.add(idLabel);
		idFieldPanel.add(idField);
		nameLabelPanel.add(nameLabel);
		nameFieldPanel.add(nameField);
		typeLabelPanel.add(typeLabel);
		typeFieldPanel.add(typeCbx);
		priceLabelPanel.add(priceLabel);
		priceFieldPanel.add(priceField);
		stockLabelPanel.add(stockLabel);
		stockFieldPanel.add(stockField);
		updateButtonPanel.add(updateBtn);
		deleteButtonPanel.add(deleteBtn);
		addStockLabelPanel.add(addStockLabel);
		addStockSpinPanel.add(addStockSpin);
		addStockButtonPanel.add(addStockBtn);
		
	modifyForm.add(idLabelPanel);
	modifyForm.add(idFieldPanel);
	modifyForm.add(nameLabelPanel);
	modifyForm.add(nameFieldPanel);
	modifyForm.add(typeLabelPanel);
	modifyForm.add(typeFieldPanel);
	modifyForm.add(priceLabelPanel);
	modifyForm.add(priceFieldPanel);
	modifyForm.add(stockLabelPanel);
	modifyForm.add(stockFieldPanel);
	
	modifyGroupButton.add(updateButtonPanel);
	modifyGroupButton.add(deleteButtonPanel);
	
	newIdField.setEditable(false);


	
	addGroupPanel.add(addStockLabelPanel);
	addGroupPanel.add(addStockSpinPanel);
	addGroupPanel.add(addStockButtonPanel);
	modifyFooter.add(modifyGroupButton);
	modifyFooter.add(addGroupPanel);
	formPanel.add(modifyForm);
	contentPanel.add(tablePanel);
	contentPanel.add(formPanel);
	footerPanel.add(insertFooter);
	footerPanel.add(modifyFooter);
		
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
//		add(inFrame);
	
	}
	
	
	public ManageBeverageRev1() {
		generateId();
		frame();
		refreshTable();
		setSize(800, 600);
		setLocation(0, 0);
//		setTitle("Janji Jywa");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void refreshTable() {
		model.setRowCount(0);
		String sql = "SELECT * FROM beverages";
		ResultSet result = connection.executeQuery(sql);
		try {

			while(result.next()) {
			Object[] row = new Object[] {
				result.getString("BeverageID"),
				result.getString("BeverageName"),
				result.getString("BeverageType"),
				result.getInt("BeveragePrice"),
				result.getInt("BeverageStock")
			};
			model.addRow(row);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateId() {
		String sql = "SELECT CAST(RIGHT(MAX(BeverageID), 3) AS INTEGER) AS 'totalBeverages' FROM beverages";
		ResultSet result = connection.executeQuery(sql);
		String newIdValue = new String();
		try {
		if (result.next()) {
			int len = result.getInt("totalBeverages");
			newIdValue += "BE";
			newIdValue += String.format("%03d", (len+1));
			newIdField.setText(newIdValue);
			len++;
			refreshTable();
		}
		
		} catch (Exception e2) {

		}
	}
	
	public static void main(String[] args) {
		new ManageBeverageRev1();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String name = nameField.getText();
		String type = typeCbx.getSelectedItem().toString();
		String priceStr = priceField.getText();
		String stockStr = stockField.getText();
		String addStockStr = addStockSpin.getValue().toString();
//		String stock 
		
// 		Integer price = Integer.parseInt(priceField.getText().toString().trim());
//		Integer stock = Integer.parseInt(stockField.getText().toString().trim());
//		Integer addStock = Integer.parseInt(addStockSpin.getValue().toString());
		
		
		

			String newId = newIdField.getText();
			String newName = newNameField.getText();
			String newType = newTypeCbx.getSelectedItem().toString();
			String newPriceStr = newPriceField.getText();
			String newStockStr =newStockSpin.getValue().toString();
//			Integer newStock = Integer.parseInt(stockField.getText());
		
		if(e.getSource() == insertBtn) {
			if (newId.isEmpty() || newName.isEmpty() || newType.isEmpty() || newPriceStr.isEmpty() || newStockStr.equals("0")) {
				JOptionPane.showMessageDialog(ManageBeverageRev1.this, "Please Fill the Beverage Id", "Message" ,JOptionPane.INFORMATION_MESSAGE);
				
			}else {
				
				int option = JOptionPane.showConfirmDialog(ManageBeverageRev1.this, "Are You Sure Want To Insert New Beverage?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				switch (option) {
				case JOptionPane.YES_OPTION:
				Integer price  = 0;
				Integer stock = 0;
					
					try {
				 price = Integer.parseInt(newPriceStr);
				stock = Integer.parseInt(newStockStr);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(ManageBeverageRev1.this, "Please Fill the Beverage Id", "Message" ,JOptionPane.INFORMATION_MESSAGE);
					}
					
					String sql = String.format(
							"INSERT INTO beverages VALUE('%s','%s','%s',%d,%d)", 
							newId, newName, newType,price,stock);
					 connection.execute(sql);
					refreshTable();
					break;
				default:
					break;
				}	
				
			}
		}else if(e.getSource() == updateBtn) {
			if ( name.isEmpty() || type.isEmpty() || priceStr.isEmpty()) {
				JOptionPane.showMessageDialog(ManageBeverageRev1.this, "Please Fill the Beverage Data!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
				
			}else {
				
				int option = JOptionPane.showConfirmDialog(ManageBeverageRev1.this, "Are You Sure Want To Update Beverage?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				Integer price = 0;
				switch (option) {
				case JOptionPane.YES_OPTION:
				try {
					 price = Integer.parseInt(priceStr);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(ManageBeverageRev1.this, "Price Must Be Numeric!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
				}
					if (price < 0) {
						JOptionPane.showMessageDialog(ManageBeverageRev1.this, "Price must more than 0!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
					}else {
					String sql = String.format(
							"UPDATE beverages SET  BeverageName = '%s', BeverageType = '%s', beveragePrice = %d WHERE beverageID = '%s'", 
							name, type,price, id);
					 connection.execute(sql);
					refreshTable();
					}
					break;
				default:
					break;
				}	
				
				
			
			}
		}else if(e.getSource() == addStockBtn) {
			if (addStockStr.equals("0")) {
				JOptionPane.showMessageDialog(ManageBeverageRev1.this, "Stock Must More Than 1", "Message" ,JOptionPane.INFORMATION_MESSAGE);
			}else {
				
				int option = JOptionPane.showConfirmDialog(ManageBeverageRev1.this, "Are You Sure Want To Add Beverage Stock?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				switch (option) {
				case JOptionPane.YES_OPTION:
//					String sql1 = String.format("SELECT BeverageStock FROM beverages WHERE beverageID ='%s' ", id);
//					ResultSet result = connection.executeQuery(sql1);
					
					
					int stock = Integer.parseInt(addStockStr);
					
					String sql2 = String.format(
							"UPDATE beverages SET BeverageStock = BeverageStock + %d  WHERE beverageID = '%s'", 
							stock, id);
					connection.execute(sql2);
					refreshTable();
					
//					try {
//						result.next();
//						int tempStock = result.getInt("BeverageStock");
//						stock = stock + tempStock;
//					
//					} catch (Exception e2) {
//						e2.printStackTrace();
//					}
					break;
				default:
					break;
				}	
				
				
			
//				Integer stock = Integer.parseInt(newStockStr);
//				String sql = String.format(
//						"UPDATE beverages SET BeverageStock -  WHERE beverageID = '%s'", 
//						name, type,price, id);
//				 connection.execute(sql);
//				refreshTable();
			}
		}else if (e.getSource() == deleteBtn) {
			
			int option = JOptionPane.showConfirmDialog(ManageBeverageRev1.this, "Are You Sure Want To Delete Beverage?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			switch (option) {
			case JOptionPane.YES_OPTION:
				String sql = String.format("DELETE FROM beverages WHERE beverageID = '%s' ", id);
				connection.execute(sql);
				refreshTable();
				break;
			default:
				break;
			}
			
			
		}else if(e.getSource() == resetBtn) {
			newNameField.setText("");
			newPriceField.setText("");
			newStockSpin.setValue(0);
		}
		
		
	
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int index = beverageTable.getSelectedRow();
		
		String id = (String) model.getValueAt(index, 0);
		String name = (String) model.getValueAt(index, 1);
		String type = (String) model.getValueAt(index, 2);
//		String type = typeCbx.getSelectedItem().toString();
		Integer price = (Integer) model.getValueAt(index, 3);
		Integer stock = (Integer) model.getValueAt(index, 4);
		
		idField.setText(id);
		nameField.setText(name);
		typeCbx.setSelectedItem(type);
		priceField.setText(price.toString());
		stockField.setText(stock.toString());
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
