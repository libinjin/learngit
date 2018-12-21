package com.youguu.quant.strategy.service.impl;

import com.youguu.quant.base.QuantTradePlateConfig;
import com.youguu.quant.strategy.dao.IUserLoopDAO;
import com.youguu.quant.strategy.dao.IUserLoopRecordDAO;
import com.youguu.quant.strategy.pojo.DNAAcl;
import com.youguu.quant.strategy.pojo.StrategyUser;
import com.youguu.quant.strategy.pojo.UserLoop;
import com.youguu.quant.strategy.pojo.UserLoopRecord;
import com.youguu.quant.strategy.service.IStrategyUserService;
import com.youguu.quant.strategy.service.IUserLoopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SomeBody on 2016/8/25.
 */
@Service("userLoopService")
public class UserLoopService implements IUserLoopService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IUserLoopDAO userLoopDAO;
    @Resource
    private IUserLoopRecordDAO userLoopRecordDAO;

    @Resource
    private IStrategyUserService strategyUserService;

    @Override
    public int saveUserLoop(int userId,int type) {
        if (userId > 0) {
            UserLoop ul = new UserLoop();
            ul.setUserId(userId);
            ul.setLoopNum(QuantTradePlateConfig.getInstance().getTypeLoopNum(type));
            ul.setFinishNum(0);
            ul.setShareNum(0);
            ul.setType(type);
            ul.setUpdateTime(new Date());
            ul.setCreateTime(new Date());
            return userLoopDAO.saveUserLoop(ul);
        }
        return 1;
    }

    @Transactional("quantTradeTx")
    @Override
    public int incrLoopNum(int userId,int type) {
        UserLoop ul = getUserLoop(userId,type);
        if (ul == null) {
            this.saveUserLoop(userId, type);
        } else {
            //这里是为了防止用户恶意刷分享数量
            if (ul.getShareNum() >= QuantTradePlateConfig.getInstance().getTypeShareNum(type)) {
                return 0;
            }
        }
        return userLoopDAO.incrLoopNum(userId,type);
    }


    /**
     * 完成回测报告使用
     * @param userId
     * @param strategyId
     * @param stocks
     * @return
     */
    @Transactional("quantTradeTx")
    @Override
    public int incrBackFinishNum(int userId, int strategyId, String stocks) {
        //保存回测记录
        UserLoopRecord ulr = new UserLoopRecord();
        ulr.setUserId(userId);
        ulr.setStrategyId(strategyId);
        ulr.setStocks(stocks);
        ulr.setCreateTime(new Date());
        ulr.setType(UserLoop.TYPE_REPORT);
        userLoopRecordDAO.saveUserLoopRecord(ulr);

        //已购买策略不需要修改回测计数
        StrategyUser su = strategyUserService.getStrategyUser(userId, strategyId);
        if (su != null) {
            return 1;
        }

        //未购买修改回测计数
        userLoopDAO.incrFinishNum(userId,UserLoop.TYPE_REPORT);
        return 1;
    }

    @Transactional("quantTradeTx")
    @Override
    public int incDNAFinishNum(int userId, String stocks) {
        //保存回测记录
        UserLoopRecord ulr = new UserLoopRecord();
        ulr.setUserId(userId);
        ulr.setStrategyId(-1);
        ulr.setStocks(stocks);
        ulr.setCreateTime(new Date());
        ulr.setType(UserLoop.TYPE_DNA);
        userLoopRecordDAO.saveUserLoopRecord(ulr);

        List<StrategyUser> list = strategyUserService.findStrategyByUserId(userId, 1);
        if(list!=null && list.size()>0){
            return 1;
        }
        //未购买修改DNA计数
        userLoopDAO.incrFinishNum(userId,UserLoop.TYPE_DNA);
        return 1;
    }

    @Override
    public UserLoop getUserLoop(int userId,int type) {
        UserLoop ul = userLoopDAO.getUserLoop(userId,type);
        if (ul == null) {
            this.saveUserLoop(userId,type);
            return userLoopDAO.getUserLoop(userId,type);
        }
        return ul;
    }

    @Override
    public int deleteAllUserLoop() {
        return userLoopDAO.deleteAllUserLoop();
    }

    @Override
    public List<UserLoopRecord> findUserLoopList(String time) {
        return userLoopRecordDAO.findUserLoopList(time,UserLoop.TYPE_REPORT);
    }

    @Override
    public DNAAcl queryDNANum(int userId) {
        //首先查询用户是否有机器人权限
        DNAAcl acl = new DNAAcl();
        List<StrategyUser> list = strategyUserService.findStrategyByUserId(userId, 1);
        if(list!=null && list.size()>0){
            acl.setStatus(1);
            acl.setPermissionFlag(true);
            return acl;
        }

        //判断用户是否有免费权限
        UserLoop userLoop = this.getUserLoop(userId,UserLoop.TYPE_DNA);

        if(userLoop.getFinishNum() >= userLoop.getLoopNum()){//已经使用完权限
            acl.setPermissionFlag(false);
            int useNum = QuantTradePlateConfig.getInstance().getTypeShareNum(UserLoop.TYPE_DNA) + QuantTradePlateConfig.getInstance().getTypeLoopNum(UserLoop.TYPE_DNA);
            if(userLoop.getFinishNum() >= useNum){
                acl.setStatus(-3);
                acl.setDescribe(QuantTradePlateConfig.getInstance().getStr_share_end());
            }else{
                acl.setStatus(-2);
                acl.setDescribe(QuantTradePlateConfig.getInstance().getStr_share());
            }
        }else{
            acl.setPermissionFlag(true);
            if(userLoop.getFinishNum() >= QuantTradePlateConfig.getInstance().getTypeLoopNum(UserLoop.TYPE_DNA)){
                acl.setStatus(3);
            }else{
                acl.setStatus(2);
            }
        }


        return acl;
    }
}
