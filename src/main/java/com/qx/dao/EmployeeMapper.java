package com.qx.dao;

import com.qx.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeMapper {
    /*功能：保存一个员工信息*/
    public void save(Employee emp);
    /*功能：通过员工编号来查询一个员工的信息，同时也包含该员工所属的部门信息*/
    public Employee findById(String id);
    /*功能：通过部门编号来查询该部门下有哪些员工*/
    public List<Employee> findEmpsByDeptId(String deptId);
    /*功能：查询所有的员工信息，同时也包括每个员工所在的部门信息*/
    public List<Employee> findAll();
    /*功能：修改一个员工的信息*/
    public void update(Employee emp);
    /*功能：通过员工编号来删除一个员工*/
    public void deleteById(String empId);
}

