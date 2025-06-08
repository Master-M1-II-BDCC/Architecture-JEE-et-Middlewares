package net.ikrame.framework;

import net.ikrame.framework.xml.Beans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MyIoCContainer {

    private Beans beans;

    public void loadBeansFromXML(String xmlFilePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Beans.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            beans = (Beans) unmarshaller.unmarshal(new File(xmlFilePath));
            System.out.println("XML chargé avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du XML : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Beans getBeans() {
        return beans;
    }

}
