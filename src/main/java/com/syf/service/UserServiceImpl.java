package com.syf.service;

import com.syf.dao.UserDao;
import com.syf.entity.User;
import com.syf.entity.UserDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> queryAll(Integer page, Integer rows) {
        Integer i = (page - 1) * rows;
        List<User> users = userDao.queryAll(i, rows);
        return users;
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public Integer total() {
        return userDao.total();
    }

    @Override
    public Workbook exportAll(Integer page, Integer rows) {
        //创建工作簿对象
        Workbook workbook = new HSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("user");
        //修改日期样式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //创建标题行
        Row row = sheet.createRow(0);
        String[] strs = {"编号", "姓名", "法名", "性别", "省份", "城市", "手机", "签名", "状态", "注册时间"};
        for (int i = 0; i < strs.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(strs[i]);
        }

        //处理数据行
        Integer p = (page - 1) * rows;
        List<User> users = userDao.queryAll(p, rows);
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getName());
            row1.createCell(2).setCellValue(users.get(i).getDharmaName());
            row1.createCell(3).setCellValue(users.get(i).getSex());
            row1.createCell(4).setCellValue(users.get(i).getProvince());
            row1.createCell(5).setCellValue(users.get(i).getCity());
            row1.createCell(6).setCellValue(users.get(i).getPhone());
            row1.createCell(7).setCellValue(users.get(i).getSign());
            row1.createCell(8).setCellValue(users.get(i).getStatus());
            Cell cell = row1.createCell(9);
            cell.setCellStyle(cellStyle);
            sheet.setColumnWidth(9, 13 * 256);
            cell.setCellValue(users.get(i).getRegistDate());
        }

        //返回工作簿对象
        return workbook;
    }

    @Override
    public Workbook customerExport(String titles, String params, Integer page, Integer rows) {
        //创建工作簿对象
        Workbook workbook = new HSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("user");
        //修改日期样式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //创建标题行
        Row row = sheet.createRow(0);
        //拆分前台传递的标题字符串
        String[] strs = titles.split(",");
        for (int i = 0; i < strs.length; i++) {
            row.createCell(i).setCellValue(strs[i]);
        }
        //拆分标题属性
        String[] fileds = params.split(",");
        //获取数据
        Integer p = (page - 1) * rows;
        List<User> users = userDao.queryAll(p, rows);
        for (int i = 0; i < users.size(); i++) {
            //创建数据行
            Row row1 = sheet.createRow(i + 1);
            //获取类对象
            User user = users.get(i);
            Class<? extends User> userClass = user.getClass();
            //创建单元格并填充内容
            for (int j = 0; j < fileds.length; j++) {
                Cell cell = row1.createCell(j);
                //获取方法名
                String methodName = "get" + fileds[j].substring(0, 1).toUpperCase() + fileds[j].substring(1);
                try {
                    //获取方法对象
                    Method method = userClass.getMethod(methodName, null);
                    //调用方法。获得方法返回值
                    Object invoke = method.invoke(user, null);
                    //根据返回值类型判断，并赋值到单元格
                    if (invoke instanceof Date) {
                        cell.setCellStyle(cellStyle);
                        //设置单元格列宽
                        sheet.setColumnWidth(j, 13 * 256);
                        cell.setCellValue((Date) invoke);
                    } else if (invoke instanceof Integer) {
                        cell.setCellValue((Integer) invoke);
                    } else {
                        cell.setCellValue((String) invoke);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回工作薄对象
        return workbook;
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            //获取工作薄对象
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            //获取工作表
            Sheet sheet = workbook.getSheet("user");
            //获取类对象
            User user = new User();
            Class<? extends User> userClass = user.getClass();
            //通过类对象获取类的所有属性
            List<String> titles = new ArrayList<>();
            Field[] fields = userClass.getDeclaredFields();
            //将属性名存入集合
            for (Field field : fields) {
                titles.add(field.getName());
            }
            titles.remove("photoImg");
            titles.remove("salt");
            //创建集合接收数据
            List<User> users = new ArrayList<>();
            //遍历表中数据(跳过标题行)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                User user2 = new User();
                //获取行
                Row row = sheet.getRow(i);
                //通过反射获取单元格数据为对象赋值
                for (int j = 0; j < titles.size(); j++) {
                    //拼接set方法名
                    String methodName = "set" + titles.get(j).substring(0, 1).toUpperCase() + titles.get(j).substring(1);
                    //获取单元格数据类型
                    int cellType = row.getCell(j).getCellType();
                    //判断数据类型并作出相应赋值
                    if (cellType == Cell.CELL_TYPE_STRING) {//字符串类型
                        String s = row.getCell(j).toString();
                        //获取方法对象
                        Method method = userClass.getMethod(methodName, String.class);
                        //调用方法,set方法无需返回值
                        method.invoke(user2, s);
                    } else {//日期类型
                        Date dateCellValue = row.getCell(j).getDateCellValue();
                        //获取方法对象
                        Method method = userClass.getMethod(methodName, Date.class);
                        //调用方法,set方法无需返回值
                        method.invoke(user2, dateCellValue);
                    }
                }
                users.add(user2);
            }
            userDao.save(users);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer queryByDate(Integer date) {
        return userDao.queryByDate(date);
    }

    @Override
    public List<UserDto> queryByProvince(String sex) {
        return userDao.queryByProvince(sex);
    }
}
