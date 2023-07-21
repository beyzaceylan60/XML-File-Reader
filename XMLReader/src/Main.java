import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class Main {
    public static void main(String[] args) {
        try {
            //Dosya yolunu değişkene atıyoruz
            String filePath = "C:/Users/sarid/Desktop/classInfo.xml";

            //dosya yolunu kaydettiğimiz stringi File yapıcısı ile kullanarak nesne oluşturuyoruz.
            File inputFile = new File(filePath);

            //SAXParserFactory örneği oluşturuyoruz
            SAXParserFactory factory=SAXParserFactory.newInstance();

            //SAXParserFactory örneğinden SAXParser objesi oluşturuyoruz.
            SAXParser saxParser = factory.newSAXParser();

            //StudentHandler objesi oluşturuyoruz.
            StudentHandler studentHandler = new StudentHandler();

            //XML dosyasını parse metoduyla ayırştırıyoruz.
            saxParser.parse(inputFile, studentHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class StudentHandler extends DefaultHandler {
    boolean isFirstName = false;
    boolean isLastName = false;
    boolean isMarks = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("student")) {
            String rollNo = attributes.getValue("rollNo");
            System.out.println("Roll No : " + rollNo);
        } else if (qName.equalsIgnoreCase("firstName")) {
            isFirstName = true;
        } else if (qName.equalsIgnoreCase("lastName")) {
            isLastName = true;
        } else if (qName.equalsIgnoreCase("marks")) {
            isMarks = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("student")) {
            System.out.println("End Element:" + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (isFirstName) {
            System.out.println("First Name: "
                    + new String(ch, start, length));
            isFirstName = false;
        } else if (isLastName) {
            System.out.println("Last Name: "
                    + new String(ch, start, length));
            isLastName = false;
        } else if (isMarks) {
            System.out.println("Marks: "
                    + new String(ch, start, length));
            isMarks = false;
        }
    }
}