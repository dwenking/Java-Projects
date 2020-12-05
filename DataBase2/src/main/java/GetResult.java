import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class GetResult {
	public static ArrayList<Student> readDB() {
		//构建Java和数据库之间的桥梁介质
		ArrayList<Student> s=new ArrayList<Student>();
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
            return null;
        }
        
        String url="jdbc:mysql://localhost:3306/test";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            //构建数据库执行者
            Statement stmt = conn.createStatement(); 
            System.out.println("创建Statement成功！");      
            
            //执行SQL语句并返回结果到ResultSet
            ResultSet rs = stmt.executeQuery("select stuNo, stuName, questionNos from t_test");
            while(rs.next()) {
            	Student input=new Student();
            	input.setStuNo(rs.getObject(1).toString());
            	input.setStuName(rs.getObject(2).toString());
            	input.setQuesNos(rs.getObject(3).toString());
            	s.add(input);
            }
                        
            rs.close();
            stmt.close();
            
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
        return s;
    }
}
