package com.mybatisplus;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mybatisplus.mapper.EmployeeMapper;
import com.mybatisplus.pojo.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = context.getBean("employeeMapper", EmployeeMapper.class);

    @Test
    public void testEnvironment() throws SQLException {
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testCommonInsert() {
        Employee employee = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@qq.com");
        employee.setAge(28);
        employee.setGender(1);

        Integer row = employeeMapper.insertAllColumn(employee);
        System.out.println("row:" + row);
        Integer id = employee.getId();
        System.out.println("id:" + id);
    }


    @Test
    public void testCommonUpdateBy() {
        Employee employee = new Employee();
        employee.setId(10);
        employee.setLastName("小泽老师");
        employee.setEmail("xz@sina.com");
        employee.setGender(0);
        Integer count = employeeMapper.updateById(employee);
        System.out.println("count:" + count);
    }

    @Test
    public void testCommonSelect() {
        List<Employee> employees = employeeMapper.selectPage(new Page<>(3, 2), null);
        employees.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void testCommonDelete() {
        Integer result = employeeMapper.deleteById(11);
        System.out.println("result:" + result);
    }

    @Test
    public void testEntityWrapperSelect() {
        List<Employee> emps = employeeMapper.selectPage(
                new Page<Employee>(1, 2),
                Condition.create()
                        .between("age", 18, 50)
                        .eq("gender", "1")
                        .eq("last_name", "Tom")
        );
        System.out.println("-----------------------------");
        emps.forEach(employee -> System.out.println(employee));
    }

    @Test
    public void testEntityWrapperUpdate() {
        Employee employee = new Employee();
        employee.setLastName("苍老师");
        employee.setEmail("cls@sina.com");
        employee.setGender(0);

        Integer count = employeeMapper.update(employee, new EntityWrapper<Employee>()
                .eq("last_name", "Tom")
                .lt("age", 44)
        );
        System.out.println("----------------------------");
        System.out.println("count:" + count);
    }

    @Test
    public void testEntityWrapperDelete() {
        Object[] objects = {"10", "11"};
        Integer count = employeeMapper.delete(new EntityWrapper<Employee>()
                .in("id", objects)
        );
        System.out.println("--------------------------------");
        System.out.println("已删除的数量：" + count);
    }

    //    ActiveRecord测试
    @Test
    public void testARPage() {
        Employee employee = new Employee();
        Page<Employee> page = employee.selectPage(new Page<Employee>(1, 1),
                new EntityWrapper<Employee>().like("last_name", "老"));
        List<Employee> records = page.getRecords();
        records.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void testARDelete() {
        Employee employee = new Employee();
        boolean delete = employee.delete(new EntityWrapper<Employee>().like("last_name", "小"));
        System.out.println("已删除的数量：" + delete);
    }

    @Test
    public void testARSelect() {
        Employee employee = new Employee();
        int count = employee.selectCount(new EntityWrapper<Employee>().eq("gender", 0));
        List<Employee> employees = employee.selectAll();
        System.out.println("查询到的数据条数：" + count);
        employees.forEach(employee1 -> System.out.println(employee1));
    }

    @Test
    public void testARInsert() {
        Employee employee = new Employee();
        employee.setLastName("宋老湿");
        employee.setEmail("sls@atguigu.com");
        employee.setGender(1);
        employee.setAge(36);

        boolean flag = employee.insert();
        System.out.println("插入是否成功：" + flag);
    }


    @Test
    public void testARUpdate() {
        Employee employee = new Employee();
        employee.setId(12);
        employee.setLastName("宋老湿");
        employee.setEmail("sls@atguigu.com");
        employee.setGender(1);
        employee.setAge(40);

        boolean flag = employee.updateById();
        System.out.println("result:" + flag);
    }

}
