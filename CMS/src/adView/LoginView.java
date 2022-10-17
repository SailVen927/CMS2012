package adView;  
import javax.swing.*;  


import dataConnect.Database;

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

public class LoginView extends JFrame implements ActionListener {  
     
    JTextField nameField=null;  
    JPasswordField passField=null;  
    static Database dataBase;
     
    public LoginView()  
    {   JButton enterButton,exitButton=null;  
    	JLabel nameLabel,passLabel,picLabel=null;
    	JPanel p1,p2,p3,p4,p5=null; 
    	
    	
    	nameLabel=new JLabel("�û�����");  
    	passLabel=new JLabel("��    �룺"); 
    	nameField=new JTextField(10);  
        passField=new JPasswordField(10);  
        enterButton=new JButton("��¼");  
        exitButton=new JButton("����");

        enterButton.addActionListener(this);  
        exitButton.addActionListener(this); 
        
        p1=new JPanel();  
        p2=new JPanel();  
        p3=new JPanel();    
        p4=new JPanel();    
        p5=new JPanel(new GridLayout(3,1));    

        this.setLayout(new BorderLayout());
        this.add(p5,BorderLayout.CENTER);
        p5.add(p1);  
        p5.add(p2);  
        p5.add(p3);  
        p1.add(nameLabel); 
        p1.add(nameField); 
        p2.add(passLabel);  
        p2.add(passField);  
        p3.add(enterButton);  	
        p3.add(exitButton);
        p1.setBackground(Color.white);
        p2.setBackground(Color.white);
        p3.setBackground(Color.white);
        p4.setBackground(Color.white);
        
        this.setTitle("CMS����Ա��¼");          
        this.setSize(350,300);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  
        this.setResizable(true); 
        
    }  
    
    public void actionPerformed(ActionEvent e){  	
        if(e.getActionCommand()=="��¼"){ 
        	adLoginEntrance();
        }else if(e.getActionCommand()=="����"){  
          
        	System.exit(0);
        }         
    }  
   
//����Ա��¼���
public void adLoginEntrance(){
	if(nameField.getText().equals("123") && passField.getText().equals("123")){
		nameField.setText("");  
        passField.setText(""); 
        if(dataBase!=null){
        	dataBase.close();
        }
        dispose();
        adMainView admainView=new adMainView();
        
	}else{
		JOptionPane.showMessageDialog(null,"  ����Ա�������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
	}
    	
}

          
}//LoginView class

