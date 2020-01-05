package priv.howard.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.howard.mybatisplusdemo.entity.Student;

public interface StudentMapper extends BaseMapper<Student> {
    /**
     * @Description MyBatisPlus下的Mapper接口
     * 不需要XML映射文件，需要继承BaseMapper接口，并将泛型指定为操作的实体对象，即可实现基本的CRUD操作
     */

//    自定义SQL方法，方法体类在injector.methods中定义
    int deleteAllStudents();
}
