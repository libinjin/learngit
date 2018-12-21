package com.youguu.quant.strategy.service.impl;

import com.youguu.core.util.PageHolder;
import com.youguu.quant.strategy.dao.IStrategyCommentDAO;
import com.youguu.quant.strategy.pojo.StrategyComment;
import com.youguu.quant.strategy.service.IStrategyCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by SomeBody on 2016/8/24.
 */
@Service("strategyCommentService")
public class StrategyCommentService implements IStrategyCommentService {

    @Resource
    private IStrategyCommentDAO strategyCommentDAO;

    @Override
    public int saveStrategyComment(StrategyComment strategy) {
        return strategyCommentDAO.saveStrategyComment(strategy);
    }

    @Override
    public int deleteStrategyComment(int id) {
        return strategyCommentDAO.deleteStrategyComment(id);
    }

    @Override
    public int updateStrategyComment(StrategyComment strategy) {
        return strategyCommentDAO.updateStrategyComment(strategy);
    }

    @Override
    public StrategyComment getStrategyComment(int id) {
        return strategyCommentDAO.getStrategyComment(id);
    }

    @Override
    public PageHolder<StrategyComment> queryStrategyCommentByPage(Map<String, Object> map, int pageIndex, int pageSize) {
        return strategyCommentDAO.queryStrategyCommentByPage(map, pageIndex, pageSize);
    }

    @Override
    public List<StrategyComment> findStrategyCommentList(int strategyId, int pageIndex, int pageSize) {
        return strategyCommentDAO.findStrategyCommentList(strategyId, pageIndex, pageSize);
    }

    @Override
    public int updateCommentStatus(int id, int status) {
        return strategyCommentDAO.updateCommentStatus(id, status);
    }
}
