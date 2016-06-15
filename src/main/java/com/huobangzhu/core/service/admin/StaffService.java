package com.huobangzhu.core.service.admin;

import com.huobangzhu.core.domain.admin.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by penghongqin on 14-9-2.
 */
public interface StaffService {

    Staff save(Staff Staff);

    Staff get(Long id);

    boolean usernameExists(String username);

    Staff getByUserName(String username);

    Page<Staff> getByStaff(Staff staff, Pageable pageable);

    Page<Staff> getStaffByPage(Pageable pageable);

}
