package com.huobangzhu.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Created with ECCS
 * User : Ting.Yao
 * Date : 2015/3/22.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCode{

    public static final int
            SUCCESSFUL = 0x0000,
            NO_SUCH_USER = 0x0001,
            NO_SUCH_AREA = 0x0002,
            DATE_FORMAT_ERRO=0X0003,


            UNDEFINDED = 0xffff;
}
