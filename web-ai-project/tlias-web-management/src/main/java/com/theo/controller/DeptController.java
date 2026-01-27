package com.theo.controller;

import com.theo.pojo.Dept;
import com.theo.pojo.Result;

import com.theo.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     **/
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        List<Dept> deptlist=deptService.findAll();

        return Result.success(deptlist);
    }
    /**
     * 删除部门:方式一 @HttpServletRequest
     **/
   /* @DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String id=request.getParameter("id");
        //将id的类型转换为Integer
        Integer deptid=Integer.parseInt(id);
        System.out.println("删除部门"+deptid);
        return Result.success();
    }
*/
    /**
     * 删除部门:方式二 @RequestParam
     * 注意事项：一旦使用了@RequestParam注解，就表示这个参数是必须传递的，否则会报错
     **/
   /* @DeleteMapping("/depts")
    public Result delete(@RequestParam("id",required=false) Integer deptid){
        System.out.println("删除部门"+deptid);
        return Result.success();
    }*/

    /**
     * 删除部门:方式三 省略@RequestParam(前端传递的参数名称和形参名称一致)[推荐]
     **/
    @DeleteMapping
    public Result delete(@RequestParam("id") Integer id){
        //System.out.println("删除部门"+id);\
        log.info("删除部门{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
    * 添加部门
    * */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("添加部门"+dept);
        log.info("添加部门{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*
    * 根据id查询部门
    * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        //System.out.println("根据id查询部门" + id);
        log.info("根据id查询部门{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /*
    * 根据id修改部门
    * */
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门"+dept);
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
