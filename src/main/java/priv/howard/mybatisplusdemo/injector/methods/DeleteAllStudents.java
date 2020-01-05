package priv.howard.mybatisplusdemo.injector.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteAllStudents extends AbstractMethod {
    /**
     * @Description 自定义的SQl方法，继承AbstractMethod类
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
//        指定SQL方法的基本种类（如删除、修改、增加等）
        SqlMethod sqlMethod = SqlMethod.DELETE;
//        SQL内容
        String sql = "delete from student";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
//        在第二个参数指定SQL对应的id值
        return this.addDeleteMappedStatement(mapperClass, "deleteAllStudents", sqlSource);
    }
}
