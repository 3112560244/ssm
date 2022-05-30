package com.qx.controller;

import com.qx.domain.Department;
import com.qx.domain.Employee;
import com.qx.domain.Login;
import com.qx.service.DepartmentService;
import com.qx.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService empService;
    @Autowired
    private DepartmentService deptService;
    public EmployeeService getEmpService() {
        return empService;
    }
    public void setEmpService(EmployeeService empService) {
        this.empService = empService;
    }
    public DepartmentService getDeptService() {
        return deptService;
    }
    public void setDeptService(DepartmentService deptService) {
        this.deptService = deptService;
    }
    /**
     * 功能：处理用户的GET方式的"/emps"请求，把所有的员工信息在emps.jsp页面进行显示
     * @param map 把key-value的键值对数据信息存储到请求域中，并在页面上获取显示
     * @return 返回逻辑视图名称
     */
    @RequestMapping(value="/emps",method=RequestMethod.GET)
    public String getEmps(Map<String,Object> map) {
        //调用服务层的查询所有员工的业务方法，并设置到Map模型中来
        map.put("emps", empService.findAllEmps());
        //在控制台输出测试
        System.out.println(empService.findAllEmps().size());
        //返回逻辑视图名称
        return "/emp/emps";
    }

    @RequestMapping(value = "/emp/findId",method = RequestMethod.GET)
    public String findId(String id,Map<String,Object> map){
        List<Employee> list = new ArrayList<>();
        Employee employee = empService.findEmpById(id);
        if(employee!=null)
            list.add(employee);
        map.put("emps", list);
        return "/emp/emps";
    }
    /**
     * 功能：处理用户的点击添加新员工的超链接/emp请求，并给客户显示添加页面
     * @param map 用于保存模型数据，并设置到请求域，用于页面部门数据显示
     * @return 返回逻辑视图名称
     */
    @RequestMapping(value="/emp/add" ,method=RequestMethod.GET)
    public String addEmp(Map<String,Object> map) {
        //获取所有的部门信息，并保存到模型域中
        map.put("depts", deptService.findAllDepts());
        //构造单选框中的显示数据，对应与表单<form:radiobuttons path="gender" items="${sexMap }"/>
        HashMap<Boolean,String> sexMap=new HashMap<Boolean,String>();
        sexMap.put(true, "男");
        sexMap.put(false, "女");
        map.put("sexMap", sexMap);
        //构造一个空的员工对象，目的是为了添加页面中的<form:form  modelAttribute="emp" >,就不会报空指针异常了
        Employee emp=new Employee();
        emp.setDept(new Department());
        map.put("employee", emp);
        return "/emp/add";
    }
    /**
     * 功能：完成新员工信息的校验与添加
     * @param employee 用于接收封装表单的员工数据（数据绑定、类型转化与格式化）
     * @param bindingResult 用来保存校验错误的字段消息
     * @param map 存储模型中的数据
     * @return 返回逻辑视图
     */
    @RequestMapping(value="/emp/add",method=RequestMethod.POST)
    public String addEmp(@Valid @ModelAttribute("employee")Employee employee, BindingResult bindingResult, Map<String,Object> map, HttpServletRequest request) {
        //如果校验失败则转发到添加页面
        if(bindingResult.getFieldErrorCount()>0) {
            System.out.println("校验失败！");
            //构造单选框中的显示数据，对应与表单<form:radiobuttons path="gender" items="${sexMap }"/>
            HashMap<Boolean,String> sexMap=new HashMap<Boolean,String>();
            sexMap.put(true, "男");
            sexMap.put(false, "女");
            map.put("sexMap", sexMap);
            //获取所有的部门信息，并保存到模型域中
            map.put("depts", deptService.findAllDepts());
            return "emp/add";
        }
        String filename = employee.getMultipartFile().getOriginalFilename();

        String realPath = request.getServletContext().getRealPath("/img");

        File targetFile = new File(realPath,filename);

        try {
            employee.getMultipartFile().transferTo(targetFile);
        }catch (Exception e){
            System.out.println("文件上传失败!");
            e.printStackTrace();
            return "emp/add";
        }
        System.out.println("文件上传成功");

        System.out.println(filename);
        employee.setImg(employee.getMultipartFile().getOriginalFilename());
        //调用服务层的业务方法，完成新员工信息的添加(注册)功能
        empService.addEmp(employee);
        //System.out.println(emp.getDept().getId());
        return "redirect:/emps";
    }
    /**
     * 功能：当用户点击修改员工的超链接时，把修改的页面给客户展现
     * @param id  要修改员工的编号
     * @param model  保存模型中数据
     * @return  返回逻辑视图名称
     */
    @RequestMapping(value="/emp/{id}")
    public String updateEmp(@PathVariable("id")String id,Model model) {
        System.out.println("修改"+id);
        //查询出该待修改的员工对象
        Employee emp=empService.findEmpById(id);
        //把该员工对象保存到模型中
        model.addAttribute("employee", emp);
        //查询出所有的部门信息并保存在模型中，目的是在修改页面中显示
        model.addAttribute("depts", deptService.findAllDepts());
        //构造一个性别的单选框，并保存到模型中
        HashMap<Boolean,String> sexMap=new HashMap<Boolean,String>();
        sexMap.put(true, "男");
        sexMap.put(false, "女");
        model.addAttribute("sexMap", sexMap);
        return "emp/update";
    }
    /**
     * 功能：完成员工信息的修改操作
     * @param employee  用于接收修改后的员工信息封装
     * @param bindingResult   校验
     * @param map   保存模型数据
     * @return  逻辑视图名称或重定向的视图名称
     */
    @RequestMapping(value="/emp/update",method=RequestMethod.POST)
    public String updateEmp(@Valid @ModelAttribute("employee")Employee employee,BindingResult bindingResult,Map<String,Object>map) {
        //如果校验失败则转发到添加页面
        if(bindingResult.getFieldErrorCount()>0) {
            System.out.println("校验失败！");
            //构造单选框中的显示数据，对应与表单<form:radiobuttons path="gender" items="${sexMap }"/>
            HashMap<Boolean,String> sexMap=new HashMap<Boolean,String>();
            sexMap.put(true, "男");
            sexMap.put(false, "女");
            map.put("sexMap", sexMap);
            //获取所有的部门信息，并保存到模型域中
            map.put("depts", deptService.findAllDepts());
            map.put("employee", employee);
            return "emp/update";
        }
        System.out.println(employee);
        //调用服务层的业务方法，完成的修改功能
        empService.updateEmp(employee);
        //System.out.println(emp.getDept().getId());
        return "redirect:/emps";
    }
    /**
     * 功能：完成员工信息的删除功能(通过员工编号来删除)
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value="/delemp/{id}",method=RequestMethod.GET)
    public String deleteEmp(@PathVariable("id")String id,Map<String,Object> map) {
        System.out.println("删除"+id);
        empService.deleteEmpById(id);
        return "redirect:/emps";
    }







    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("login",new Login());
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("login") Login login, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            System.out.println("表单效验失败");
            return "login";
        }

        if("admin".equals(login.getName()) && "admin".equals(login.getPwd())){
            session.setAttribute("login",login);
            return "redirect:/emps";
        }else{
            System.out.println("用户名或密码错误!");
            model.addAttribute("error","用户名或密码错误");
            return "login";
        }



    }




}
