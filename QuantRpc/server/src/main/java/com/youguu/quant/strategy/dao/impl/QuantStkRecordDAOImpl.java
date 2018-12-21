package com.youguu.quant.strategy.dao.impl;

import com.youguu.quant.base.QuantDAO;
import com.youguu.quant.strategy.dao.IQuantStkRecordDAO;
import com.youguu.quant.strategy.pojo.QuantStkRecord;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyj on 2016/9/28.
 */
@Repository
public class QuantStkRecordDAOImpl extends QuantDAO<QuantStkRecord> implements IQuantStkRecordDAO {
    @Override
    public int saveQuantStkRecord(QuantStkRecord qsr) {
        return insert(qsr);
    }

    @Override
    public int ifExists(int rid, int sid) {
        Map<String,Integer> map = new HashMap<>();
        map.put("rid",rid);
        map.put("sid",sid);
        return findUniqueBy("ifExists",map);
    }
}
