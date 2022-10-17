package userView;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;

import adView.LoginView;

import dataConnect.Database;

public class MainView extends JFrame implements ActionListener{
	JTable table;
	private javax.swing.Timer timer;
	private JLabel timeLabel;
	
	public static void main(String[] args){ 
		
		MainView mainView=new MainView();
		 } 
	public MainView(){
		JScrollPane jsp;
		JTextField commentField;
		JFrame mainJFrame=null;
		JButton enterButton,searchButton,fixButton,returnButton=null;
		String tempString;
		
		InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 25));//全局字体设置
		Database db=new Database();
		 table=new JTable();
		 table=db.postNews();
		jsp=new JScrollPane(table);
		commentField=new JTextField();
		
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	          public void valueChanged(ListSelectionEvent e) {
	              String value;
	              value = table.getValueAt(table.getSelectedRow(),0).toString(); 
	              CommentView commentView=new CommentView(value);
	          }
	      });
	   
		enterButton=new JButton("管理员登录");
		returnButton=new JButton("退出");
		
		timeLabel=new JLabel();

		timer=new javax.swing.Timer(500, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeLabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
			}
		});
		timer.start();
		
		enterButton.addActionListener(this);
		returnButton.addActionListener(this);
		
		JPanel p1;
		this.setLayout(new BorderLayout());
		this.add(jsp,BorderLayout.CENTER);
		p1=new JPanel();
		this.add(p1,BorderLayout.SOUTH);
		p1.add(enterButton);
		p1.add(returnButton);
		this.add(new JLabel("点击新闻可以评论"),BorderLayout.NORTH);
		this.add(timeLabel,BorderLayout.NORTH);

        this.setTitle("CMS内容管理系统");          
        this.setSize(900,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="管理员登录"){  
			LoginView loginView=new LoginView();
	    }else if(e.getActionCommand()=="退出"){
	    	 dispose(); 
	    }
	}
	
	private static void InitGlobalFont(Font font){
		FontUIResource fontRes=new FontUIResource(font);
		for (Enumeration<Object> keys=UIManager.getDefaults().keys(); keys.hasMoreElements();){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if (value instanceof FontUIResource){
				UIManager.put(key,fontRes);
			}
		}
	}
	
	
}
