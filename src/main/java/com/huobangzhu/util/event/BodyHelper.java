package com.huobangzhu.util.event;

/**
 * User: liuchang
 * Date: 15/3/24
 */
public final class BodyHelper {
    public static Body getEventBody(Payload payload) {
        Body body = new Body(payload);
        return body;
    }
}
