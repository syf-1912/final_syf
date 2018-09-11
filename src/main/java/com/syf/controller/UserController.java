package com.syf.controller;

import com.syf.entity.User;
import com.syf.entity.UserDto;
import com.syf.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 展示数据
     *
     * @param page 当前页码
     * @param rows 每页显示数据量
     * @return 前台数据表格需要的数据格式
     */
    @RequestMapping("/showAll")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        //求总条数
        Integer total = userService.total();
        //获得当前页的数据
        List<User> users = userService.queryAll(page, rows);
        map.put("total", total);
        map.put("rows", users);
        return map;
    }

    /**
     * 导出全部数据
     *
     * @param page 当前页码
     * @param rows 每页显示数据量
     */
    @RequestMapping("/exportAll")
    public void exportAll(Integer page, Integer rows, HttpServletResponse response) {
        //获取工作薄对象
        Workbook workbook = userService.exportAll(page, rows);
        //导出
        derive(workbook, response);
    }

    /**
     * 自定义导出数据
     *
     * @param titles 中文标题行
     * @param params 标题对应的属性
     * @param page   当前页码
     * @param rows   每页显示数据量
     */
    @RequestMapping("/customerExport")
    public void customerExport(String titles, String params, Integer page, Integer rows, HttpServletResponse response) {
        //获取工作薄对象
        Workbook workbook = userService.customerExport(titles, params, page, rows);
        //导出
        derive(workbook, response);
    }

    /**
     * 下载导入模板
     */
    @RequestMapping("/downLoad")
    public void downLoad(HttpServletRequest request, HttpServletResponse response) {
        //获取需要下载的文件
        File file = new File(request.getServletContext().getRealPath("/") + "template/userExcel(副本).xls");
        //设置文件名
        String a = "userExcel(副本).xls";
        String newName = null;
        try {
            //将文件名进行编解码
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置导出文件名和文件格式
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        response.setContentType("application/vnd.ms-excel");
        //响应到页面
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/import")
    public void importData(MultipartFile file) {
        userService.importData(file);
    }

    /**
     * 导出数据表格
     */
    public static void derive(Workbook workbook, HttpServletResponse response) {
        //设置新文件名，防止重名
        String a = new Date().getTime() + "userExcel.xls";
        String newName = null;
        try {
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置导出文件名和文件格式
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        response.setContentType("application/vnd.ms-excel");

        //响应到页面
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看近期注册用户数量
     */
    @RequestMapping("/bar")
    public Map<String, Object> bar() {
        Map<String, Object> map = new HashMap<>();
        //创建X轴数据集合
        List<String> strs = new ArrayList<>();
        strs.add("近1周");
        strs.add("近2周");
        strs.add("近3周");
        strs.add("近1月");

        //获取对应数据
        List<Integer> users = new ArrayList<>();
        users.add(userService.queryByDate(7));
        users.add(userService.queryByDate(14));
        users.add(userService.queryByDate(21));
        users.add(userService.queryByDate(31));

        //存入map返回
        map.put("xAxis", strs);
        map.put("users", users);
        return map;
    }

    @RequestMapping("/map")
    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        List<UserDto> mans = userService.queryByProvince("男");
        List<UserDto> womens = userService.queryByProvince("女");
        map.put("man", mans);
        map.put("womens", womens);
        return map;
    }

    /*@RequestMapping("/regist")
    public void regist(User user, HttpSession session) {
        session.setAttribute("user", "小王");
        userService.regist(user);
    }*/
}
