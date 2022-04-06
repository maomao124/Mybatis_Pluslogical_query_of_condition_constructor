package mapper;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import data.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：Mybatis_Plus条件构造器之逻辑查询
 * Package(包名): mapper
 * Class(测试类名): StudentMapperTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/6
 * Time(创建时间)： 22:13
 * Version(版本): 1.0
 * Description(描述)： 测试类
 * or
 * 拼接 OR
 * 主动调用 or 表示紧接着下一个方法不是用 and 连接!(不调用 or 则默认为使用 and 连接
 * and
 * AND 嵌套
 */


class StudentMapperTest
{
    /**
     * Or.
     *
     * @throws IOException the io exception
     */
    @Test
    public void or() throws IOException
    {
        String resource = "mybatis-config.xml";
        //读取配置文件mybatis-config.xml
        InputStream config = Resources.getResourceAsStream(resource);
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_no", 1001L)
                .or().eq("class_no", 1003L)
                .or().eq("class_no", 1005L);

        List<Student> list = studentMapper.selectList(queryWrapper);
        for (Student student : list)
        {
            System.out.print(student);
        }
        System.out.println("数量：" + list.size());

        sqlSession.close();
    }

    /**
     * And.
     *
     * @throws IOException the io exception
     */
    @Test
    public void and() throws IOException
    {
        String resource = "mybatis-config.xml";
        //读取配置文件mybatis-config.xml
        InputStream config = Resources.getResourceAsStream(resource);
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_no", 1001L)
                .and(i -> i.eq("sex", "女"));

        List<Student> list = studentMapper.selectList(queryWrapper);
        for (Student student : list)
        {
            System.out.print(student);
        }
        System.out.println("数量：" + list.size());

        sqlSession.close();
    }
}