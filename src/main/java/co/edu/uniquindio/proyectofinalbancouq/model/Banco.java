package co.edu.uniquindio.proyectofinalbancouq.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreBanco;
    private List<Cliente> clientes;

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    // Método para guardar el estado del banco en un archivo
    public void guardarDatos(String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
        }
    }

    // Método para cargar el estado del banco desde un archivo
    public static Banco cargarDatos(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (Banco) ois.readObject();
        }
    }
}