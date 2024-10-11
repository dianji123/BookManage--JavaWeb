package cn.itcast.bookmanager.JFrame;

import cn.itcast.bookmanager.dao.UserDao;
import cn.itcast.bookmanager.model.User;
import cn.itcast.bookmanager.utils.DbUtil;
import cn.itcast.bookmanager.utils.toolUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginFrm extends JFrame {
    public static User currentUser;
    private JFrame jf;
    private JTextField userNameText;
    private JTextField passwordText;
    private JComboBox<String> comboBox;


    public static void main(String[] args) {
//        try {
//            //new LoginFrm();
//            // BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
//            // org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new LoginFrm();
    }

    public LoginFrm(){

        jf=new JFrame("图书管理");
        jf.getContentPane().setFont(new Font("幼圆", Font.BOLD, 14));
        jf.setBounds(600, 250, 500, 467);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);


        ImageIcon imageIcon = new ImageIcon("source/OIP-C.jpg");
        Image img = imageIcon.getImage();
        img = img.getScaledInstance(300,150,Image.SCALE_DEFAULT);
        imageIcon.setImage(img);
        JLabel lblNewLabel = new JLabel(imageIcon);
        //JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(24, 10, 430, 218);
        jf.getContentPane().add(lblNewLabel);

        JLabel label = new JLabel("用户名：");
        label.setFont(new Font("幼圆", Font.BOLD, 14));
        label.setBounds(120, 250, 70, 29);
        jf.getContentPane().add(label);

        userNameText = new JTextField();
        userNameText.setBounds(199, 252, 127, 25);
        jf.getContentPane().add(userNameText);
        userNameText.setColumns(10);

        JLabel label_1 = new JLabel("密码：");
        label_1.setFont(new Font("幼圆", Font.BOLD, 14));
        label_1.setBounds(135, 289, 70, 29);
        jf.getContentPane().add(label_1);

        passwordText = new JPasswordField();
        passwordText.setColumns(10);
        passwordText.setBounds(199, 291, 127, 25);
        jf.getContentPane().add(passwordText);

        JLabel label_2 = new JLabel("权限：");
        label_2.setFont(new Font("幼圆", Font.BOLD, 14));
        label_2.setBounds(135, 328, 70, 29);
        jf.getContentPane().add(label_2);

        comboBox = new JComboBox();
        comboBox.setBounds(199, 332, 127, 25);
        comboBox.addItem("用户");
        comboBox.addItem("管理员");
        jf.getContentPane().add(comboBox);

        JButton button = new JButton("登录");

        button.setBounds(153, 377, 65, 29);
        jf.getContentPane().add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin(e);
            }
        });


        JButton button_1 = new JButton("注册");

        button_1.setBounds(263, 377, 65, 29);
        jf.getContentPane().add(button_1);

        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new Reg_Frm();
            }
        });

        JLabel lblNewLabel_1 = new JLabel("");
        //lblNewLabel_1.setIcon(new ImageIcon(LoginFrm.class.getResource("C:\\Users\\10956\\Desktop\\cover.jpg")));
        lblNewLabel_1.setIcon(new ImageIcon());
        lblNewLabel_1.setBounds(0, 0, 484, 429);
        jf.getContentPane().add(lblNewLabel_1);

        jf.setVisible(true);
        jf.setResizable(true);

    }
    UserDao userDao = new UserDao();
    DbUtil dbUtil = new DbUtil();

    protected void checkLogin(ActionEvent e){
        String userName = userNameText.getText();
        String password = passwordText.getText();
        int index = comboBox.getSelectedIndex();
        if(toolUtil.isEmpty(userName) || toolUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空");
            return;
        }
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        if(index == 0){
            user.setRole(1);
        }else {
            user.setRole(2);
        }
        Connection conn = null;
        try{
            conn = dbUtil.getConnection();
            User login = userDao.login(conn, user);
            currentUser = login;
            if(login == null){
                JOptionPane.showMessageDialog(null, "登录失败");
            } else {
                //权限 1普通 2管理员
                if(index == 0){
                    jf.dispose();
                    new UserMenuFrm();
                } else {
                    jf.dispose();
                    //new AdminMenuFrm();
                }
            }
        } catch (Exception e2){
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "登录异常");
        } finally {
            try{
                dbUtil.closeConn(conn);
            } catch (Exception e3){
                e3.printStackTrace();
            }
        }

    }

}
