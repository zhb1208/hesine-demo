package com.hesine.manager.generate.gui;


import com.hesine.manager.generate.Generate;
import com.hesine.manager.generate.db.DBConstant;
import com.hesine.manager.generate.db.DBOperator;
import com.hesine.manager.generate.project.CreateProjectUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/3/5
 */
public class GenConfigAndRun implements ActionListener {

    JFrame jf;
    JTextField jtf,jtf1;
    JTextField jpf, jpf2, jpf3, jpf4;
    JComboBox box, box1;

    public GenConfigAndRun() {
        System.out.println("hostname:" + DBConstant.hostname);
        System.out.println("database:" + DBConstant.database);
        System.out.println("user:" + DBConstant.user);
        System.out.println("password:" + DBConstant.password);
        jf = new JFrame("配置相关数据");
        jf.setLayout(new GridLayout(9, 2));
        jf.add(new JPanel());
        JLabel jl1 = new JLabel("项目路径：");
        jtf = new JTextField(12);
        JPanel jp1 = new JPanel();
        jp1.add(jl1);
        jp1.add(jtf);
        jf.add(jp1);

        JLabel jl11 = new JLabel("项目名称：");
        jtf1 = new JTextField(12);
        JPanel jp11 = new JPanel();
        jp11.add(jl11);
        jp11.add(jtf1);
        jf.add(jp11);

        // 选择表名 begin
        JLabel jl41 = new JLabel("初始项目：");
        box1 = new JComboBox();
        JPanel jp51 = new JPanel();
        jp51.add(jl41);
        box1.addItem("否");
        box1.addItem("是");
        jp51.add(box1);
        jf.add(jp51);


        JLabel jl2 = new JLabel("项目包名：");
        jpf = new JTextField(12);
        JPanel jp2 = new JPanel();
        jp2.add(jl2);
        jp2.add(jpf);
        jf.add(jp2);

        JLabel jl3 = new JLabel("模块名称：");
        jpf2 = new JTextField(12);
        JPanel jp3 = new JPanel();
        jp3.add(jl3);
        jp3.add(jpf2);
        jf.add(jp3);

        // 选择表名 begin
        JLabel jl4 = new JLabel("选择表名：");
        jpf3 = new JTextField(12);
        box = new JComboBox();
        JPanel jp5 = new JPanel();
        jp5.add(jl4);

        // 获取表数据
        // 组装表
        JPanel jptable = new JPanel();
        jptable.setBorder(BorderFactory.createTitledBorder("请选择需要生成的表"));// 定义一个面板的边框显示条

        // 获取表
        List<Map<String,String>> list = DBOperator.getTables(null);
        jptable.setLayout(new GridLayout(list.size(), 3));// 定义排版，1行3列
        JCheckBox jcb = null;
        for(Map<String,String> a: list) {
            box.addItem(a.get("tableName"));
//            jcb = new JCheckBox(a.get("tableName"));
//            jptable.add(jcb);
        }

        jp5.add(box);
        jf.add(jp5);
        // 选择表名 end

        JLabel jl5 = new JLabel("表名前缀：");
        jpf4 = new JTextField(12);
        JPanel jp6 = new JPanel();
        jp6.add(jl5);
        jp6.add(jpf4);
        jf.add(jp6);

        JPanel jp4 = new JPanel();
        JButton jb1 = new JButton("生成代码");
        jb1.addActionListener(this);
        JButton jb2 = new JButton("取消");
        jb2.addActionListener(this);
        jp4.add(jb1);
        jp4.add(jb2);
        jf.add(jp4);

        jf.setResizable(false);
        jf.setVisible(true);
        jf.setSize(500, 400);
        jf.setLocation(500, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GenConfigAndRun();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comm = e.getActionCommand();
        if (comm.equals("生成代码")) {
            // jf.dispose();
            if ("".equals(jtf.getText())
                    || "".equals(jtf1.getText())
                    || "".equals(new String(jpf.getText()))
                    || jpf.getText() == null) {
                final JFrame jf = new JFrame("错误");
                JLabel jl = new JLabel("项目路径 或者 项目名称 或者 项目包名 不能为空！");
                JPanel jp1 = new JPanel();
                JPanel jp2 = new JPanel();
                jp1.add(jl);
                jf.add(jp1);
                JButton jb = new JButton("确定");
                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.dispose();
                    }

                });
                jp2.add(jb);
                jf.add(jp2);
                jf.setLayout(new GridLayout(2, 1));
                jf.setResizable(false);
                jf.setVisible(true);
                jf.pack();
                jf.setLocation(400, 300);
            } else {
                String projectPath = jtf.getText();
                String projectName = jtf1.getText();
                String packageName = jpf.getText();
                String moduleName = jpf2.getText();
                String tableName = (String) box.getSelectedItem();
                String initProject = (String) box1.getSelectedItem();
                String prefix = jpf4.getText();
                File file = new File(projectPath);

                if (initProject.equals("是")) {
                    // 生成初始化项目
                    CreateProjectUtils createProjectUtils = new CreateProjectUtils();
                    createProjectUtils.setProjectName(projectName);
                    createProjectUtils.setPackageName(packageName);
                    createProjectUtils.setBasedir(file);
                    createProjectUtils.execute();
                } else {
                    // 生成动态代码文件
                    Map<String,String> tableMap = DBOperator.getTable(prefix,tableName);
                    List<Map<String,String>> listTC =
                            DBOperator.getTableColumns(tableName);
                    Generate.execute(file, packageName, moduleName, tableMap, listTC);
                }

                int a = JOptionPane.showConfirmDialog(jf, "生成成功！\n"
                        + "\n点确定退出", "生成结果",
                        JOptionPane.OK_CANCEL_OPTION);
                if (a == 0) {
                    jf.dispose();
                }

            }
        } else if (comm.equals("取消")) {
            System.exit(0);
        }

    }
}