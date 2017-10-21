import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Converter {
    public static void main (String[] ARGS) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Hakon von Maydell\\Desktop\\german_small.txt"))){

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element component = doc.createElement("component");
            component.setAttribute("name", "ProjectDictionaryState");
            doc.appendChild(component);

            Element dictionary = doc.createElement("dictionary");
            dictionary.setAttribute("name", "Hakon von Maydell");
            component.appendChild(dictionary);

            Element words = doc.createElement("words");
            dictionary.appendChild(words);


            List<String> woerter = new ArrayList<>();
            String zeile;
            while ((zeile = br.readLine()) != null) {
                zeile = zeile.toLowerCase();
                woerter.add(zeile);
            }

            Iterator<String> it = woerter.iterator();
            while (it.hasNext()) {
                String temp = it.next();
                Element w = doc.createElement("w");
                w.setTextContent(temp);
                words.appendChild(w);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Hakon von Maydell\\Desktop\\Hakon_von_Maydell.xml"));
            transformer.transform(source, result);
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e){
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
