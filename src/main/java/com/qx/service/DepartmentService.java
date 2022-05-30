package com.qx.service;

import com.qx.dao.DepartmentMapper;
import com.qx.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT,readOnly = true)
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentDao;

    public DepartmentMapper getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentMapper departmentDao) {
        this.departmentDao = departmentDao;
    }
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void addDept(Department dept) {
        departmentDao.save(dept);
    }

    public Department findDeptById(String deptId) {
        return departmentDao.findById(deptId);
    }

    public List<Department> findAllDepts(){
        return departmentDao.findAll();
    }
}
