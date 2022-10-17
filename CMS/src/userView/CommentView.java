package userView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dataConnect.Database;

public class CommentView extends JFrame implements ActionListener{
	Database dataBase;
	JTextField commentField;
	String newString;
	

	public CommentView(String news){
		JButton confirmButton,returnButton=null;
		
		
		
		JScrollPane jsp;
		
		dataBase=new Database();
		newString=news;
		String nnum="";
		String content=null;
		ResultSet rs=dataBase.getRS("SELECT nnum FROM news WHERE ntitle=\'"+newString+"\'");
		try {
			rs.next();
			nnum=rs.getString(1).trim();
			rs=dataBase.getRS("SELECT ncontent FROM news WHERE ntitle=\'"+newString+"\'");
			rs.next();
			content=rs.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JTable table=new JTable();
		 table=dataBase.postcomOnlynews(nnum);
		jsp=new JScrollPane(table);
		JLabel newLabel;
		commentField=new JTextField();
		JPanel p1,p2,p3,p4,p5,p6,p7,p8;
		
		
		 
		confirmButton=new JButton("评论");
		returnButton=new JButton("返回");
	
		//监听
		returnButton.addActionListener(this);
		confirmButton.addActionListener(this);
		
		//布局
		this.setLayout(new BorderLayout());
		this.add(newLabel=new JLabel("<html>新闻：\n"+content),BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		newLabel.setPreferredSize(new Dimension(0,400));
		p1=new JPanel(new BorderLayout());
		this.add(p1,BorderLayout.SOUTH);
		p1.add(new JLabel("发表评论："),BorderLayout.NORTH);
		p1.add(commentField,BorderLayout.CENTER);
		p2=new JPanel();
		p2.add(confirmButton);
		p2.add(returnButton);
		p1.add(p2,BorderLayout.SOUTH);

		
		this.setTitle("新闻");          
        this.setSize(1000,700);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="评论"){
				dataBase=new Database();
				
				try {
					int temp=0;
					ResultSet rs1=dataBase.getRS("SELECT MAX(cnum) FROM commentList");
					rs1.next();
						temp=Integer.parseInt(rs1.getString(1).trim());
						temp++;
					ResultSet rs2=dataBase.getRS("SELECT nnum FROM news WHERE ntitle=\'"+newString+"\'");
					rs2.next();
					dataBase.noReturnFunction("INSERT INTO commentList VALUES(\'"+commentField.getText().trim()+"\',\'"+temp+"\',\'"+rs2.getString(1).trim()+"\')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					JOptionPane.showMessageDialog(null,"评论成功！");
					
				
		}else if(e.getActionCommand()=="返回"){
			commentField.setText("");
			dispose();
		}
}//actionPerformed
	
	
}
