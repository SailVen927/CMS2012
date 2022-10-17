package adView;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Message1_1Impl;

public class adMainView extends JFrame implements ActionListener{
	
	
	public adMainView(){
		JButton newsButton,commButton=null;
		JPanel panel;
		newsButton=new JButton("新闻管理");
		commButton=new JButton("评论管理");
		
		newsButton.addActionListener(this);
		commButton.addActionListener(this);
		
		JPanel p2;
		p2=new JPanel(new GridLayout(2,1));
		this.setLayout(new BorderLayout());
		this.add(p2,BorderLayout.CENTER);
		
		p2.add(newsButton);
		p2.add(commButton);
		
        this.setTitle("管理员界面");          
        this.setSize(900,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="新闻管理"){  
			 newsCView news=new newsCView(); 
	    }else if(e.getActionCommand()=="评论管理"){
	    	commentCView com=new commentCView();  
	    }
	}
	
}

