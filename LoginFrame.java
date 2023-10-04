import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginFrame extends JFrame implements ActionListener
{
	JLabel img = new JLabel(new ImageIcon("icons/coffee.png"));
	ImageIcon icon = new ImageIcon("icons/icon.png");
	JLabel lhead = new JLabel("CaFe Billing System");
	JLabel luser = new JLabel("Username");
	JLabel lpass = new JLabel("Password");
	JTextField tuser = new JTextField();
	JPasswordField tpass = new JPasswordField();
	JButton blogin = new JButton("Login",new ImageIcon("icons/log.png"));
	JButton bcancel = new JButton("Cancel",new ImageIcon("icons/close.png"));
	
	LoginFrame()
	{
		super("Login Frame");
		setLayout(null);
		setSize(630,370);
		setLocation(400,200);
		setResizable(false);
		lhead.setBounds(60,20,550,60);  add(lhead);
		luser.setBounds(320,150,125,30);  add(luser);
		lpass.setBounds(320,190,125,30);  add(lpass);
		tuser.setBounds(440,150,150,30);  add(tuser);
		tpass.setBounds(440,190,150,30);  add(tpass);
		blogin.setBounds(340,240,100,30);  add(blogin);
		bcancel.setBounds(450,240,100,30);  add(bcancel);
		
		setIconImage(icon.getImage());
		img.setBounds(0,0,630,360);    add(img);
		
		tpass.setEchoChar('*');
		lhead.setFont(new Font("MV Boli",Font.BOLD,50));
		luser.setFont(new Font("Corbel",Font.BOLD,25));
		lpass.setFont(new Font("Corbel",Font.BOLD,25));
		blogin.setBackground(Color.white);
		bcancel.setBackground(Color.white);
		blogin.addActionListener(this);
		bcancel.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String usr = tuser.getText();
		String pwd = tpass.getText();

		if(ae.getSource()==blogin)
		{
			if(tuser.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Invalid Username", "Dialog",JOptionPane.ERROR_MESSAGE);
				tuser.requestFocus();
			}
			else if(tpass.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Invalid Password", "Dialog",JOptionPane.ERROR_MESSAGE);
				tpass.requestFocus();
			}
			else if(usr.equals("admin") && pwd.equals("admin"))
			{
				new MainFrame();
				setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Invalid User", "Dialog",JOptionPane.ERROR_MESSAGE);
				tuser.setText(null);
				tpass.setText(null);				
				tuser.requestFocus();
			}
		}
		if(ae.getSource()==bcancel)
		{
			System.exit(0);
		}
	}
	public static void main(String s[])
	{
		new LoginFrame();
	}
}