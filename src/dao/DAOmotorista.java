package DAO;

import model.Modelmotorista;
import util.ConexaoSQLite;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOmotorista {

    ConexaoSQLite conexao;

    public DAOmotorista() {
        conexao = new ConexaoSQLite();
    }

    public int criarTBmotorista() {
        conexao.conectar();
        String sql
                = "CREATE TABLE if not exists tbl_motorista ("
                + "nome text,"
                + "cpf text,"
                + "pk_id integer PRIMARY KEY,"
                + "carro text)";
        try {
            Statement h = conexao.getConexao().createStatement();
            h.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOmotorista.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int salvarmotoristaDAO(Modelmotorista pModelmotorista) {
        conexao.conectar();
        String sql
                = "INSERT INTO tbl_motorista ("
                + "nome,"
                + "cpf,"
                + "pk_id,"
                + "carro"
                + ") VALUES (?,?,?,?);";
        try (PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pModelmotorista.getNome());
            preparedStatement.setString(2, pModelmotorista.getCpf());
            //preparedStatement.setInt(3, pModelmotorista.getId());
            preparedStatement.setString(4, pModelmotorista.getCarro());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            conexao.desconectar();
        }

    }

    public Modelmotorista getmotoristaDAO(String pNome) {
        Modelmotorista modelmotorista = null;

        String sql = "SELECT "
                + "nome,"
                + "cpf,"
                + "pk_id,"
                + "carro"
                + " FROM"
                + " tbl_motorista"
                + " WHERE"
                + " nome = ?"
                + ";";

        conexao.conectar();

        try (PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql)) {

            preparedStatement.setString(1, pNome);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                modelmotorista = new Modelmotorista();
                modelmotorista.setNome(resultSet.getString(1));
                modelmotorista.setCpf(resultSet.getString(2));
                modelmotorista.setId(resultSet.getInt(3));
                modelmotorista.setCarro(resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar();
        }
        return modelmotorista;
    }

    public ArrayList<Modelmotorista> getListamotoristaDAO() {
        ArrayList<Modelmotorista> listamodelmotorista = new ArrayList();
        Modelmotorista modelmotorista = null;

        String sql = "SELECT "
                + "nome,"
                + "cpf,"
                + "pk_id,"
                + "carro"
                + " FROM"
                + " tbl_motorista"
                + ";";

        conexao.conectar();

        try (PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                modelmotorista = new Modelmotorista();
                modelmotorista.setNome(resultSet.getString(1));
                modelmotorista.setCpf(resultSet.getString(2));
                modelmotorista.setId(resultSet.getInt(3));
                modelmotorista.setCarro(resultSet.getString(4));
                listamodelmotorista.add(modelmotorista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar();
        }
        return listamodelmotorista;
    }

    public boolean atualizarmotoristaDAO(Modelmotorista pModelmotorista) {
        conexao.conectar();
        String sql
                = "UPDATE tbl_motorista SET "
                + "nome = ?,"
                + "cpf = ?,"
                
                + "carro = ?"
                + " WHERE pk_id = ? "
                + ";";

        try (PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql)) {
            preparedStatement.setString(1, pModelmotorista.getNome());
            preparedStatement.setString(2, pModelmotorista.getCpf());
         
            preparedStatement.setString(3, pModelmotorista.getCarro());
            preparedStatement.setInt(4, pModelmotorista.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            conexao.desconectar();
        }

    }

    public boolean excluirmotoristaDAO(String pNome) {
        conexao.conectar();
        String sql
                = "DELETE FROM tbl_motorista "
                + " WHERE nome = ? "
                + ";";

        try (PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(sql)) {
            preparedStatement.setString(1, pNome);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            conexao.desconectar();
        }
    }
}
