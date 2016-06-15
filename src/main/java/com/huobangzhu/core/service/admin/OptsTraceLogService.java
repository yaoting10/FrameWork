package com.huobangzhu.core.service.admin;

import com.huobangzhu.core.domain.admin.OptsTraceLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by penghongqin on 14-9-1.
 */
public interface OptsTraceLogService {

    OptsTraceLog save(OptsTraceLog optsTraceLog);

    public Page<OptsTraceLog> findOptsTraceLogByConditions(String username, Long begDate, Long endDate, String operation, Pageable pageable);


}
