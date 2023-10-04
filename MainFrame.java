import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.print.*;
import java.text.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar; 

class MainFrame extends JFrame implements ActionListener
{
	JLabel img = new JLabel(new ImageIcon("icons/coffee.jpg"));
	ImageIcon icon = new ImageIcon("icons/icon.png");
	JLabel lhead = new JLabel("CaFe Billing System");
	JLabel lorder = new JLabel("Your Order");
	
	JTable table = new JTable();
	JScrollPane panel = new JScrollPane(table);
	Object[] cols = {"Item","Quantity","Rate","Total"};
	Object[] row = new Object[4];
	DefaultTableModel model = new DefaultTableModel();
	JLabel l1 = new JLabel("Select Item");
	JLabel l2 = new JLabel("Enter No. Of Items");
	JComboBox combo = new JComboBox();
	JTextField t1 = new JTextField();
	JButton btnAdd = new JButton("Add Item",new ImageIcon("icons/add.png"));
	JButton btnCal = new JButton("Calculate",new ImageIcon("icons/rupees.png"));
	JButton btnSave = new JButton("Save Data",new ImageIcon("icons/save.png"));
	JButton btnBill = new JButton("View Bill",new ImageIcon("icons/bill.png"));
	JButton btnMenu = new JButton("View Menu",new ImageIcon("icons/menu.png"));
	JButton btnLogout = new JButton("Logout",new ImageIcon("icons/logout.png"));
	
	JLabel lbill = new JLabel("BillNo:");
	JLabel ldate = new JLabel("Date:");
	JLabel lgst = new JLabel("GST(10.25%):");
	JLabel ltotal = new JLabel("Sub-Total:");
	JTextField tbill = new JTextField();
	JTextField tdate = new JTextField();
	JTextField tgst = new JTextField();
	JTextField ttotal = new JTextField();
	
	double total=0,gst=0;
	int inc=0,billno=0,nor=0;
	String date;
	Connection cn;
	Statement st;
	ResultSet rs;
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==btnAdd)
		{
			addItems();
		}
		if(ae.getSource()==btnCal)
		{
			CalculateBill();
		}
		if(ae.getSource()==btnSave)
		{
			storeBill();
		}
		if(ae.getSource()==btnBill)
		{
			new BillFrame();
		}	
		if(ae.getSource()==btnMenu)
		{
			new MenuFrame();
		}
		if(ae.getSource()==btnLogout)
		{
			System.exit(0);
		}
		
	}
	public void CalculateBill()
	{
		nor = table.getRowCount();
		if(nor < 1)
		{
			JOptionPane.showMessageDialog(null,"Please Add Record in Table");
		}
		else
		{
			int rows=table.getRowCount();  
			for(int i=0;i<rows;i++)
			{
				total = total+Double.parseDouble(table.getValueAt(i,3).toString());
			}
			//calculate gst
			gst = (double)Math.round((10.25/100)*total);
			tgst.setText(String.valueOf(gst));
			ttotal.setText(String.valueOf(total+gst));
		}
	}
	public void GenerateBillNo()
	{
		try
		{
			int x;
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cfm","root","");
			st = cn.createStatement();
			rs = st.executeQuery("SELECT * FROM bill WHERE billno=(SELECT MAX(billno) FROM bill);");
			if(rs.next())
			{
				int Gen_bill = rs.getInt(1) + 1;
				tbill.setText(Integer.toString(Gen_bill));
				System.out.println(Integer.toString(Gen_bill));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
	MainFrame()
	{
		super("Main Frame");
		setLayout(null);
		setSize(1166,655);
		setLocation(100,40);
		setResizable(false);
		
		lhead.setBounds(100,10,550,60);  add(lhead);
		lorder.setBounds(300,80,200,40);  add(lorder);
		lbill.setBounds(100,140,80,30);  add(lbill);
		ldate.setBounds(400,140,80,30);  add(ldate);
		lgst.setBounds(170,390,120,30);     add(lgst);
		ltotal.setBounds(400,390,100,30);     add(ltotal);
		
		tbill.setBounds(170,140,120,30);  add(tbill);
		tdate.setBounds(480,140,120,30);  add(tdate);
		tgst.setBounds(290,390,80,30);  add(tgst);
		ttotal.setBounds(500,390,100,30);  add(ttotal);
		
		
		lbill.setFont(new Font("Roboto",Font.BOLD,18));
		ldate.setFont(new Font("Roboto",Font.BOLD,18));
		tbill.setFont(new Font("Roboto",Font.BOLD,16));
		tdate.setFont(new Font("Roboto",Font.BOLD,16));
		lgst.setFont(new Font("Roboto",Font.BOLD,18));
		ltotal.setFont(new Font("Roboto",Font.BOLD,18));
		tgst.setFont(new Font("Roboto",Font.BOLD,16));
		ttotal.setFont(new Font("Roboto",Font.BOLD,16));
		
		//function for generate billno
		/*LocalDateTime now = LocalDateTime.now();
		int x = Integer.parseInt(DateTimeFormatter.ofPattern("ddMMyyyy").format(now));
		tbill.setText(String.valueOf(x+"1"));*/
		
		GenerateBillNo();
		
		//System time.
		LocalDateTime now = LocalDateTime.now();
        date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(now);
		tdate.setText(date);
		
		
		addTable();    //add Table
		tbill.setEditable(false);
		tdate.setEditable(false);
		tgst.setEditable(false);
		ttotal.setEditable(false);
		
		l1.setBounds(200,450,150,30);     add(l1);
		l2.setBounds(400,450,200,30);     add(l2);
		combo.setBounds(200,480,170,30);  add(combo);
		t1.setBounds(400,480,100,30);     add(t1);
		btnAdd.setBounds(180,530,120,30);  add(btnAdd);
		btnAdd.setBackground(Color.white);
		btnCal.setBounds(310,530,120,30);  add(btnCal);
		btnCal.setBackground(Color.white);
		btnSave.setBounds(180,570,120,30);  add(btnSave);
		btnSave.setBackground(Color.white);
		btnBill.setBounds(310,570,120,30);  add(btnBill);
		btnBill.setBackground(Color.white);
		btnMenu.setBounds(440,530,120,30);  add(btnMenu);
		btnMenu.setBackground(Color.white);
		btnLogout.setBounds(440,570,120,30);  add(btnLogout);
		btnLogout.setBackground(Color.white);
		
		combo.addItem("Select...");
		combo.addItem("Tea");
		combo.addItem("Coffee");
		combo.addItem("Cold Coffee");
		combo.addItem("Milk");
		combo.addItem("Badam Milk");
		combo.addItem("Cold Drink");
		combo.addItem("Juice");
		combo.addItem("Lassi");
		combo.addItem("Puff");
		combo.addItem("Omellete");
		combo.addItem("Noodles");
		combo.addItem("Maggie");
		combo.addItem("Pasta");
		combo.addItem("Samosa");
		combo.addItem("Chaat");
		combo.addItem("Cookies");
		setIconImage(icon.getImage());
		img.setBounds(0,0,1166,655);    add(img);
		
		lhead.setFont(new Font("MV Boli",Font.BOLD,50));
		lorder.setFont(new Font("MV Boli",Font.BOLD,30));
		l1.setFont(new Font("Roboto",Font.BOLD,20));
		l2.setFont(new Font("Roboto",Font.BOLD,20));
		combo.setFont(new Font("Roboto",Font.BOLD,16));
		
		btnAdd.addActionListener(this);
		btnCal.addActionListener(this);
		btnSave.addActionListener(this);
		btnBill.addActionListener(this);
		btnMenu.addActionListener(this);
		btnLogout.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void storeBill()
	{
		if(tbill.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"BillNo is Not Valid");
		}
		else if(ttotal.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"First Calculate Total");
		}
		else if(tgst.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"First Calculate GST");
		}
		else
		{
		try
		{	  
            String sqlDate = "" + date;  
			int x = Integer.parseInt(tbill.getText());
			float y = Float.parseFloat(ttotal.getText());
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cfm","root","");
			st = cn.createStatement();
			String str = "insert into bill(billno,no_of_items,amount,billdate) values("+x+","+nor+","+y+",now())";
			st.executeUpdate(str);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		ttotal.setText(null);
		tgst.setText(null);
		t1.setText(null);
		model.setRowCount(0);
		combo.setSelectedIndex(0);
		GenerateBillNo();
		}
	}
	public void addBillNo()
	{
		billno=Integer.parseInt(tbill.getText());
		int i=1;
		tbill.setText(String.valueOf(billno+i));
	}
	public void addTable()
	{
		model.setColumnIdentifiers(cols);
        table.setModel(model);
		panel.setBounds(130,180,500,200);
		add(panel);
	}	
	public static void main(String s[])
	{
		new MainFrame();
	}
	public void addItems()
	{
		int tea=10,coffee=15,cold=30,milk=10,badam=20,drink=18,lassi=25,juice=30;
		int puff=15,omellete=25,maggie=25,pasta=25,samosa=12,cookies=15,noodles=50,chaat=40;
		if(combo.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"Please Select Item");
			combo.requestFocus();
		}
		else if(t1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please Enter Quantity");
			t1.requestFocus();
		}
		else
		{
			row[0]=combo.getSelectedItem();
			row[1]=t1.getText();
			if(combo.getSelectedItem()=="Tea")
			{
				row[2]=tea;
				row[3]=Integer.parseInt(t1.getText())*tea;
			}
			if(combo.getSelectedItem()=="Coffee")
			{
				row[2]=coffee;
				row[3]=Integer.parseInt(t1.getText())*coffee;
			}
			if(combo.getSelectedItem()=="Cold Coffee")
			{
				row[2]=cold;
				row[3]=Integer.parseInt(t1.getText())*cold;
			}
			if(combo.getSelectedItem()=="Milk")
			{
				row[2]=milk;
				row[3]=Integer.parseInt(t1.getText())*milk;
			}
			if(combo.getSelectedItem()=="Badam Milk")
			{
				row[2]=badam;
				row[3]=Integer.parseInt(t1.getText())*badam;
			}
			if(combo.getSelectedItem()=="Cold Drink")
			{
				row[2]=drink;
				row[3]=Integer.parseInt(t1.getText())*drink;
			}
			if(combo.getSelectedItem()=="Lassi")
			{
				row[2]=lassi;
				row[3]=Integer.parseInt(t1.getText())*lassi;
			}
			if(combo.getSelectedItem()=="Juice")
			{
				row[2]=juice;
				row[3]=Integer.parseInt(t1.getText())*juice;
			}
			if(combo.getSelectedItem()=="Puff")
			{
				row[2]=puff;
				row[3]=Integer.parseInt(t1.getText())*puff;
			}
			if(combo.getSelectedItem()=="Omellete")
			{
				row[2]=omellete;
				row[3]=Integer.parseInt(t1.getText())*omellete;
			}
			if(combo.getSelectedItem()=="Noodles")
			{
				row[2]=noodles;
				row[3]=Integer.parseInt(t1.getText())*noodles;
			}
			if(combo.getSelectedItem()=="Maggie")
			{
				row[2]=maggie;
				row[3]=Integer.parseInt(t1.getText())*maggie;
			}
			if(combo.getSelectedItem()=="Pasta")
			{
				row[2]=pasta;
				row[3]=Integer.parseInt(t1.getText())*pasta;
			}
			if(combo.getSelectedItem()=="Samosa")
			{
				row[2]=samosa;
				row[3]=Integer.parseInt(t1.getText())*samosa;
			}
			if(combo.getSelectedItem()=="Chaat")
			{
				row[2]=chaat;
				row[3]=Integer.parseInt(t1.getText())*chaat;
			}
			if(combo.getSelectedItem()=="Cookies")
			{
				row[2]=cookies;
				row[3]=Integer.parseInt(t1.getText())*cookies;
			}
			model.addRow(row);
		}
			
	}
}