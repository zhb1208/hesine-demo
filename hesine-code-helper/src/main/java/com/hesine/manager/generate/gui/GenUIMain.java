package com.hesine.manager.generate.gui;


import com.hesine.manager.generate.db.DBConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/3/5
 */
public class GenUIMain implements ActionListener {

    JFrame jf;
    JTextField jtf;
    JTextField jtf1;
    JTextField jtf2;
    JPasswordField jpf;

    public GenUIMain() {
        jf = new JFrame("代码生成页面");
        jf.setLayout(new GridLayout(7, 2));
        jf.add(new JPanel());

        JLabel jl5= new JLabel("IPAndPort：");
        jtf1 = new JTextField(12);
        jtf1.setText("127.0.0.1:3306");
        JPanel jp5 = new JPanel();
        jp5.add(jl5);
        jp5.add(jtf1);
        jf.add(jp5);

        JLabel jl4 = new JLabel("数据库名：");
        jtf2 = new JTextField(12);
        JPanel jp4 = new JPanel();
        jp4.add(jl4);
        jp4.add(jtf2);
        jf.add(jp4);

        JLabel jl1 = new JLabel("  用户名：");
        jtf = new JTextField(12);
        jtf.setText("root");
        JPanel jp1 = new JPanel();
        jp1.add(jl1);
        jp1.add(jtf);
        jf.add(jp1);

        JLabel jl2 = new JLabel("密    码：");
        jpf = new JPasswordField(12);
        JPanel jp2 = new JPanel();
        jp2.add(jl2);
        jp2.add(jpf);
        jf.add(jp2);

        JPanel jp3 = new JPanel();
        JButton jb1 = new JButton("配置相关数据");
        jb1.addActionListener(this);
//        JButton jb2 = new JButton("生成代码");
//        jb2.addActionListener(this);
        JButton jb3 = new JButton("取消");
        jb3.addActionListener(this);
        jp3.add(jb1);
//        jp3.add(jb2);
        jp3.add(jb3);
        jf.add(jp3);

        jf.setResizable(false);
        jf.setVisible(true);
        jf.setSize(500, 400);
        jf.setLocation(500, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GenUIMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comm = e.getActionCommand();
        if (comm.equals("配置相关数据")) {
            if ("".equals(jtf.getText())
                    || "".equals(jtf1.getText())
                    || "".equals(jtf2.getText())) {
                JOptionPane.showConfirmDialog(
                        jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
                        "IPAndPort 或者 数据库名 或者 用户名 或者 密码 不能为空！!\n请重新输入！", "错误",
                        JOptionPane.DEFAULT_OPTION);
                jtf.setText(null);
                jtf1.setText(null);
                jtf2.setText(null);
                jpf.setText(null);
                jtf.requestFocus();// 光标回来
            } else {
                DBConstant.hostname = jtf1.getText();
                DBConstant.database = jtf2.getText();
                DBConstant.user = jtf.getText();
                DBConstant.password = new String(jpf.getPassword());
                DBConstant.isProperties = false;
                jf.dispose();
                new GenConfigAndRun();
            }
        } else if (comm.equals("取消")) {
            System.exit(0);
        }

    }

}