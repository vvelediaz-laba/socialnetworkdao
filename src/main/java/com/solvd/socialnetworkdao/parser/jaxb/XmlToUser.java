package com.solvd.socialnetworkdao.parser.jaxb;

import com.solvd.socialnetworkdao.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlToUser {
    private static final Logger logger = LogManager.getLogger(XmlToUser.class.getName());

    public static User getUserFromXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (User) unmarshaller.unmarshal(new File("src/main/resources/user.xml"));

        } catch (JAXBException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
