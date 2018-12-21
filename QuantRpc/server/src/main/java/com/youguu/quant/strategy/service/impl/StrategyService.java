package com.youguu.quant.strategy.service.impl;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.dao.IStrategyExtendDAO;
import com.youguu.quant.strategy.dao.impl.StrategyDAO;
import com.youguu.quant.strategy.pojo.Strategy;
import com.youguu.quant.strategy.pojo.StrategyExtend;
import com.youguu.quant.strategy.pojo.StrategyRunLog;
import com.youguu.quant.strategy.service.IStrategyRunLogService;
import com.youguu.quant.strategy.service.IStrategyService;
import com.youguu.quant.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Service("strategyService")
public class StrategyService implements IStrategyService {

    @Resource
    private StrategyDAO strategyDAO;

    @Resource
    private IStrategyExtendDAO strategyExtendDAO;

    @Resource
    private IStrategyRunLogService strategyRunLogService;

    @Transactional("quantTradeTx")
    @Override
    public int saveStrategy(Strategy strategy) {
        strategyDAO.saveStrategy(strategy);

        int strategyId = strategy.getId();

        StrategyExtend se = new StrategyExtend();
        se.setStrategyId(strategyId);
        se.setUpdateTime(new Date());
        strategyExtendDAO.saveStrategyExtend(se);

        return strategyId;
    }

    @Override
    public int deleteStrategy(int id) {
        return strategyDAO.deleteStrategy(id);
    }

    @Override
    public int updateStrategy(Strategy strategy) {
        return strategyDAO.updateStrategy(strategy);
    }

    @Override
    public Strategy getStrategy(int id) {
        return strategyDAO.getStrategy(id);
    }

    @Override
    public PageHolder<Strategy> queryStrategyByPage(Map<String, Object> map, int pageIndex, int pageSize) {
        return strategyDAO.queryStrategyByPage(map, pageIndex, pageSize);
    }

    @Transactional("quantTradeTx")
    @Override
    public int updateStrategyStatus(int id, int status) {
        strategyDAO.updateStrategyStatus(id, status);
        StrategyRunLog srl = new StrategyRunLog();
        srl.setStatus(status);
        srl.setStrategyId(id);
        srl.setCreateTime(new Date());
        strategyRunLogService.saveStrategyRunLog(srl);
        return 1;
    }

    @Override
    public List<Strategy> findAll() {
        return strategyDAO.findAll();
    }

    @Override
    public Strategy findStrategyByCategoryId(String categoryId) {
        return strategyDAO.findStrategyByCategoryId(categoryId);
    }

    @Override
    public List<Integer> findAllRunStrategyId() {
        return strategyDAO.findAllRunStrategyId();
    }

    @Override
    public boolean isExitsFile(int strategyId) {
        return FileUtil.isExistsStrategyFile(strategyId);
    }

    @Override
    public boolean clearStrategyData(int strategyId) {
        return FileUtil.backupWhenLoop(strategyId);
    }
}
