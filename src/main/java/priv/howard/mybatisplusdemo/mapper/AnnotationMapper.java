package priv.howard.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;
import priv.howard.mybatisplusdemo.entity.Student;

import java.util.List;

public interface AnnotationMapper extends BaseMapper<Student> {
    /**
     * @Description 测试注解形式的自定义SQL
     */

    /**
     * 查询
     */
//    传入参数查询SQL
    @Select("select * from student where stu_no = #{stuNo} ")
    Student queryStudentById(int stuNo);

//    使用Wrapper查询SQL
//    在SQL语句中条件部分处直接拼接${ew.customSqlSegment}，不用where
    @Select("select * from student ${ew.customSqlSegment}")
//    传入参数Wrapper<T>，并且通过@Param注解声明参数名为Constants.WRAPPER(即"ew")
    Student queryStudentWithWrapper(@Param(Constants.WRAPPER) Wrapper<Student> wrapper);

//    通过使用注解@Results（即XML形式的ResultMap）映射查询结果，可以指定id来提供给其他SQL方法使用
//    每一条映射通过@Result指定，property为属性，column为字段（与ResultMap相同）
    @Results(id = "resultMap", value = {
            @Result(property = "stuNo", column = "stu_no"),
            @Result(property = "stuName", column = "stu_name"),
            @Result(property = "stuAge", column = "stu_age")
    })
    @Select("select * from student where stu_age = #{stuAge}")
    List<Student> queryStudentsByAge(int stuAge);

    /**
     * 增加
     */
//    传入对象增加行
    @Insert("insert into student values (#{stuNo}, #{stuName}, #{stuAge}, 1, 0)")
    int addStudent(Student student);

//    主键自增并返回主键的增加操作，SQL语句中不传入主键值，注意指定插入列
    @Insert("insert into student(stu_name, stu_age, version, logic_delete) values (#{stuName}, #{stuAge}, 1, 0)")
//    通过@Options注解添加XML形式中在标签中的参数值，指定参数useGeneratorKeys=true使用数据库自增策略，keyProperty指定主键属性
    @Options(useGeneratedKeys = true, keyProperty = "stuNo")
    int addStudentWithAutoId(Student student);

    /**
     * 修改和删除（和XML形式相同）
     */
    @Update("update student set stu_name = #{stuName}, stu_age = #{stuAge} where stu_no = #{stuNo}")
    int updateStudentById(Student student);

//    通过Wrapper传送修改的条件，注意多个参数时每个参数都要通过@Param指定参数名
    @Update("update student set stu_name = #{stu.stuName}, stu_age = #{stu.stuAge} ${ew.customSqlSegment}")
    int updateStudentWithWrapper(@Param(Constants.WRAPPER) Wrapper<Student> wrapper, @Param("stu") Student student);

    @Delete("delete from student where stu_no = #{stuNo}")
    int deleteStudentById(int stuNo);
}
