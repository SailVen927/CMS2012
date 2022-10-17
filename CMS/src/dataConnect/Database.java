package dataConnect;

import java.sql.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Database{
	   Statement sql=null;
	   static Connection con=null;
	  ResultSet rs=null;
	  PreparedStatement ps=null;
   public Database() {
	   String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//数据库引擎
	   String uri="jdbc:sqlserver://localhost:1433;DatabaseName=CMS_DB";
	   String Name="sa";
	   String passWord="159753456ffsail";
	   try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con=DriverManager.getConnection(uri, Name, passWord);
		//System.out.println("数据库连接成功");
		//LoginView loginView=new LoginView();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.out.println("数据库连接失败了！");
	}
	   
   }//main
   
   public JTable postNews(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("");
       
       //数据库操作
       Database dataBase=new Database();
       rowData=new Vector<>();
       ResultSet rs=dataBase.getRS("Select ntitle from news");//rs存放查询结果
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));//content
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
	   }
	 
   public JTable postNewsALL(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("编号");
       columnNames.add("新闻标题");
       columnNames.add("新闻内容");
       
       //数据库操作
       Database dataBase=new Database();
       rowData=new Vector<>();
       ResultSet rs=dataBase.getRS("Select nnum,ntitle,ncontent from news");//rs存放查询结果
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));//content
				tempVector.add(rs.getString(2));//content
				tempVector.add(rs.getString(3));
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
	   }
   
   public JTable postcomALL(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("新闻编号");
       columnNames.add("评论编号");
       columnNames.add("评论");
       
       //数据库操作
       Database dataBase=new Database();
       rowData=new Vector<>();
       ResultSet rs=dataBase.getRS("Select fk_nnum,cnum,comment from commentList order by fk_nnum ASC");//rs存放查询结果
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));//content
				tempVector.add(rs.getString(2));//content
				tempVector.add(rs.getString(3));//content
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
	   }
   
public JTable postcomOnlynews(String nnum){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("评论");
       
       //数据库操作
       Database dataBase=new Database();
       rowData=new Vector<>();
       ResultSet rs=dataBase.getRS("Select comment from commentList where fk_nnum=\'"+nnum+"\'");//rs存放查询结果
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));//content
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(25);
       return table;
	   }

//返回SQL语句结果
   public ResultSet getRS(String selectSQL){
	   try{
		   ps=con.prepareStatement(selectSQL);
	       rs=ps.executeQuery();
	   }catch(Exception e){
	    e.printStackTrace();
	   }
	   return rs;
	  }//getRS
//关闭资源   
public void close(){
	   if(con!=null){
	    try {
	     con.close();
	     
	    } catch (SQLException e) {
	     e.printStackTrace();
	    }
	    con=null;
	   }
	  }//close
  
   //无参操作
   public void noReturnFunction(String insertSQL){
	   try {
		   ps=con.prepareStatement(insertSQL);
		   ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }//insert
}
