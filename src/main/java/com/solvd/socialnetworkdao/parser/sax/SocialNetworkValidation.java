package com.solvd.socialnetworkdao.parser.sax;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class SocialNetworkValidation {
   public static void validate(String xmlFilePath, String xsdFilePath) throws SAXException, IOException {
       SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
       Schema schema = factory.newSchema(new File(xsdFilePath));
       Validator validator = schema.newValidator();
       validator.validate(new StreamSource(new File(xmlFilePath)));
   }
}
