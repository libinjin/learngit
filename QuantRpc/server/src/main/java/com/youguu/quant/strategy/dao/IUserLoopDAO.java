package com.youguu.quant.strategy.dao;

import com.youguu.quant.strategy.pojo.UserLoop;

/**
 * Created by SomeBody on 2016/8/25.
 */
public interface IUserLoopDAO {
    public int saveUserLoop(UserLoop userLoop);

    public int incrLoopNum(int userId,int type);

    public int incrFinishNum(int userId,int type);

    public UserLoop getUserLoop(int userId,int type);

    public int deleteAllUserLoop();
}
