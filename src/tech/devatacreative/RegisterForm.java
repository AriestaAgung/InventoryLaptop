package tech.devatacreative;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterForm extends LoginForm{
    private JTextField tfNama;
    private JTextField tfUser;
    private JPasswordField pfPass;
    private JButton btnRegister;
    public JPanel panelRegister;
    static JFrame registerFrame = new JFrame("Register");


    public RegisterForm() {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    public void registerUser(){
        String sql, varUser, varNama;
        makeConnection();
        try {
            varNama = tfNama.getText();
            varUser = tfUser.getText();
            String varPassword = new String(pfPass.getPassword());
            sql = "INSERT INTO users(username, password, name) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, varUser);
            preparedStatement.setString(2, varPassword);
            preparedStatement.setString(3, varNama);
            preparedStatement.executeUpdate();
            System.out.println(varPassword);
            JOptionPane.showMessageDialog(null, "Registrasi berhasil !");
            tampilLoginForm();
            registerFrame.dispose();
        }catch (SQLException e){
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {

        registerFrame.setContentPane(new RegisterForm().panelRegister);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.pack();
        registerFrame.setVisible(true);
        registerFrame.setResizable(false);
    }
}
