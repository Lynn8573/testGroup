package com.qfedu.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类是对数据库进行增删改查的
 */
public class BaseDao {

    /**
     * 统一的修改方法，处理增删改的
     * @param sql  需要执行的sql语句
     * @param parameters  对应的sql语句的参数，现在弄成数组了
     * @return
     */
    public int update(String sql, Object[] parameters) throws SQLException{
        if (sql == null) {
            throw new SQLException("sql is null");
        }
        //1.获取数据库的连接
        Connection connection = JdbcUtil.getConnection();
        //2.使用对应的sql获取对应预处理的搬运工
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.开始处理参数Object[] obj = new Object[]{"宝强", 12, "经纪人"};
        // String sql = "insert into work (name, age, info) values (?, ?, ?)";
        //获取是参数的个数
        int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();//3
        //4.使用预处理的搬运工对象对咱们的sql语句进行赋值操作
        if (parameters != null && parameters.length == parameterCount) {
            for (int i = 1; i <= parameterCount; i++) {
                preparedStatement.setObject(i, parameters[i -1]);
            }
        }
        //5执行了sql语句
        int i = preparedStatement.executeUpdate();
        //6.关闭资源
        JdbcUtil.close(connection, preparedStatement);
        return i;

    }

    /**
     *
     * @param sql
     * @param parameters
     * @param cls  这个参数需要带泛型，来约束当前方法中使用的泛型
     *             这个参数还提供了一个非常重要的信息，指定查询数据的类的对象
     * @param <T>
     * @return
     */
    public <T> List<T> query(String sql, Object[] parameters, Class<T> cls) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
       if (sql == null || cls == null) {
           throw new NullPointerException();
       }
       //1.获取数据库连接对象
        Connection connection = JdbcUtil.getConnection();
       //2.使用sql预处理的搬运工
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.参数的处理，借助于参数元数据
        int parameterCount = preparedStatement.getParameterMetaData().getParameterCount();
        //4.对sql里面的？进行赋值
        if (parameters != null && parameters.length == parameterCount) {
            for (int i = 1; i <= parameterCount; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
        }
        //5.执行查询的sql
        ResultSet resultSet = preparedStatement.executeQuery();
        //6.准备List集合
        List<T> list = new ArrayList<>();
        //7.获取结果集元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        //8.获取字段的个数
        int columnCount = metaData.getColumnCount();
        //9.遍历
        while (resultSet.next()) {
            //10创建类对象（来接数据查询出来的数据）Work work = new Work()
            T t = cls.getConstructor(null).newInstance(null);
            for (int i = 1; i <=  columnCount; i++) {
                //12.获取字段的名字
                String columnName = metaData.getColumnName(i);//id, name ,age
                //13.获取对应字段的数据
                Object value = resultSet.getObject(columnName);
                //14.使用BeanUtils给指定字段的数据在类对象中给赋值
                BeanUtils.setProperty(t, columnName, value);
            }
            //15.存到list集合中
            list.add(t);
        }
        JdbcUtil.close(connection, preparedStatement, resultSet);
        //如果list不为空，就返回list， 如果为空就返回null
        return list.size() != 0 ? list : null;
    }
}
