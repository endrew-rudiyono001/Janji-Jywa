import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JButton;
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

import com.mysql.cj.protocol.Resultset;


public class BuyBeverageFormRev1 extends JInternalFrame implements ActionListener, MouseListener{

	JPanel headerPanel = new JPanel(new BorderLayout()),
			contentPanel = new JPanel(new GridLayout(4,0,20,15)),
			footerPanel = new JPanel(new GridLayout(0,3,20,15));
	
	JPanel titlePanel = new JPanel(),
			headerTablePanel = new JPanel(new BorderLayout());
	
	JPanel	
			formPanel = new JPanel(new GridLayout(0,2,15,15)),
			form1Panel = new JPanel(new GridLayout(0,2,20,10)),
			form2Panel = new JPanel(new GridLayout(0,2,20,10)),
			formButtonPanel = new JPanel();
	
//	
	JPanel detailTablePanel = new JPanel(new BorderLayout()),
			detailGroupPanel = new JPanel(new BorderLayout()),
			detailButtonGroup = new JPanel(new GridLayout(0,3,10,15));
	
	JLabel titleLabel = new JLabel("Buy Beverage"),
			idLabel = new JLabel("Beverage ID"),
			nameLabel = new JLabel("Beverage Name"),
			typeLabel = new JLabel("Beverage Type"),
			priceLabel = new JLabel("Beverage Price"),
			stockLabel = new JLabel("Beverage Stock"),
			qtyLabel = new JLabel("Beverage Quantity");
	
	JTextField idField = new JTextField(),
			nameField = new JTextField(),
			typeField = new JTextField(),
			priceField = new JTextField(),
			stockField = new JTextField();
	
	JSpinner qtyField = new JSpinner();
	
	JButton addButton = new JButton("Add Cart"),
			removeButton = new JButton("Remove Selection Cart"),
			clearButton = new JButton("Clear Cart"),
			checkoutButton = new JButton("Checkout");
	
	JPanel removeButtonPanel = new JPanel(new BorderLayout()),
			clearButtonPanel = new JPanel(new BorderLayout()),
			checkoutButtonPanel = new JPanel(new BorderLayout());
			
	SpinnerNumberModel numberModel = new SpinnerNumberModel(0, 0, 1000000, 1);
	
	
	JPanel addButtonPanel = new JPanel();
//			
	String headerColumn[] = {"Beverage ID" , "Beverage Name", "Beverage Type" , "Beverage Price" ,"Beverage Stock"};
	DefaultTableModel headerModel = new DefaultTableModel(headerColumn , 0);
	JTable headerTable = new JTable(headerModel);
	JScrollPane headerScrollPane = new JScrollPane(headerTable);
//	
	String detailColumn[] = {"Beverage ID" , "Beverage Name", "Beverage Type" , "Beverage Price" ,"Beverage Stock", "Beverage Quantity" , "Sub Total"};
	DefaultTableModel detailModel = new DefaultTableModel(detailColumn , 0);
	JTable detailTable = new JTable(detailModel);
	JScrollPane detailScrollPane = new JScrollPane(detailTable);
	
	
	JPanel idLabelPanel = new JPanel(new BorderLayout()),
			 idFieldPanel = new JPanel(new BorderLayout()),
			 nameLabelPanel = new JPanel(new BorderLayout()),
			 nameFieldPanel = new JPanel(new BorderLayout()),
			 typeLabelPanel = new JPanel(new BorderLayout()),
			 typeFieldPanel = new JPanel(new BorderLayout()),
			 priceLabelPanel = new JPanel(new BorderLayout()),
			 priceFieldPanel = new JPanel(new BorderLayout()),
			 stockLabelPanel = new JPanel(new BorderLayout()),
			 stockFieldPanel = new JPanel(new BorderLayout()),
			 qtyLabelPanel = new JPanel(new BorderLayout()),
			 qtyFieldPanel = new JPanel(new BorderLayout());

	
//	JInternalFrame interFrame = new JInternalFrame();
	JPanel addFrame = new JPanel(new GridLayout(2,0,15,10));
	
	DatabaseConnection connection = new DatabaseConnection();
	
	public void frame() {
		setLayout(new BorderLayout());
		headerTable.setFillsViewportHeight(true);
		detailTable.setFillsViewportHeight(true);
//		interFrame.setVisible(true);
		
		titlePanel.add(titleLabel);
		
		headerPanel.add(titlePanel);
		
		headerTable.addMouseListener(this);
		
		headerTablePanel.add(headerScrollPane);
		
		contentPanel.add(headerTablePanel);
		
		idLabelPanel.add(idLabel);
		idFieldPanel.add(idField);
		nameLabelPanel.add(nameLabel);
		nameFieldPanel.add(nameField);
		typeLabelPanel.add(typeLabel);
		typeFieldPanel.add(typeField);
		priceLabelPanel.add(priceLabel);
		priceFieldPanel.add(priceField);
		stockLabelPanel.add(stockLabel);
		stockFieldPanel.add(stockField);
		qtyLabelPanel.add(qtyLabel);
		qtyFieldPanel.add(qtyField);
		addButtonPanel.add(addButton);

		form1Panel.add(idLabelPanel);
		form1Panel.add(idFieldPanel);
		form1Panel.add(nameLabelPanel);
		form1Panel.add(nameFieldPanel);
		form1Panel.add(typeLabelPanel);
		form1Panel.add(typeFieldPanel);
		
		
		form2Panel.add(priceLabelPanel);
		form2Panel.add(priceFieldPanel);
		form2Panel.add(stockLabelPanel);
		form2Panel.add(stockFieldPanel);
		form2Panel.add(qtyLabelPanel);
		form2Panel.add(qtyFieldPanel);
			
		formPanel.add(form1Panel);
		formPanel.add(form2Panel);
		formButtonPanel.add(addButtonPanel);
		
	
//		
//		contentPanel.add(addFormPanel);
		
		contentPanel.add(formPanel);
		contentPanel.add(formButtonPanel);
		
		
		idField.setEditable(false);
		nameField.setEditable(false);
		typeField.setEditable(false);
		priceField.setEditable(false);
		stockField.setEditable(false);
		
		addButton.addActionListener(this);
		removeButton.addActionListener(this);
		clearButton.addActionListener(this);
		checkoutButton.addActionListener(this);
		
		detailTablePanel.add(detailScrollPane);
		
		removeButtonPanel.add(removeButton);
		clearButtonPanel.add(clearButton);
		checkoutButtonPanel.add(checkoutButton);
		
		contentPanel.add(detailTablePanel);
		
		detailButtonGroup.add(removeButtonPanel);
		detailButtonGroup.add(clearButtonPanel);
		detailButtonGroup.add(checkoutButtonPanel);

		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(detailButtonGroup, BorderLayout.SOUTH);
		
//		add(interFrame);
	}
	
	
	public void headerRefreshTable() {
		headerModel.setRowCount(0);
		String sql = "SELECT * From beverages";
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
				headerModel.addRow(row);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public BuyBeverageFormRev1() {
		frame();
		headerRefreshTable();
		refreshDetailTable();
		setSize(800,600);
		System.out.println(generateId());
//		setTitle("Janji Jywa");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BuyBeverageFormRev1();
	}

	public void refreshDetailTable() {
		detailModel.setRowCount(0);
		String sql = String.format("SELECT c.BeverageID, "
				+ "BeverageName, "
				+ "BeverageType, "
				+ "BeveragePrice,"
				+ "BeverageStock,  "
				+ "Quantity, "
				+ "BeveragePrice * Quantity AS 'Sub Total' "
				+ "FROM carts c JOIN beverages b ON c.BeverageID = b.BeverageID WHERE UserID = '%s'", Users.getUserId());
		
		ResultSet result = connection.executeQuery(sql);
		try {
			while (result.next()) {
				Object[] row = new Object[] {
						result.getString("BeverageID"),
						result.getString("BeverageName"),
						result.getString("BeverageType"),
						result.getInt("BeveragePrice"),
						result.getInt("BeverageStock"),
						result.getInt("Quantity"),
						result.getInt("Sub Total")
				};
				detailModel.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = idField.getText();
		String name = nameField.getText();
		String type = typeField.getText();
		String price = priceField.getText();
		String stockStr = stockField.getText();
		String quantityStr = qtyField.getValue().toString();
		int row = detailTable.getSelectedRow();
		
	
		if (e.getSource() == addButton) {
			
			if (id.isEmpty() || name.isEmpty() || type.isEmpty() || price.isEmpty() || stockStr.isEmpty() || quantityStr.isEmpty()) {
				JOptionPane.showMessageDialog(BuyBeverageFormRev1.this, "Please Select The Beverage", "Message" ,JOptionPane.INFORMATION_MESSAGE);
				
			}else {
				int stock = Integer.parseInt(stockStr);
				int qty = Integer.parseInt(quantityStr);
				if (qty > stock || qty == 0) {
					JOptionPane.showMessageDialog(BuyBeverageFormRev1.this, "No More Stock For This Beverage", "Message" ,JOptionPane.INFORMATION_MESSAGE);
				}else {
					
	
				String sql = "SELECT c.BeverageID, Quantity, BeverageStock FROM carts c JOIN beverages b ON c.BeverageID = b.BeverageID";
				
				ResultSet result = connection.executeQuery(sql);
				
				try {
					
					String sql2 = String.format("INSERT INTO carts VALUE('%s', '%s', %d)", Users.getUserId(), id, qty);
					connection.execute(sql2);
					refreshDetailTable();
					
					while (result.next()) {
						
						if (id.contains(result.getString("BeverageID"))) {
							if (qty + result.getInt("Quantity") <= result.getInt("BeverageStock")) {
								String sql3 = String.format( "UPDATE carts SET Quantity = Quantity + %d WHERE BeverageID = '%s' ", qty, id);
								connection.execute(sql3);
								refreshDetailTable();
							}else {
								JOptionPane.showMessageDialog(BuyBeverageFormRev1.this, "No More Stock For This Beverage!", "Message" ,JOptionPane.INFORMATION_MESSAGE);
							}
						}
						
					}
					qtyField.setValue(0);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
					
				}
		   }
		}else if (e.getSource() == removeButton) {
			String detailId = (String) detailModel.getValueAt(row, 0);
			Integer detailQty = (Integer) detailModel.getValueAt	(row, 5);
			
			String sql = String.format("DELETE FROM carts WHERE BeverageID = '%s' AND Quantity = %d", detailId, detailQty);
			connection.execute(sql);
			JOptionPane.showMessageDialog(BuyBeverageFormRev1.this, "Successfully Remove The Cart!" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
			refreshDetailTable();
		}else if (e.getSource() == clearButton) {
			
			int option = JOptionPane.showInternalConfirmDialog(BuyBeverageFormRev1.this, "Are You Sure Want To Clear Cart?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			switch (option) {
			case JOptionPane.YES_OPTION:
				String sql 	= "DELETE FROM carts";
				connection.execute(sql);
				refreshDetailTable();
				break;

			default:
				break;
			}
			
			
			
		}else if(e.getSource() == checkoutButton) {
	int option = JOptionPane.showInternalConfirmDialog(BuyBeverageFormRev1.this, "Are You Sure Want To Checkout Cart?", "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			LocalDate date = LocalDate.now();
			
			
			switch (option) {
			case JOptionPane.YES_OPTION:
				String sql = "SELECT * From carts";
				ResultSet result = connection.executeQuery(sql);
				String newId = generateId();
				try {
					while (result.next()) {
						String headerSql = String.format("INSERT INTO headertransactions VALUE ('%s', '%s', '%s') ", newId , Users.getUserId(), date);
						connection.execute(headerSql);
						refreshDetailTable();
			
						String detailSql = String.format("INSERT INTO detailtransactions VALUE ('%s', '%s', %d) ",newId, result.getString("BeverageID") ,result.getInt("Quantity"));
						connection.execute(detailSql);
						refreshDetailTable();
						
						String updateSql = String.format("UPDATE beverages SET BeverageStock = BeverageStock - %d WHERE BeverageID = '%s'", result.getInt("Quantity") , result.getString("BeverageID"));
						connection.execute(updateSql);
						refreshDetailTable();
						
						String removeSql = String.format("DELETE FROM carts");
						connection.execute(removeSql);
						refreshDetailTable();
					
						JOptionPane.showMessageDialog(BuyBeverageFormRev1.this, "Successfully Checkout!" , "Message" ,JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
				
				break;

			default:
				break;
			}
		}
		
		
	}
		
		public String generateId() {
			String sql = "SELECT CAST(RIGHT(MAX(TransactionID), 3) AS INTEGER) AS 'totalTransaction' FROM headertransactions";
			ResultSet result = connection.executeQuery(sql);
			String newIdValue = new String();
			try {
			if (result.next()) {
				int len = result.getInt("totalTransaction");
				newIdValue += "TR";
				newIdValue += String.format("%03d", (len+1));
				len++;
			}else {
				return null;
			}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			return newIdValue;
		}
		

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = headerTable.getSelectedRow();
		
		String id = (String) headerModel.getValueAt(row, 0);
		String name = (String) headerModel.getValueAt(row, 1);
		String type = (String) headerModel.getValueAt(row, 2);
		Integer price = (Integer) headerModel.getValueAt(row, 3);
		Integer stock = (Integer) headerModel.getValueAt(row, 4);

		idField.setText(id);
		nameField.setText(name);
		typeField.setText(type);
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



	
//	public void archive() {

//	}
}
