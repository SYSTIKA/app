package br.com.pgxp.systika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(final String[] args) throws IOException,
                                                        SAXException, TikaException {
        System.out.println("------------ Parsing");

        File diretorio = new File("/home/70744416353/Documentos");
        File fList[] = diretorio.listFiles();

        for (int i = 0; i < fList.length; i++) {
            metadata(fList[i]);
        }


    }

    public static void metadata(File file) throws FileNotFoundException, IOException, SAXException, TikaException {
        InputStream input = new FileInputStream(file);
        ContentHandler handler = new DefaultHandler();

        Metadata metadata = new Metadata();

        Parser parser = new AutoDetectParser();

        ParseContext parseCtx = new ParseContext();

        parser.parse(input, handler, metadata, parseCtx);

        String[] metadataNames = metadata.names();

        System.out.println(file);

        for (String name : metadataNames) {

            System.out.println(name + ": " + metadata.get(name));

        }
        input.close();

    }
}
