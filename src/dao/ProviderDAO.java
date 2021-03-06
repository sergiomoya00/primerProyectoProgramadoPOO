/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.mail.Provider;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Providers;
import vista.AdminProvider;
import vista.ClientRegister;
import vista.ProviderRegister;

/**
 *
 * @author jabre
 */
public class ProviderDAO {

    ProviderRegister providerRegister;
    AdminProvider adminProvider;
    ClientRegister clientRegister;
    Conexion conexion = new Conexion();
    Connection cin = conexion.getConnection();
    PreparedStatement ps;
    ArrayList<Providers> array = new ArrayList<>();

    public ProviderDAO() {
    }

    public void providerRegister(String id, String name, String company) {

        String insertar = "insert into proveedores (idProveedor,nombre,empresa,calificacion) values (?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, company);
            ps.setFloat(4, 0.0F);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, "No Registrado ");
        }
    }

    public void providerInformationRegister(String username, String provincia, String canton, String distrito, String señas, int phone, String email, String ubication, String website, String schedule, String profile) {

        String insertar = "insert into informacionProveedor(nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, username);
            ps.setString(2, null);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, señas);
            ps.setInt(7, phone);
            ps.setString(8, email);
            ps.setString(9, ubication);
            ps.setString(10, website);
            ps.setString(11, schedule);
            ps.setString(12, profile);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(providerRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(providerRegister, e.toString());
        }
    }

    public void clientInformationRegister(String username, int cedula, String provincia, String canton, String distrito, String señas, int phone, String email, String ubication) {

        String insertar = "insert into informacion(nombreUsuario,cedula,provincia,canton,distrito,señas,telefono,correoElectronico,ubicacion,sitio,horario,perfil) values (?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            ps = cin.prepareCall(insertar);
            ps.setString(1, username);
            ps.setInt(2, cedula);
            ps.setString(3, provincia);
            ps.setString(4, canton);
            ps.setString(5, distrito);
            ps.setString(6, señas);
            ps.setInt(7, phone);
            ps.setString(8, email);
            ps.setString(9, ubication);
            ps.setString(10, null);
            ps.setString(11, null);
            ps.setString(12, null);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(clientRegister, "Registrado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(clientRegister, "No Registrado ");
        }
    }

    public void getAllProviders(JTable table) {

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            table.setModel(modelo);
            ResultSet rs = null;
            String login = "SELECT * FROM proveedores A,informacionProveedor B";
            ps = cin.prepareStatement(login);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idProveedor");
            modelo.addColumn("Nombre");
            modelo.addColumn("Empresa");
            modelo.addColumn("Calificacion");
            modelo.addColumn("Provincia");
            modelo.addColumn("Canton");
            modelo.addColumn("Distrito");
            modelo.addColumn("Señas");
            modelo.addColumn("Telefono");
            modelo.addColumn("Correo");
            modelo.addColumn("Ubicacion");
            modelo.addColumn("Sito Web");
            modelo.addColumn("Horario");
            modelo.addColumn("Perfil");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];

                for (int i = 1; i <= cantidadColumnas + 2; i++) {
                    if (i < 7) {
                        if (i == 5 || i == 6) {

                        } else {
                            filas[i - 1] = rs.getObject(i);
                        }
                    } else {
                        if (i > cantidadColumnas) {
                        } else {
                            filas[i - 3] = rs.getObject(i);
                        }
                    }
                }

                modelo.addRow(filas);

            }

        } catch (SQLException ex) {

        }

    }

    public void deleteProvider(String id) {
        String delete = "delete  from proveedores where idProveedor='" + id + "'";

        try {
            ps = cin.prepareCall(delete);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(adminProvider, "Proveedor eliminado con exito");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }

    public void deleteProviderInformation(String id) {
        String delete = "delete from informacionProveedor where nombreUsuario='" + id + "'";

        try {
            ps = cin.prepareCall(delete);
            ps.executeQuery();

        } catch (Exception e) {

        }
    }

    public void updateProviderInformation(String username, String provincia, String canton, String distrito, String señas, int phone, String email, String ubication, String website, String schedule, String profile) {

        String update = "UPDATE informacionProveedor SET  provincia='" + provincia + "',canton='" + canton + "',distrito='" + distrito + "',señas='" + señas + "',telefono='" + phone + "',correoElectronico='" + email + "',ubicacion='" + ubication + "',sitio='" + website + "',horario='" + schedule + "',perfil='" + profile + "' WHERE nombreUsuario='" + username + "'";
        try {

            ps = cin.prepareStatement(update);
            ps.executeUpdate();
             JOptionPane.showMessageDialog(adminProvider, "Informacion editada con exito");

        } catch (Exception e) {
       JOptionPane.showMessageDialog(adminProvider, e.toString());
        }
    }

}
