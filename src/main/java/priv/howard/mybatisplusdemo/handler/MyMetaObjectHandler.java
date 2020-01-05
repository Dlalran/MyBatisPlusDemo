package priv.howard.mybatisplusdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * @Description 自定义字段填充处理器，定义插入和更新时自动填充的字段值
     */
//    插入
    @Override
    public void insertFill(MetaObject metaObject) {
//        指定需要自动填充的属性名和属性值
        this.setInsertFieldValByName("stuName", "zs", metaObject);
    }
//    更新
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("stuName", "zs", metaObject);
    }
}
