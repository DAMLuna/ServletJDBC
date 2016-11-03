/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Andreu Luna Font
 */
public class GeneradorXml {

    //Método que prepara los campos del archivo XML
    public void prepararArchivo(ArrayList<Alumnos> alum) {
        String nomXml = "C:\\Users\\kerinvel\\Documents\\NetBeansProjects\\-Acceso a datos\\2.2Servlet\\src\\java\\xml\\XmlAlumno.xml";
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "Curso", null);

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0");
            //Creación de elementos
            //creamos el elemento principal libros            
            Element elSec = document.createElement("Alumnos");
            //Añadimos el libro al documento
            document.getDocumentElement().appendChild(elSec);
            //creamos un nuevo elemento que será libro
            Element elTer = document.createElement("Alumno");
            //Añadimos el elemento hijo a la raíz
            elSec.appendChild(elTer);
            //creamos un nuevo elemento que sera titulo
            Element eleNom = document.createElement("Nombre");
            //Añadimos elemento
            elTer.appendChild(eleNom);
            //Ingresamos el titulo
            Text vlr = document.createTextNode(alum.get(0).getNomAl());
            //Añadimos valor
            eleNom.appendChild(vlr);

            Element elSub1 = document.createElement("Asignaturas");
            elTer.appendChild(elSub1);

            for (int i = 0; i < alum.size(); i++) {
                Element elSub11 = document.createElement("Asignatura");
                elSub1.appendChild(elSub11);
                Text vlr2 = document.createTextNode(alum.get(i).getNomAss());
                elSub11.appendChild(vlr2);
            }

            Element elSub2 = document.createElement("Tutorias");
            elTer.appendChild(elSub2);

            for (int i = 0; i < alum.size(); i++) {
                Element elSub21 = document.createElement("Tutoria");
                elSub2.appendChild(elSub21);
                Text vlr3 = document.createTextNode(alum.get(i).getNomTut());
                elSub21.appendChild(vlr3);
            }

            guardaConFormato(document, nomXml);
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    //Método que 
    public void guardaConFormato(Document document, String URL) {
        try {
            TransformerFactory transFact = TransformerFactory.newInstance();

            //Formateamos el fichero. Añadimos sangrado y la cabecera de XML
            transFact.setAttribute("indent-number", new Integer(3));
            Transformer trans = transFact.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

            //Hacemos la transformación
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            DOMSource domSource = new DOMSource(document);
            trans.transform(domSource, sr);

            //Mostrar información a guardar por consola (opcional)
            //Result console= new StreamResult(System.out);
            //trans.transform(domSource, console);
            try {
                //Creamos fichero para escribir en modo texto
                PrintWriter writer = new PrintWriter(new FileWriter(URL));

                //Escribimos todo el árbol en el fichero
                writer.println(sw.toString());

                //Cerramos el fichero
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
