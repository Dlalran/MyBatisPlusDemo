package priv.howard.mybatisplusdemo.generator.code.service.impl;

import priv.howard.mybatisplusdemo.generator.code.entity.Student;
import priv.howard.mybatisplusdemo.generator.code.mapper.StudentMapper;
import priv.howard.mybatisplusdemo.generator.code.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author howard
 * @since 2019-12-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
