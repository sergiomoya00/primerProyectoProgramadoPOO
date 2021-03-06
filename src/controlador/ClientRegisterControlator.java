/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProviderDAO;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ChooseRole;
import vista.ClientLogIn;
import vista.ClientRegister;
import vista.ProviderRegister;
import vista.UserRegister;

/**
 *
 * @author jabre
 */
public class ClientRegisterControlator implements ActionListener {

    private ClientRegister userRegister;
    private ProviderDAO user = new ProviderDAO();
    private String nombre;
    private String opcion;
    private ProviderRegister provider = new ProviderRegister();
    private ClientLogIn role = new ClientLogIn();

    public ClientRegisterControlator() {

    }

    public ClientRegisterControlator(ClientRegister user) {
        this.userRegister = user;
    }

    public void openClientRegister(String nombre,String opcion) {
        userRegister.setTitle("Registo Usuario");
        userRegister.setLocationRelativeTo(null);
        userRegister.setVisible(true);
        
        this.opcion=opcion;
        this.nombre = nombre;
        this.userRegister.nextButton.setActionCommand("nextButton");
        this.userRegister.nextButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        switch (buttons.valueOf(evento.getActionCommand())) {
            case nextButton:
                user.clientInformationRegister(nombre, Integer.parseInt(userRegister.txtId.getText()), userRegister.txtProvince.getText(), userRegister.txtcanton.getText(), userRegister.txtDistrict.getText(), userRegister.txtInfo.getText(), Integer.parseInt(userRegister.txtphone.getText()), userRegister.txtemail.getText(), userRegister.txtUbication.getText());
                new ClientLogInControlator(role).openUserRegister();
                break;

        }
    }

    public enum buttons {
        nextButton
    }
}
