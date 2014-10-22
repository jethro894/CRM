.jsp
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
 public static void main(String[] args) {
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  String url = null;
  String user = null;
  String password = null;
  String sql = null;
  try {
   Class.forName("com.mysql.jdbc.Driver"); //加载mysq驱动
  } catch (ClassNotFoundException e) {
   System.out.println("Driver Error");
   e.printStackTrace();//打印出错详细信息
  }
  try {
   url = 
    "jdbc:mysql://160.39.146.147/homework2?user=inta&password=inta";//简单写法：url = "jdbc:myqsl://localhost/test(数据库名)? user=root(用户)&password=yqs2602555(密码)";
   user = "inta";
   password = "inta";
   conn = DriverManager.getConnection(url,user,password);
  } catch (SQLException e) {
   System.out.println("Error in link to database");
   e.printStackTrace();
  }
  try {
   stmt = conn.createStatement();
   sql = "select * from view_record"
      
   

   //改这一句就行！！！！！


   rs = stmt.executeQuery(sql);//执行sql语句

  // while(rs.next()) {
  //  System.out.print(rs.getInt("deptno") + "   ");
  //  System.out.print(rs.getString("deptname") + "   ");
  // System.out.println(rs.getInt("age") + "   ");
  // }
  } catch (SQLException e) {
   System.out.println("Error in Database Manipulation");
   e.printStackTrace();
  }
//关闭数据库
  try {
   if(rs != null) {
    rs.close();
    rs = null;
   }
   if(stmt != null) {
    stmt.close();
    stmt = null;
   }
   if(conn != null) {
    conn.close();
    conn = null;
   }
  } catch(Exception e) {
   System.out.println("Error in database close");
   e.printStackTrace();
  }
 }
}

