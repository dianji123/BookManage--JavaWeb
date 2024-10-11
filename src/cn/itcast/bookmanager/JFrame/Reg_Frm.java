package cn.itcast.bookmanager.JFrame;

import cn.itcast.bookmanager.model.User;
import cn.itcast.bookmanager.utils.*;
import cn.itcast.bookmanager.dao.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;

public class Reg_Frm {

    private JFrame jf;
    private JTextField textField_un;
    private JTextField textField_pwd;
    private JTextField textField_tel;
    private JLabel userNameMes;
    private JLabel passwordMes;
    private JLabel telMes;
    private JRadioButton male;
    private JRadioButton female;

    public static void main(String[] args) {
        new Reg_Frm();
    }

    public Reg_Frm(){
        jf = new JFrame("用户注册");
        jf.setSize(500,500);
        jf.setBounds(600, 250, 500, 467);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel uName = new JLabel("用户名：");
        JLabel pwd = new JLabel("密码：");
        JLabel tel = new JLabel("手机号：");
        JLabel gender = new JLabel("性别：");

        uName.setForeground(Color.BLACK);
        uName.setFont(new Font("幼圆", Font.BOLD, 16));
        uName.setBounds(120,65,80,40);
        pwd.setForeground(Color.BLACK);
        pwd.setFont(new Font("幼圆", Font.BOLD, 16));
        pwd.setBounds(120,108,65,40);
        tel.setForeground(Color.BLACK);
        tel.setFont(new Font("幼圆", Font.BOLD, 16));
        tel.setBounds(120,151,80,40);
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("幼圆", Font.BOLD, 16));
        gender.setBounds(120,195,80,40);

        jf.getContentPane().add(uName);
        jf.getContentPane().add(pwd);
        jf.getContentPane().add(tel);
        jf.getContentPane().add(gender);

        textField_un = new JTextField();
        textField_pwd = new JTextField();
        textField_tel = new JTextField();

        textField_un.setFont(new Font("Dialog", Font.BOLD,14));
        textField_un.setToolTipText("这里是用户名的文本框");
        textField_un.setColumns(10);
        textField_pwd.setFont(new Font("Dialog", Font.BOLD,14));
        textField_pwd.setToolTipText("");
        textField_pwd.setColumns(10);
        textField_tel.setFont(new Font("Dialog", Font.BOLD,14));
        textField_tel.setToolTipText("");
        textField_tel.setColumns(10);

        textField_un.setBounds(198,70,164,30);
        textField_pwd.setBounds(198,114,164,30);
        textField_tel.setBounds(198,158,164,30);

        jf.getContentPane().add(textField_un);
        jf.getContentPane().add(textField_pwd);
        jf.getContentPane().add(textField_tel);

        ButtonGroup group = new ButtonGroup();
        male = new JRadioButton("男");
        female = new JRadioButton("女");
        male.setFont(new Font("幼圆", Font.BOLD, 16));
        male.setBounds(198,200,60,30);
        female.setFont(new Font("幼圆", Font.BOLD, 16));
        female.setBounds(280,200,60,30);

        group.add(male);
        group.add(female);
        jf.add(male);
        jf.add(female);

        JButton reg_btn = new JButton("注册");
        JButton login_btn = new JButton("登录");

        reg_btn.setFont(new Font("幼圆", Font.BOLD, 14));
        reg_btn.setBounds(150,270,70,30);
        login_btn.setFont(new Font("幼圆", Font.BOLD, 14));
        login_btn.setBounds(280,270,70,30);

        jf.add(reg_btn);
        jf.add(login_btn);

        userNameMes = new JLabel();
        passwordMes = new JLabel();
        telMes = new JLabel();

        textField_un.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String uName = textField_un.getText();
                if(toolUtil.isEmpty(uName)){
                    userNameMes.setText("用户名不能为空");
                    userNameMes.setForeground(Color.RED);
                }else {
                    boolean flag =
                            uName.matches("^[a-zA-Z0-9]+$");
                    if(flag){
                        userNameMes.setText("√");
                        userNameMes.setForeground(Color.GREEN);
                    }else {
                        JOptionPane.showMessageDialog(null, "用户名只能包含字母和数字");
                        userNameMes.setText("");
                    }
                }

            }
        });
        textField_pwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String pwd = textField_pwd.getText();
                if(toolUtil.isEmpty(pwd)){
                    passwordMes.setText("密码不能为空");
                    passwordMes.setForeground(Color.RED);
                }else {
                    boolean flag =
                            pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
                    if(flag){
                        passwordMes.setText("√");
                        passwordMes.setForeground(Color.GREEN);
                    }else {
                        JOptionPane.showMessageDialog(null, "密码需为6~16为数字的组合");
                        passwordMes.setText("");
                    }
                }
            }
        });
        textField_tel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String tel = textField_tel.getText();
                if(toolUtil.isEmpty(tel)){
                    telMes.setText("手机号不能为空");
                    telMes.setForeground(Color.RED);
                }else {
                    boolean flag =
                            tel.matches("^1[3-9]\\d{9}$");
                    if(flag){
                        telMes.setText("√");
                        telMes.setForeground(Color.GREEN);
                    }else {
                        JOptionPane.showMessageDialog(null, "手机号错误");
                        telMes.setText("");
                    }
                }
            }
        });

        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new LoginFrm();
            }
        });

        reg_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegCheck(e);
            }
        });

        jf.setVisible(true);
        jf.setResizable(true);
    }
    protected void RegCheck (ActionEvent e){
        String reg_un = textField_un.getText();
        String reg_pwd = textField_pwd.getText();
        String reg_tel = textField_tel.getText();
        String reg_gender = "";
        if(male.isSelected()){
            reg_gender = male.getText();
        }else {
            reg_gender = female.getText();
        }
        if(toolUtil.isEmpty(reg_un) || toolUtil.isEmpty(reg_pwd) ||toolUtil.isEmpty(reg_tel)){
            JOptionPane.showMessageDialog(null, "请输入相关信息");
            return;
        }
        User user = new User();
        user.setUserName(reg_un);
        user.setPassword(reg_pwd);
        user.setPhone(reg_tel);
        user.setSex(reg_gender);
        user.setRole(1);
        Connection conn = null;
        DbUtil dbUtil = new DbUtil();
        UserDao userDao = new UserDao();
        try{
            conn = dbUtil.getConnection();
            int i = userDao.addUser(conn, user);
            if(i == 2){
                JOptionPane.showMessageDialog(null, "该用户名已存在，请重新注册");
            }else if(i == 0){
                JOptionPane.showMessageDialog(null, "注册失败");
            }else {
                JOptionPane.showMessageDialog(null, "注册成功");
                jf.dispose();
                new LoginFrm();
            }
        } catch (Exception e1){
            e1.printStackTrace();
        } finally {
            try{
                dbUtil.closeConn(conn);
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

}
