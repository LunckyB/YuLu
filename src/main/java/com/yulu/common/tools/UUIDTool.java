package com.yulu.common.tools;

import java.util.UUID;

public class UUIDTool {
    /**
     * 获取uuid
     * @return
     */
    public static String fastSimpleUUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
