package com.youguu.quant.quote;

import com.youguu.quote.pojo.CurStatus;
import com.youguu.quote.rpc.client.QuoteClient;

/**
 * Created by lenovo on 2016/11/21.
 */
public class QuoteUtil {
    public static double getCurPrice(String code){
        if(code!=null){
            if(code.length()==6){
                code = com.youguu.core.util.QuoteUtil.convertTo8(code);
            }
            CurStatus cs = QuoteClient.getCurStatusByCode(code);
            return cs.getCurPrice();
        }
        return 0;
    }

    public static double getClosePrice(String code){
        if(code!=null){
            if(code.length()==6){
                code = com.youguu.core.util.QuoteUtil.convertTo8(code);
            }
            CurStatus cs = QuoteClient.getCurStatusByCode(code);
            return cs.getClosePrice();
        }
        return 0;
    }

    public static String getStockName(String code){
        if(code!=null){
            if(code.length()==6){
                code = com.youguu.core.util.QuoteUtil.convertTo8(code);
            }
            CurStatus cs = QuoteClient.getCurStatusByCode(code);
            return cs.getName();
        }
        return "";
    }

    public static CurStatus getCurStatus(String code){
        if(code!=null){
            if(code.length()==6){
                code = com.youguu.core.util.QuoteUtil.convertTo8(code);
            }
            CurStatus cs = QuoteClient.getCurStatusByCode(code);
            return cs;
        }
        return null;
    }

}
