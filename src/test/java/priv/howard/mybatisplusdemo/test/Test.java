package priv.howard.mybatisplusdemo.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import priv.howard.mybatisplusdemo.entity.Student;
import priv.howard.mybatisplusdemo.mapper.AnnotationMapper;
import priv.howard.mybatisplusdemo.mapper.StudentMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void testInsert(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        可以将Bean类型作为参数传给getBean来获得指定类型的Bean
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student("howard", 21);
        int result = studentMapper.insert(student);
        System.out.println(result);
//        MyBatisPlus会将自增生成的主键返回到对象中
        System.out.println(student);
//      MyBatisPlus不需要commit
    }

    public static void testDelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
//        根据主键删除
        int result = studentMapper.deleteById(1);
        System.out.println(result);
    }

//    测试批量删除
    public static void testBatchDelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

        List<Integer> stuNos = new ArrayList<>();
        stuNos.add(2);
        stuNos.add(3);
//        批量删除
        int result = studentMapper.deleteBatchIds(stuNos);
        System.out.println(result);
    }

//    测试通过Map传条件删除
    public static void testMapDelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        通过Map传条件参数
        Map<String, Object> map = new HashMap<String, Object>();
//        key为字段名，value为字段值，多个条件间为AND关系
        map.put("stu_name", "howard");
        map.put("stu_age", 21);
        int result = studentMapper.deleteByMap(map);
        System.out.println(result);
    }

    public static void testUpdate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        根据传入对象的主键属性查询行，并将字段设为其他属性
        Student student = new Student(5, "howard", 20);
        int result = studentMapper.updateById(student);
        studentMapper.
        System.out.println(result);
    }

    public static void testQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        根据id查询
//        Student student = studentMapper.selectById(5);
//        查询全部
        List<Student> students = studentMapper.selectList(null);
        System.out.println(students);
    }

    public static void testBatchQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        根据List集合中的id值批量查询
        List<Integer> stuNos = new ArrayList<>();
        stuNos.add(5);
        stuNos.add(6);
        List<Student> students = studentMapper.selectBatchIds(stuNos);
        System.out.println(students);
    }

    public static void testMapQuery() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        根据Map中的条件查询
        Map<String, Object> map = new HashMap<>();
        map.put("stu_name", "howard");
        map.put("stu_age", 21);
        List<Student> students = studentMapper.selectByMap(map);
        System.out.println(students);
    }

//    测试Wrapper
    public static void testQueryWrapper() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        通过QueryWrapper指定查询条件，wrapper相当于where语句
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

//        第一个参数一般是列名，后面是对应的条件值，且支持链式写法，条件间关系为AND
//        wrapper.between("stu_no", 5, 6).gt("stu_age", 20);
//        如果条件间关系需要为OR，则链式条件间增加.or()
//        wrapper.between("stu_no", 5, 6).or().gt("stu_age", 20);
//        如果需要括号指定优先级，如A or (B and C)，使用or()的Lambda表达式形式，将括号内的条件写在or内部->的右侧（实现侧）
//        wrapper.between("stu_no", 5, 6).or(i -> i.eq("stu_name", "howard").eq("stu_age", 21));

//        注意：like形式条件不需要添加%%
//        wrapper.like("stu_name", "war");

//        通过orderByAsc以增序对结果进行排序
        wrapper.orderByAsc("stu_age");
//        也可以通过last强制在SQL语句最后添加指定排序条件，注意在开头添加空格
//        wrapper.last(" order by stu_age asc");

        List<Student> students = studentMapper.selectList(wrapper);
//        Lambda遍历集合元素并打印
        students.stream().forEach(student -> System.out.println(student));
    }

    public static void testUpdateWrapper() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        通过UpdateWrapper指定修改（update）的条件
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("stu_no", 6);
        Student student = new Student(6, "john", 22);
        int result = studentMapper.update(student, wrapper);
        System.out.println(result);

    }

//    测试AR(Active Recorder)模式
    public static void testAR() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        不需要通过Mapper进行数据库操作

//        实体类继承抽象类Model即可实现AR模式，通过实体类来进行CRUD操作
        /*Student student = new Student(8, "william", 24);
        boolean result = student.insert();*/

//        通过实体类的id进行查询
        /*Student student = new Student();
        student.setStuNo(5);
        Student result = student.selectById();*/

//        通过实体类中的id进行删除
        /*Student student = new Student();
        student.setStuNo(8);
        boolean result = student.deleteById();*/
//        通过单独指定id删除
        /*boolean result = new Student().deleteById(8);*/

//        将实体类id指定的行的其他列修改为实体类其他属性
        /*Student student = new Student(8, "hazard", 24);
        boolean result = student.updateById();*/

//        通过Wrapper添加查询条件
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
//        Lambda形式设置条件，在wrapper.lambda()后链式
//
//        添加条件
//        注意：条件方法中不指定字段名，而是引用实体类中进行条件判断属性的get方法
        wrapper.lambda().eq(Student::getStuName, "howard");
//        相当于以下语句
//        wrapper.eq("stu_name", "howard");

        List<Student> result = new Student().selectList(wrapper);

        System.out.println(result);
    }

//    测试MyBatisPlus分页功能
    public static void testPage() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        传入Page对象进行查询，传入泛型类型为实体类，构造方法传入参数分别为当前页和页大小
        IPage<Student> page = studentMapper.selectPage(new Page<>(2,2), null);
//        通过getRecorders获取当前页数据集合
        List<Student> students = page.getRecords();
        System.out.println("当前页数据：" + students);
        System.out.println("当前页页码：" + page.getCurrent());
        System.out.println("当前页大小：" + page.getSize());
        System.out.println("总数据量：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
    }

//    测试SQL阻断器
    public static void testDeleteAll() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        对全表进行删除操作，会被阻断器禁止，会报错:Prohibition of full table deletion
        int result = studentMapper.delete(null);
        System.out.println(result);
    }

//    测试乐观锁
    public static void testVersion() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

        Student student = new Student(5, "howard", 21);
//        设置需要修改的数据现版本应该为1，版本错误将不执行修改，成功修改后版本号会+1
        student.setVersion(1);

        int result = studentMapper.updateById(student);
        System.out.println(result);

    }

//    测试自定义SQL注入器、SQL方法
    public static void testSqlInjector() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        使用自定义SQL方法
        int result = studentMapper.deleteAllStudents();
        System.out.println(result);
    }

    public static void testLogicDelete() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

//        使用MP自带的删除、查找方法都会附加逻辑删除检查与操作，查询被逻辑删除的数据可通过手写SQL

//        实体类中指定了LogicDelete字段，删除时只会将该字段设为指定的值（默认1）
        int result = studentMapper.deleteById(8);
        System.out.println(result);
//        查询时只查询LogicDelete字段为未删除值（默认0）的行
        Student student = studentMapper.selectById(8);
        System.out.println(student);
    }

//    测试自动填充
//    注意:该功能测试失败,没有效果
    public static void testAutoFill() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);

        Student student = new Student();
        student.setStuAge(20);
//        不指定stuAge,使用自动填充
        int result = studentMapper.insert(student);
        System.out.println(result);
    }

//    测试MyBatis注解开发
    public static void testAnnotation() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationMapper annotationMapper = context.getBean("annotationMapper", AnnotationMapper.class);

//        传入参数查询
        /*Student result = annotationMapper.queryStudentById(5);*/
//        传入Wrapper查询
        /*QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_no", 5);
        Student result = annotationMapper.queryStudentWithWrapper(wrapper);*/
//        通过@Results映射查询结果
        /*List<Student> result = annotationMapper.queryStudentsByAge(22);*/

//        新增行
        /*Student student = new Student(1, "kate", 19);
        int result = annotationMapper.addStudent(student);*/
//        通过自增主键新增行
        /*Student student = new Student();
        student.setStuName("pat");
        student.setStuAge(19);
        int result = annotationMapper.addStudentWithAutoId(student);
        System.out.println(student);*/

//        修改
        /*Student student = new Student(1, "kate", 19);
        int result = annotationMapper.updateStudentById(student);*/
//        Wrapper修改
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("stu_no", 5);
        Student student = new Student();
        student.setStuAge(20);
        student.setStuName("howard");
        int result = annotationMapper.updateStudentWithWrapper(wrapper, student);

//        删除
        /*int result = annotationMapper.deleteStudentById(1);*/

        System.out.println(result);

    }
    public static void main(String[] args) throws SQLException {
//        testInsert();
//        testDelete();
//        testBatchDelete();
//        testMapDelete();
//        testUpdate();
//        testQuery();
//        testBatchQuery();
//        testMapQuery();
//        testQueryWrapper();
//        testUpdateWrapper();
//        testQueryWrapper();
//        testAR();
//        testPage();
//        testDeleteAll();
//        testVersion();
//        testSqlInjector();
//        testLogicDelete();
//        testAutoFill();
        testAnnotation();
    }
}
