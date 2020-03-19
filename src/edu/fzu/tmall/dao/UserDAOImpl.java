package edu.fzu.tmall.dao;


import edu.fzu.tmall.pojo.User;
import edu.fzu.tmall.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  UserDAOImpl implements UserDAO {

	@Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from User";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

	@Override
    public void add(User bean) {
        String sql = "insert into user values(null ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /*
    在数据库中更新bean的值
     */
	@Override
    public void update(User bean) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE user SET password=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);;
            stmt.setString(1,bean.getPassword());
            stmt.setString(2,String.valueOf(bean.getId()));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

	@Override
    public void delete(int id) {
    }
	
	@Override
    public User get(int id) {
        return null;
    }




    public  List convertList(ResultSet rs) throws SQLException{
        List list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取行的数量
        while (rs.next()) {
            /*Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);*/
            User user = new User();
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setId(rs.getInt("id"));
            list.add(user);
        }
        return list;
    }


	@Override
    public List<User> list() {
        List<User> list =null;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            list = convertList(rs);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return list;
    }

	@Override
    public List<User> list(int start, int count) {
       return null;
    }

	@Override
    public boolean isExist(String name) {
        User user = get(name);
        return user != null;

    }

	@Override
    public User get(String name) {
        return null;
    }

    public User get(String name, String password) {
       return null;
    }


    public static void main(String[] args){
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.list();
        for(User us:users){
             System.out.println(us.getName()+us.getId());
        }
    }



}