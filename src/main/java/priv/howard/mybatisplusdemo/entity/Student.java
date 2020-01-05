package priv.howard.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

//@TableName的value指定该实体类对应的表名，不指定则默认为类名
@TableName("student")
//使用Oracle的自增序列作为主键生成策略时，使用注解@KeySequence，并指定序列名value，主键类型clazz
//@KeySequence(value = "seq_stu", clazz = Integer.class)
public class Student extends Model<Student> {
    /**
     * @Description 实体类继承Model<T>抽象类，将实现AR(Active Recorder)模式，即对象本身封装数据库CRUD操作
     */

//    通过注解@TableId指定主键属性，参数value指定主键列名，type指定主键策略IdType.AUTO为自增(MySQL)，IdType.INPUT为手动输入或使用Oracle的序列生成
//    注意：自增依赖于数据库的自增特性，需要对主键字段设置自增
    @TableId(value = "stu_no", type = IdType.AUTO)
    private int stuNo;

//    通过注解@TableField指定非主键属性，
//    注意：不配置时默认将驼峰命名的属性名转换为下划线分隔的字段名，如:stuAge -> stu_age
//    在MyBatis配置文件中设置mapUnderscoreToCamelCase=false可以禁用
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String stuName;

//    通过@TableField的参数fill来指定在什么情况下自动填充，默认为DEFAULT不填充，还有INSERT、UPDATE、INSERT_UPDATE
//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private int stuAge;

//   对于数据库中不存在的属性，通过@TableField指定exist=false
    @TableField(exist = false)
    private String desc;

//    乐观锁需要的版本属性，添加@Version注解，用于在表更新时检查数据是否已被他人修改
    @Version
    private Integer version;

//    逻辑删除的代表值，添加注解@TableLogic，0为未删除，1为已删除，可以在MBP全局配置中配置，也可以通过注解传参
    @TableLogic
    private int logicDelete;

    public Student() {
    }

    public Student(String stuName, int stuAge) {
        this.stuName = stuName;
        this.stuAge = stuAge;
    }

    public Student(int stuNo, String stuName, int stuAge) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
    }


    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public int getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(int logicDelete) {
        this.logicDelete = logicDelete;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}
