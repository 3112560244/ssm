package com.qx.dao;

import com.qx.domain.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentMapper {
    /*功能：保存一个部门的信息*/
    public void save(Department dept);
    /*功能：通过部门编号来查询一个部门的信息*/
    public Department findById(String id);
    /*功能：查询所有的部门信息*/
    public List<Department> findAll();

}

