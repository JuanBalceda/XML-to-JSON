package com.balceda.xmltojson;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class Main {
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, JSONException {

        //Loading XML file
        File file = new File("src/main/books.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDoc = builder.parse(file);

        OutputFormat format = new OutputFormat(xmlDoc);
        StringWriter stringOut = new StringWriter();
        XMLSerializer serial = new XMLSerializer(stringOut,
                format);
        serial.serialize(xmlDoc);
        String data = stringOut.toString();

        JSONObject xmlJSONObj = XML.toJSONObject(data);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        System.out.println(jsonPrettyPrintString);

    }
}
