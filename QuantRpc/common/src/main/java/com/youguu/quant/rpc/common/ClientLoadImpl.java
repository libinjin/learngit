package com.youguu.quant.rpc.common;

import com.youguu.core.util.rpc.multipex.IClientLoad;

import java.util.HashMap;
import java.util.Map;

public class ClientLoadImpl implements IClientLoad {

    @Override
    public Map<String, Class<?>> loadClient() {
        Map<String, Class<?>> map = new HashMap<String, Class<?>>();
        for (ClientMultiplexProp client : ClientMultiplexProp.values()) {
            map.put(client.getName(), client.getClient());
        }
        return map;
    }

    @Override
    public Map<String, Class<?>> loadAsyncClient() {
        return null;
    }

}
