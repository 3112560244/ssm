package com.qx.service;

import com.qx.dao.EmployeeMapper;
import com.qx.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    @Autowired
    private EmployeeMapper empDao;

    public EmployeeMapper getEmpDao() {
        return empDao;
    }
    public void setEmpDao(EmployeeMapper empDao) {
        this.empDao = empDao;
    }
    /*功能：添加一个新的员工*/
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void addEmp(Employee emp) {
        empDao.save(emp);
    }
    /*功能：通过员工编号来查询一个员工*/
    public Employee findEmpById(String empId) {

        return empDao.findById(empId);
    }
    /*功能：通过部门编号来查询该部门下的所有员工*/
    public List<Employee> findEmpsByDeptId(String deptId){
        return empDao.findEmpsByDeptId(deptId);
    }
    /*功能：查询所有的员工信息*/
    public List<Employee> findAllEmps(){
        return empDao.findAll();
    }
    /*功能：修改一个员工的信息*/
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void updateEmp(Employee emp) {
        empDao.update(emp);
    }
    /*功能：通过员工编号来删除一个员工信息*/
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void deleteEmpById(String empId) {
        empDao.deleteById(empId);
    }
}
