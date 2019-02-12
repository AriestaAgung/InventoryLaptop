package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm extends MainForm{
    private JTextField tfUsername;
    private JPanel panelLogin;
    private JButton btnLogin;
    private JPasswordField pfPassword;
    private JButton btnRegister;
    static JFrame mainFrame = new JFrame("LOGIN");



    public LoginForm() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }

    public void login(){
        String user;
        String password;
        String sql;
        makeConnection();
        try{
                user = tfUsername.getText();
                password = new String(pfPassword.getPassword());
                sql = "SELECT username, password FROM users WHERE username='"+user+"' AND password='"+password+"'";
//                preparedStatement = connection.prepareStatement(sql);
//                preparedStatement.setString(1, user);
//                preparedStatement.setString(2, password);
//                preparedStatement.executeUpdate();
//                preparedStatement.close();
                statement = connection.createStatement();
                result = statement.executeQuery(sql);
                while (result.next()){
                    String username = result.getString("username"), passsword = result.getString("password");
                    if (user.equals(username) && password.equals(passsword)){

                        tampilMainForm();

                        mainFrame.dispose();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Username / Password salah !","Failed !", JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void register(){
        JFrame registerFrame = new JFrame("Register");
        registerFrame.setContentPane(new RegisterForm().panelRegister);
        registerFrame.pack();
        registerFrame.setVisible(true);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setResizable(false);
    }

    public static void tampilLoginForm(){
        mainFrame.setContentPane(new LoginForm().panelLogin);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }



    public static void main(String[] args) {

        tampilLoginForm();


    }
}
