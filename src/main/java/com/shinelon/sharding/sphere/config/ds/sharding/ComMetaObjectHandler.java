package com.shinelon.sharding.sphere.config.ds.sharding;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/***
 * ComMetaObjectHandler.java
 *
 * @author shinelon
 *
 * @date 2019年02月22日
 *
 */
@Component
public class ComMetaObjectHandler implements MetaObjectHandler {
//    private static final Logger logger = LoggerFactory.getLogger(ComMetaObjectHandler.class);

    private static final String COLUMN_UPDATE_DATE = "updateTime";
    private static final String COLUMN_CREATE_DATE = "createTime";
    private static final String COLUMN_STATUS = "status";

    @Override
    public void insertFill(MetaObject metaObject) {
        Object status = getFieldValByName(COLUMN_STATUS, metaObject);
        if (status == null) {
            setInsertFieldValByName(COLUMN_STATUS, 1, metaObject);
        }
        Object createDate = getFieldValByName(COLUMN_CREATE_DATE, metaObject);
        if (createDate == null) {
            setInsertFieldValByName(COLUMN_CREATE_DATE, new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateDate = getFieldValByName(COLUMN_UPDATE_DATE, metaObject);
        if (updateDate == null) {
            setUpdateFieldValByName(COLUMN_UPDATE_DATE, new Date(), metaObject);
        }
    }

}
