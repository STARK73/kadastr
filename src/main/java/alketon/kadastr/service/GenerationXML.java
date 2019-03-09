package alketon.kadastr.service;

import alketon.kadastr.models.Contract;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class GenerationXML {

    public String getXML2(Contract contract) {


        Path path = Paths.get("C:/Users/Антон/IdeaProjects/kadastrFiles/templates/4.pmp");
        try {
            Files.copy(new File("C:/Users/Антон/IdeaProjects/kadastrFiles/templates/3.pmp").toPath(),
                    new File("C:/Users/Антон/IdeaProjects/kadastrFiles/templates/4.pmp").toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(path,
                    new String(Files.readAllBytes(path), "Windows-1251").replace("@Family",
                            contract.getFamilyName())
                            .getBytes("Windows-1251"));
            Files.write(path,
                    new String(Files.readAllBytes(path), "Windows-1251").replace("@First",
                            contract.getFirstName())
                            .getBytes("Windows-1251"));
            Files.write(path,
                    new String(Files.readAllBytes(path), "Windows-1251").replace("@Patronymic",
                            contract.getPatronymicName())
                            .getBytes("Windows-1251"));
            Files.write(path,
                    new String(Files.readAllBytes(path), "Windows-1251").replace("@SNILS",
                            contract.getSnils())
                            .getBytes("Windows-1251"));
            Files.write(path,
                    new String(Files.readAllBytes(path), "Windows-1251").replace("@ADDRESS",
                            contract.getAddressObject())
                            .getBytes("Windows-1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "C:/Users/Антон/IdeaProjects/kadastrFiles/templates/2.xml";
    }

    public String getXML(Contract contract) {
        try {
            File fXmlFile = new File("C:\\Users\\Антон\\IdeaProjects\\kadastrFiles\\templates\\1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath
                    .compile("//assignedTo");
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            System.out.println("Root element :"
                    + node.getTextContent());
            node.setTextContent("abc_puv@efg.com");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\temp\\2.xml"));
            transformer.transform(source, result);

            String xmlString = result.getWriter().toString();


            System.out.println(xmlString);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return "C:\\temp\\2.xml";
    }

}
