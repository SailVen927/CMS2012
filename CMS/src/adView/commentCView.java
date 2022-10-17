package adView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import userView.CommentView;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dataConnect.Database;

public class commentCView extends JFrame implements ActionListener{
	JTable table;
	String value;
	Database database;

	public commentCView(){
		
		JScrollPane jsp;
		JButton deleButton,returnButton=null;
		
		
		database=new Database();
		deleButton=new JButton("ɾ��");
		returnButton=new JButton("����");
		table=new JTable();
		table=database.postcomALL();
		jsp=new JScrollPane(table);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	          public void valueChanged(ListSelectionEvent e) {
	              value=table.getValueAt(table.getSelectedRow(),1).toString(); 
	          }
	      });
		
		returnButton.addActionListener(this);
		deleButton.addActionListener(this);
		
		JPanel p1,p2,p3,p4,p5,p6,p7;
		this.setLayout(new BorderLayout());
		this.add(jsp,BorderLayout.CENTER);
		p1=new JPanel(new BorderLayout());
		this.add(p1,BorderLayout.SOUTH);
		p2=new JPanel();
		p1.add(p2,BorderLayout.SOUTH);
		p2.add(deleButton);
		p2.add(returnButton);
		
		this.setTitle("���۹���");          
        this.setSize(1200,900);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="ɾ��"){
			database.noReturnFunction("delete from commentList where cnum=\'"+value+"\'");
			JOptionPane.showMessageDialog(null,"ɾ�����۳ɹ���");
		}else if(e.getActionCommand()=="����"){
			dispose();
		}
}//actionPerformed
}

