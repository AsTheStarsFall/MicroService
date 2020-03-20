package com.tianhy.springbootspringmvc.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.tianhy.springbootspringmvc.pojo.User;
import com.tianhy.springbootspringmvc.service.PdfExportService;
import com.tianhy.springbootspringmvc.service.UserService;
import com.tianhy.springbootspringmvc.validator.UserValidator;
import com.tianhy.springbootspringmvc.view.PDFView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //调用控制器前先执行此方法
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
        //定义日期参数格式
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), false
        ));
    }

    /**
     * @param user   -- 用户对象用StringToUserConverter转换
     * @param Errors --验证器返回的错误
     * @param date   -- 因为WebDataBinder已经绑定了格式，所以不再需要注解
     * @return 各类数据
     */
    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(@Valid User user, Errors Errors, Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);
        // 判断是否存在错误
        if (Errors.hasErrors()) {
            // 获取全部错误
            List<ObjectError> oes = Errors.getAllErrors();
            for (ObjectError oe : oes) {
                // 判定是否字段错误
                if (oe instanceof FieldError) {
                    // 字段错误
                    FieldError fe = (FieldError) oe;
                    map.put(fe.getField(), fe.getDefaultMessage());
                } else {
                    // 对象错误
                    map.put(oe.getObjectName(), oe.getDefaultMessage());
                }
            }
        }
        return map;
    }

    //导出PDF
    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(String userName, String note) {
        List<User> users = userService.findUsers(userName, note);
        View view = new PDFView(exportService());
        ModelAndView mv = new ModelAndView();
        //设置视图
        mv.setView(view);
        //加入数据模型
        mv.addObject("userList", users);
        return mv;
    }

    //导出PDF自定义
    public PdfExportService exportService() {
        // 使用Lambda表达式定义自定义导出
        return (model, document, writer, request, response) -> {
            try {
                // A4纸张
                document.setPageSize(PageSize.A4);
                // 标题
                document.addTitle("用户信息");
                // 换行
                document.add(new Chunk("\n"));
                // 表格，3列
                PdfPTable table = new PdfPTable(3);
                // 单元格
                PdfPCell cell = null;
                // 字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                // 标题
                cell = new PdfPCell(new Paragraph("id", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                // 获取数据模型中的用户列表
                List<User> userList = (List<User>) model.get("userList");
                for (User user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName()));
                    table.addCell(cell);
                    String note = user.getNote() == null ? "" : user.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }
                // 在文档中加入表格
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        };
    }

    @GetMapping("/header/page")
    public String headerPage() {
        return "header";
    }

    @PostMapping("/header/user")
    @ResponseBody
    // 通过@RequestHeader接收请求头参数
    public User headerUser(@RequestHeader("id") Long id) {
        User user = userService.getUser(id);
        return user;
    }
}
