import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

class MenuFrame extends JFrame implements ActionListener
{
	JLabel img = new JLabel(new ImageIcon("icons/Menu.jpg"));
	ImageIcon icon = new ImageIcon("icons/icon.png");
	JButton b1 = new JButton("Close",new ImageIcon("icons/cancel.png"));
	
	JLabel menu = new JLabel("Menu");
	JLabel item = new JLabel("Item                     Rate");
	JLabel item2 = new JLabel("Item                     Rate");
	JLabel line = new JLabel("-----------------------------");
	JLabel line2 = new JLabel("----------------------------");
	
	JLabel ltea = new JLabel("Tea .................... 10.00");
	JLabel lcoffee = new JLabel("Coffee ................ 15.00");
	JLabel lcold = new JLabel("Cold Coffee ......... 30.00");
	JLabel lmilk = new JLabel("Milk .................... 10.00");
	JLabel lbadam = new JLabel("Badam Milk ......... 20.00");
	JLabel llassi = new JLabel("Lassi .................... 25.00");
	JLabel ldrink = new JLabel("Cold Drink ............ 18.00");
	JLabel ljuice = new JLabel("Juice .................... 30.00");
	
	
	JLabel lpuff = new JLabel("Puff ...................... 15.00");
	JLabel lnoodles = new JLabel("Noodles ................ 50.00");
	JLabel lomellete = new JLabel("Omellete ............... 25.00");
	JLabel lmaggie = new JLabel("Maggie .................. 25.00");
	JLabel lsamosa = new JLabel("Samosa ................. 12.00");
	JLabel lpasta = new JLabel("Pasta .................... 25.00");
	JLabel lchaat = new JLabel("Chaat .................... 40.00");
	JLabel lcookies = new JLabel("Cookies ................. 15.00");
	
	
	MenuFrame()
	{
		super("Bill Frame");
		setLayout(null);
		setSize(900,432);
		setLocation(250,150);
		setResizable(false);		
		setIconImage(icon.getImage());
		
		menu.setBounds(450,10,100,30);	 add(menu);
		item.setBounds(50,50,500,30);	 add(item);
		line.setBounds(30,80,500,30);	 add(line);
		ltea.setBounds(50,110,500,30);	 add(ltea);
		lcoffee.setBounds(50,140,500,30);	 add(lcoffee);
		lcold.setBounds(50,170,500,30);	 add(lcold);
		lmilk.setBounds(50,200,500,30);	 add(lmilk);
		lbadam.setBounds(50,230,500,30);	 add(lbadam);
		llassi.setBounds(50,260,500,30);	 add(llassi);
		ldrink.setBounds(50,290,500,30);	 add(ldrink);
		ljuice.setBounds(50,320,500,30);	 add(ljuice);
		
		item2.setBounds(630,50,500,30);	 add(item2);
		line2.setBounds(610,80,500,30);	 add(line2);
		lpuff.setBounds(630,110,500,30);	 add(lpuff);
		lnoodles.setBounds(630,140,500,30);	 add(lnoodles);
		lomellete.setBounds(630,170,500,30);	 add(lomellete);
		lmaggie.setBounds(630,200,500,30);	 add(lmaggie);
		lsamosa.setBounds(630,230,500,30);	 add(lsamosa);
		lpasta.setBounds(630,260,500,30);	 add(lpasta);
		lchaat.setBounds(630,290,500,30);	 add(lchaat);
		lcookies.setBounds(630,320,500,30);	 add(lcookies);
		
		img.setBounds(0,0,900,432);    add(img);

		menu.setFont(new Font("Calibri",Font.BOLD,30));
		item.setFont(new Font("Calibri Light",Font.BOLD,20));
		item2.setFont(new Font("Calibri Light",Font.BOLD,20));
		line.setFont(new Font("Calibri Light",Font.BOLD,25));
		line2.setFont(new Font("Calibri Light",Font.BOLD,25));
		ltea.setFont(new Font("Calibri Light",Font.BOLD,18));
		lcoffee.setFont(new Font("Calibri Light",Font.BOLD,18));
		lcold.setFont(new Font("Calibri Light",Font.BOLD,18));
		lmilk.setFont(new Font("Calibri Light",Font.BOLD,18));
		lbadam.setFont(new Font("Calibri Light",Font.BOLD,18));
		llassi.setFont(new Font("Calibri Light",Font.BOLD,18));
		ldrink.setFont(new Font("Calibri Light",Font.BOLD,18));
		ljuice.setFont(new Font("Calibri Light",Font.BOLD,18));
		lpuff.setFont(new Font("Calibri Light",Font.BOLD,18));
		lnoodles.setFont(new Font("Calibri Light",Font.BOLD,18));
		lomellete.setFont(new Font("Calibri Light",Font.BOLD,18));
		lmaggie.setFont(new Font("Calibri Light",Font.BOLD,18));
		lsamosa.setFont(new Font("Calibri Light",Font.BOLD,18));
		lpasta.setFont(new Font("Calibri Light",Font.BOLD,18));
		lchaat.setFont(new Font("Calibri Light",Font.BOLD,18));
		lcookies.setFont(new Font("Calibri Light",Font.BOLD,18));
		
		b1.setBounds(400,365,100,30);	 add(b1);
		b1.setBackground(Color.white);
		b1.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			setVisible(false);
		}
	}
	public static void main(String s[])
	{
		new MenuFrame();
	}
}