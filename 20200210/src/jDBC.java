import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");//注册驱动
        //配置连接
        String url = "jdbc:mysql://127.0.0.1:3306/java20_0210?useSSL=false&characterEncoding=utf8";;
        String user = "root"; //连接 mysql 的用户名
        String password = ""; // 连接 mysql 的密码
        Connection connection = DriverManager.getConnection(url, user, password);


        //插入数据，供SELECT用
        {
            Statement statement = connection.createStatement();//通过连接获取一条语句对象
            String sql = "INSERT INTO users (id, name) VALUES (1, '陈沛鑫')";
            statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
            statement.close();
        }

        {
            Statement statement = connection.createStatement();
            String sql = "UPDATE users SET name = '啦啦' WHERE id = 1";
            statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
            statement.close();
        }

        {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM users WHERE id = 1";
            statement.executeUpdate(sql); // 通过 statement 对象来执行 SQL 语句
            statement.close();
        }

        //执行SELECT语句
        {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> nameList = new ArrayList<>();
            while (resultSet.next()) {
             /*需要不同的类型，调用不同的方法
            int r = resultSet.getInt();
            long r = resultSet.getLong();
            String r = resultSet.getString();

            int id = resultSet.getInt(1);   // 下标是从 1 开始的，不是从 0 开始的
            int id = resultSet.getInt("id"); // 通过字段名称获取
             */
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                // 结果集马上需要销毁，所以通过 List 保存结果
                nameList.add(name);

                System.out.println(id + ", " + name);
            }
            resultSet.close();
            statement.close();
        }
        connection.close();
    }
}
