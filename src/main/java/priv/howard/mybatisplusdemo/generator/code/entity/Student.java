package priv.howard.mybatisplusdemo.generator.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author howard
 * @since 2019-12-03
 */
//通过Lombok的注解@Data给实体类自动添加getter、setter、toString、hashcode等
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Student extends Model {

    private static final long serialVersionUID=1L;

    @TableId(value = "stu_no", type = IdType.AUTO)
    private Integer stuNo;

    private String stuName;

    private Integer stuAge;


}
