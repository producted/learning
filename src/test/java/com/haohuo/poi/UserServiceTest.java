package com.haohuo.poi;

import com.haohuo.beans.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangpeike
 * @date 10:52 2019/1/21
 */
public class UserServiceTest {

    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void testLogin(){
        int size = 50000;
        List<User> users = new ArrayList<>();
        User user;
        for (int i = 0; i < size; i++) {
            user = new User();
            user.setId(i);
            user.setAge(i+10);
            user.setName("user" + i);
            user.setRemark(System.currentTimeMillis() + " ");
            user.setSex(i%3==0?"男":"女");
            users.add(user);
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        threadPool.execute(() -> {
            String[] columnName = {"用户id", "姓名", "年龄", "性别", "备注"};
            Object[][] data = new Object[size][5];
            int index = 0;
            for (User u : users) {
                data[index][0] = u.getId();
                data[index][1] = u.getName();
                data[index][2] = u.getAge();
                data[index][3] = u.getSex();
                data[index][4] = u.getRemark();
                index++;
            }

            XSSFWorkbook xssfWorkbook = generateExcel("test", "test", columnName, data);
            System.out.println("完成了！！！");
        });
    }

    private static XSSFWorkbook generateExcel(String sheetName,String title,String[] columnName, Object[][] data){
        XSSFWorkbook workBook = new XSSFWorkbook();
        //在workBook中添加一个sheet

        //如果没有给定sheet名 系统用默认的sheet1
        XSSFSheet sheet;
        if (StringUtils.isNotBlank(sheetName))
            sheet = workBook.createSheet(sheetName);
        else
            sheet = workBook.createSheet();

        //构建大标题 可以没有
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;
        cell = headRow.createCell(0);
        cell.setCellValue(title);

        //大标题行的偏移
        int offset = 0;
        if (StringUtils.isNotBlank(title))
            offset = 1;
        headRow = sheet.createRow(offset);
        for (int i = 0; i < columnName.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellValue(columnName[i]);
        }

        //构建表体数据 不能为空
        for (int i = 0; i < data.length; i++) {
            headRow = sheet.createRow(++offset);
            for (int j = 0; j < data[i].length; j++) {
                cell = headRow.createCell(j);
                if (data[i][j] instanceof BigDecimal)
                    cell.setCellValue(((BigDecimal) data[i][j]).doubleValue());
                else if (data[i][j] instanceof Double)
                    cell.setCellValue((Double) data[i][j]);
                else if (data[i][j] instanceof Long)
                    cell.setCellValue((Long) data[i][j]);
                else if (data[i][j] instanceof Integer)
                    cell.setCellValue((Integer) data[i][j]);
                else if (data[i][j] instanceof Boolean)
                    cell.setCellValue((Boolean) data[i][j]);
                else if (data[i][j] instanceof Date)
                    cell.setCellValue((Date) data[i][j]);
                else
                    cell.setCellValue((String) data[i][j]);

            }
        }
        return workBook;
    }
}
