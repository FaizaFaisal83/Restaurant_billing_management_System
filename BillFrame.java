import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

class BillFrame extends JFrame implements ActionListener
{
	JLabel img = new JLabel(new ImageIcon("icons/cup.jpg"));
	ImageIcon icon = new ImageIcon("icons/icon.png");
	JLabel l1 = new JLabel("Enter BillNo.");
	JTextField t1 = new JTextField();
	JButton b1 = new JButton("Find",new ImageIcon("icons/search.png"));
	
	JLabel l2 = new JLabel("BillNo");
	JLabel l3 = new JLabel("Items");
	JLabel l4 = new JLabel("Amount");
	JLabel l5 = new JLabel("Date");
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField();
	JTextField t4 = new JTextField();
	JTextField t5 = new JTextField();
	JButton b2 = new JButton("Close",new ImageIcon("icons/cancel.png"));
	
	public void showData()
	{
		if(t1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please Enter BillNo.");
			t1.requestFocus();
		}
		else 
		{
			l2.setVisible(true);
			l3.setVisible(true);
			l4.setVisible(true);
			l5.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			t5.setVisible(true);
			
			Connection cn;
			Statement st;
			ResultSet rs;
			int x = Integer.parseInt(t1.getText());
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cfm","root","");
				st = cn.createStatement();
				rs = st.executeQuery("Select billno,no_of_items,amount,billdate from bill where billno = "+x+"");
				while(rs.next())
				{
					t2.setText(rs.getString(1));
					t3.setText(rs.getString(2));
					t4.setText(rs.getString(3));
					String date = rs.getString(4);
					t5.setText(date.substring(0,10));
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
		}
	}
	BillFrame()
	{
		super("Bill Frame");
		setLayout(null);
		setSize(720,360);
		setLocation(400,200);
		setResizable(false);		
		setIconImage(icon.getImage());
		
		l1.setBounds(330,50,150,30);	 add(l1);
		t1.setBounds(460,50,150,30);	 add(t1);
		b1.setBounds(610,50,90,29);	 add(b1);
		l2.setBounds(420,120,80,30);     add(l2);
		t2.setBounds(500,120,150,30);     add(t2);
		l3.setBounds(420,155,80,30);     add(l3);
		t3.setBounds(500,155,150,30);     add(t3);
		l4.setBounds(420,190,80,30);     add(l4);
		t4.setBounds(500,190,150,30);     add(t4);
		l5.setBounds(420,225,80,30);     add(l5);
		t5.setBounds(500,225,150,30);     add(t5);
		b2.setBounds(500,270,90,30);     add(b2);
		
		img.setBounds(0,0,720,360);    add(img);
		
		
		l2.setVisible(false);
		l3.setVisible(false);
		l4.setVisible(false);
		l5.setVisible(false);
		t2.setVisible(false);
		t3.setVisible(false);
		t4.setVisible(false);
		t5.setVisible(false);
	
		b1.setToolTipText("Search Bill Details");
		b1.setBackground(Color.white);
		b2.setBackground(Color.white);
		l1.setFont(new Font("Roboto",Font.BOLD,18));
		l2.setFont(new Font("Roboto",Font.BOLD,18));
		l3.setFont(new Font("Roboto",Font.BOLD,18));
		l4.setFont(new Font("Roboto",Font.BOLD,18));
		l5.setFont(new Font("Roboto",Font.BOLD,18));
		t1.setFont(new Font("Roboto",Font.BOLD,16));
		t2.setFont(new Font("Roboto",Font.BOLD,16));
		t3.setFont(new Font("Roboto",Font.BOLD,16));
		t4.setFont(new Font("Roboto",Font.BOLD,16));
		t5.setFont(new Font("Roboto",Font.BOLD,16));
		b1.addActionListener(this);
		b2.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			showData();
		}
		if(ae.getSource()==b2)
		{
			setVisible(false);
		}
	}
	public static void main(String s[])
	{
		new BillFrame();
	}
}