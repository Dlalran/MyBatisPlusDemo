package priv.howard.mybatisplusdemo.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import priv.howard.mybatisplusdemo.injector.methods.DeleteAllStudents;

import java.util.List;

public class MySqlInjector extends AbstractSqlInjector {
    /**
     * @Description 自定义SQL方法注入器，实现AbstractInjector类，还要在Spring的MyBatis配置中配置该注入器
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        /**
         * 将默认的和自定义的SQL方法注入SQL方法列表中
         */

//        获得默认（MP自带）的SQl方法
        List<AbstractMethod> methodList = new DefaultSqlInjector().getMethodList(mapperClass);
//        添加自定义SQL方法
        methodList.add(new DeleteAllStudents());
        return methodList;
    }
}
