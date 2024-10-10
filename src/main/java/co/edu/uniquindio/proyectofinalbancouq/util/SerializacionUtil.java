package co.edu.uniquindio.proyectofinalbancouq.util;

import co.edu.uniquindio.proyectofinalbancouq.model.Usuario;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.List;

public class SerializacionUtil {

    private static final String BINARIO_PATH = "C:/td/persistencia/usuarios.bin";
    private static final String XML_PATH = "C:/td/persistencia/usuarios.xml";

    // Serialización binaria
    public static void guardarUsuariosBinario(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BINARIO_PATH))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }

    public static List<Usuario> cargarUsuariosBinario() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BINARIO_PATH))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LogUtil.registrarExcepcion(e);
        }
        return null;
    }

    // Serialización XML
    public static void guardarUsuariosXML(List<Usuario> usuarios) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(XML_PATH))) {
            encoder.writeObject(usuarios);
        } catch (IOException e) {
            LogUtil.registrarExcepcion(e);
        }
    }

    public static List<Usuario> cargarUsuariosXML() {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(XML_PATH))) {
            return (List<Usuario>) decoder.readObject();
        } catch (FileNotFoundException e) {
            LogUtil.registrarExcepcion(e);
        }
        return null;
    }
}
