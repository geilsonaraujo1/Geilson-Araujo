package controller;

import model.Modelmotorista;
import DAO.DAOmotorista;
import java.util.ArrayList;

public class Controllermotorista {

    private DAOmotorista daomotorista = new DAOmotorista();

    public int salvarmotoristaController(Modelmotorista pModelmotorista) {
        return this.daomotorista.salvarmotoristaDAO(pModelmotorista);
    }

    public int criarTBMotorista() {
        return this.daomotorista.criarTBmotorista();
    }

    public Modelmotorista getmotoristaController(String pNome) {
        return this.daomotorista.getmotoristaDAO(pNome);
    }

    public ArrayList<Modelmotorista> getListamotoristaController() {
        return this.daomotorista.getListamotoristaDAO();
    }

    public boolean atualizarmotoristaController(Modelmotorista pModelmotorista) {
        return this.daomotorista.atualizarmotoristaDAO(pModelmotorista);
    }

    public boolean excluirmotoristaController(String pNome) {
        return this.daomotorista.excluirmotoristaDAO(pNome);
    }
}
