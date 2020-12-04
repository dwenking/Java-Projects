import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class InsertTest {
	
	public static void main(String[] a)
	{
		safeInsertBook();	
		//batchInsertBook();
	}


	public static void safeInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_test(stuNo,stuName,questionNos) values(?,?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            int stuid = 110;
            String stuName = "张三";
            String quesNo = "1,3,5,7,9";
            
            //values(1, 'Effective Java', 50)
            pstmt.setInt(1, stuid);
            pstmt.setString(2, stuName);
            pstmt.setString(3, quesNo);
            
            int result = pstmt.executeUpdate();
                        
            pstmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(null != conn)
        		{
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }

	public static void batchInsertBook()
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test?allowMultiQueries=true";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_book(bookid,bookname,price) values(?,?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            
            String bookName = "aaaaaaaaaaaaaaaa";
            int price = 50;
            
            //values(1, 'Effective Java', 50)
            for(int i=200;i<210;i++)
            {
            	pstmt.setInt(1, i);
                pstmt.setString(2, bookName);
                pstmt.setInt(3, price);
                pstmt.addBatch();
            }            
            
            pstmt.executeBatch();
                        
            pstmt.close();
            
            System.out.println("操作成功");
            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
        	try {
        		if(null != conn) {
            		conn.close();
            	}
        	}
        	catch (SQLException e){
                e.printStackTrace();
        	}        	
        }
    }
}


