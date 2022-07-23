import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TransactionForm extends JInternalFrame implements MouseListener{

	
	JPanel headerPanel = new JPanel(),
			contentPanel = new JPanel(new GridLayout(3,0,5,15)),
			footerPanel = new JPanel(new GridLayout(1,0,5,15));
	
	JLabel titleLabel = new JLabel("Transaction History"),
			selectedLabel = new JLabel("Selected ID"),
			totalLabel = new JLabel("Grand Total");
			
	
	JTextField selectedField = new JTextField(10),
			totalField = new JTextField();
	
	
	String headerColumn[] = {"Transaction ID", "User ID" , "Transaction Date"}; 
	DefaultTableModel headerModel = new DefaultTableModel(headerColumn, 0);
	JTable headerTable = new JTable(headerModel);
	JScrollPane headerScrollPane = new JScrollPane(headerTable);
	
	String detailColumn[] = {"Transaction ID", "Beverage ID" , "Beverage Name", "Beverage Type" , "Beverage Price", "Beverage Quantity" , "Sub Total"};
	DefaultTableModel detailModel = new DefaultTableModel(detailColumn, 0);
	JTable detailTable = new JTable(detailModel);
	JScrollPane detailScrollPane = new JScrollPane(detailTable);
	
	JPanel selectedGroupPanel = new JPanel(new FlowLayout()),
			totalGroupPanel = new JPanel(new GridLayout(0,2,10,15));
	
	JPanel selectedLabelPanel = new JPanel(new BorderLayout()),
			selectedFieldPanel = new JPanel(new BorderLayout()),
			totalLabelPanel = new JPanel(new BorderLayout()),
			totalFieldPanel = new JPanel(new BorderLayout());
	
	JPanel headerTablePanel = new JPanel(new BorderLayout()),
			detailTablePanel = new JPanel(new BorderLayout());
	
//	JInternalFrame inFrame = new JInternalFrame();
	
	DatabaseConnection connection = new DatabaseConnection();
	
	public void frame() {
		setLayout(new BorderLayout());
//		inFrame.setVisible(true);
		
		headerPanel.add(titleLabel);

		headerTablePanel.add(headerScrollPane);
//		
		selectedField.setEditable(false);
		totalField.setEditable(false);
		
		headerTable.addMouseListener(this);
		contentPanel.add(headerTablePanel);

		totalLabelPanel.add(totalLabel);
		totalFieldPanel.add(totalField);
		
		selectedGroupPanel.add(selectedLabel);
		selectedGroupPanel.add(selectedField);
		
		headerTable.setFillsViewportHeight(true);
		detailTable.setFillsViewportHeight(true);
	
		
		
		contentPanel.add(selectedGroupPanel);
		
		detailTablePanel.add(detailScrollPane);
		
		contentPanel.add(detailTablePanel);
		
		totalGroupPanel.add(totalLabel);
		totalGroupPanel.add(totalField);
		
		footerPanel.add(totalGroupPanel);
		add(headerPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
//		add(inFrame);
	}
	
	public void headerRefreshTable() {
		headerModel.setRowCount(0);
		
//		String userId = new String();
		String userId = Users.getUserId();
		String sql = String.format("SELECT * From headertransactions WHERE UserID = '%s'", userId) ;
		ResultSet result = connection.executeQuery(sql);
		
		try {
			while(result.next()) {
				Object[] row = new Object[] {
					result.getString("TransactionID"),
					result.getString("UserID"),
					result.getString("TransactionDate")
				};
				headerModel.addRow(row);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public TransactionForm() {
		frame();
		headerRefreshTable();
		detailRefreshTable();
		setSize(800, 600);
//		setTitle("Janji Jywa");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public void detailRefreshTable() {
	
	}

	
	public static void main(String[] args) {
		new TransactionForm();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Integer grandTotal = 0;
		detailModel.setRowCount(0);
		int rowIndex = headerTable.getSelectedRow();
		String transactionID = (String) headerModel.getValueAt(rowIndex, 0);
		selectedField.setText(transactionID);
		String sql = String.format("SELECT "
				+ "TransactionID, "
				+ "b.BeverageID, "
				+ "BeverageName, "
				+ "BeverageType, "
				+ "BeveragePrice, "
				+ "Quantity, "
				+ "BeveragePrice * Quantity AS 'Subtotal'"
				+ "FROM detailtransactions dt "
				+ "JOIN beverages b ON dt.BeverageID = b.BeverageID "
				+ "WHERE TransactionID = '%s'", transactionID);
		
		ResultSet result = connection.executeQuery(sql);
		
		try {
			while(result.next()) {
				Object[] row = new Object[] {
					result.getString("TransactionID"),
					result.getString("BeverageID"),
					result.getString("BeverageName"),
					result.getString("BeverageType"),
					result.getString("BeveragePrice"),
					result.getInt("Quantity"),
					result.getInt("Subtotal")
				};
				grandTotal += result.getInt("Subtotal");
				totalField.setText(grandTotal.toString());
				detailModel.addRow(row);
				headerRefreshTable();
			}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
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
