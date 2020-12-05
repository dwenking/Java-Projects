import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;



public class InsertTest {
	
	public static void main(String[] a)
	{
		TxtFile f=new TxtFile("sixteen.txt");
		HashMap<String,String> text=new HashMap<String,String>();
		f.readFile(text);
		//System.out.println(text.size());
		batchInsertBook(text);
	}


	public static void batchInsertBook(HashMap<String,String> t)
	{
		//构建Java和数据库之间的桥梁介质
        try{            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("注册驱动成功!");
        }catch(ClassNotFoundException e1){
            System.out.println("注册驱动失败!");
            e1.printStackTrace();
        }
        
        String url="jdbc:mysql://localhost:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=utf8";        
        Connection conn = null;
        try {
        	//构建Java和数据库之间的桥梁：URL，用户名，密码
            conn = DriverManager.getConnection(url, "root", "123456");
            
            String sql = "insert into t_sixteen(id,str) values(?,?)";
            
            //构建数据库执行者
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            //执行SQL语句
            
            //values(1, 'Effective Java', 50)
            Iterator<String> it=t.keySet().iterator();
            while(it.hasNext())
            {
            	String key=it.next();
            	pstmt.setString(1,key);
                pstmt.setString(2,t.get(key));
                
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


