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
import javax.swing.JComboBox;
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

public class newsCView extends JFrame implements ActionListener{
	JTable table;
	JTextField titleField,contentField=null;
	String value1,value2;
	Database database;

	public newsCView(){
		
		JScrollPane jsp;
		JButton addButton,updatButton,deleButton,returnButton=null;
		
		database=new Database();
		addButton=new JButton("����");
		updatButton=new JButton("����");
		deleButton=new JButton("ɾ��");
		returnButton=new JButton("����");
		titleField=new JTextField(10);
		contentField=new JTextField(10);
		table=new JTable();
		table=database.postNewsALL();
		jsp=new JScrollPane(table);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	          public void valueChanged(ListSelectionEvent e) {
	              value1=table.getValueAt(table.getSelectedRow(),0).toString(); 
	              value2= table.getValueAt(table.getSelectedRow(),1).toString(); 
	          }
	      });
		
		returnButton.addActionListener(this);
		addButton.addActionListener(this);
		updatButton.addActionListener(this);
		deleButton.addActionListener(this);
		
		JPanel p1,p2,p3,p4,p5,p6,p7;
		this.setLayout(new BorderLayout());
		this.add(jsp,BorderLayout.CENTER);
		p1=new JPanel(new BorderLayout());
		this.add(p1,BorderLayout.SOUTH);
		p2=new JPanel();
		p1.add(p2,BorderLayout.SOUTH);
		p2.add(addButton);
		p2.add(updatButton);
		p2.add(deleButton);
		p2.add(returnButton);
		p3=new JPanel(new GridLayout(1,2));
		p1.add(p3,BorderLayout.CENTER);
		p4=new JPanel(new BorderLayout());
		p4.add(new JLabel("���⣺"),BorderLayout.NORTH);
		p4.add(titleField,BorderLayout.CENTER);
		p5=new JPanel(new BorderLayout());
		p5.add(new JLabel("���ݣ�"),BorderLayout.NORTH);
		p5.add(contentField,BorderLayout.CENTER);
		p3.add(p4);
		p3.add(p5);
		
		this.setTitle("���Ź���");          
        this.setSize(1200,900);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="����"){
			database=new Database();
			try {
				ResultSet rs1=database.getRS("SELECT MAX(nnum) FROM news");
				rs1.next();
				System.out.println(rs1.getString(1).trim());
				int temp=Integer.parseInt(rs1.getString(1).trim());
				temp++;
				database.noReturnFunction("INSERT INTO news VALUES(\'"+temp+"\',\'"+contentField.getText().trim()+"\',\'"+titleField.getText().trim()+"\')");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
				JOptionPane.showMessageDialog(null,"�������ųɹ���");
		}else if(e.getActionCommand()=="����"){
			String t=titleField.getText().trim();
			String c=contentField.getText().trim();
			if(t.isEmpty() && !c.isEmpty()){
				database.noReturnFunction("UPDATE news SET ncontent=\'"+contentField.getText().trim()+"\' WHERE nnum=\'"+value1+"\'");
				JOptionPane.showMessageDialog(null,"�޸��������ݳɹ���");
			}else if(c.isEmpty() && !t.isEmpty()){
				database.noReturnFunction("UPDATE news SET ntitle=\'"+titleField.getText().trim()+"\' WHERE nnum=\'"+value1+"\'");
				JOptionPane.showMessageDialog(null,"�޸����ű���ɹ���");
			}else if(!c.isEmpty() && !t.isEmpty()){
				database.noReturnFunction("UPDATE news SET ncontent=\'"+contentField.getText().trim()+"\' WHERE nnum=\'"+value1+"\'");
				database.noReturnFunction("UPDATE news SET ntitle=\'"+titleField.getText().trim()+"\' WHERE nnum=\'"+value1+"\'");
				JOptionPane.showMessageDialog(null,"�޸����ű�������ݳɹ���");
			}else{
				JOptionPane.showMessageDialog(null,"δ�������ݣ�");
			}
			
			
		}else if(e.getActionCommand()=="ɾ��"){
			database.noReturnFunction("delete from commentList where fk_nnum=\'"+value1+"\'");
			database.noReturnFunction("delete from news where nnum=\'"+value1+"\'");
			JOptionPane.showMessageDialog(null,"ɾ�����ųɹ���");
		}else if(e.getActionCommand()=="����"){
			dispose();
		}
}//actionPerformed
}

