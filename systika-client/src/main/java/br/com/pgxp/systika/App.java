package br.com.pgxp.systika;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sun.misc.Cleaner;

/**
 * Hello world!
 *
 */
public class App {


    public static void main(final String[] args) throws IOException,
                                                        SAXException, TikaException, Exception {
        System.out.println("------------ Parsing");
        String path = "http://localhost:8084/systika-server/rest/manutencao";
        ClientRequestFactory factory = new ClientRequestFactory();
        ClientRequest clientRequest = factory.createRequest(path+"/salvar/dasdasd"); 
        

//         NewJerseyClient client = new NewJerseyClient();
//         
//         System.out.println(client.carregar(Arquivos.class, "1", "asda").toString());
         

        File diretorio = new File("/media/escritorio/backup/Users/Paulo/Documents/san german");
        File fList[] = diretorio.listFiles();

        for (int i = 0; i < fList.length; i++) {
        clientRequest.body(MediaType.APPLICATION_JSON, metadata(fList[i])); 
        clientRequest.execute();
         //client.salvar(metadata(fList[i]), "sdasd");
        }

        
        //client.close();
        
      
      ClientRequest cr = factory.createRequest(path+"/carregar/1/dasdasd");  
      Arquivos resposta = cr.getTarget(Arquivos.class); 
      System.out.println(resposta.getLocal());  
        
    }

    public static Arquivos metadata(File file) throws FileNotFoundException, IOException, SAXException, TikaException {
        InputStream input = new FileInputStream(file);
        ContentHandler handler = new DefaultHandler();
        Arquivos arquivos = new Arquivos();

        Metadata metadata = new Metadata();

        Parser parser = new AutoDetectParser();

        ParseContext parseCtx = new ParseContext();

        parser.parse(input, handler, metadata, parseCtx);

        String[] metadataNames = metadata.names();

        System.out.println(file);

        arquivos.setLocal(file.getAbsolutePath());
        arquivos.setHashFile(""+file.hashCode());
        arquivos.setMetadata(metadataNames.toString());
        for (String name : metadataNames) {
            
            System.out.println(name + ": " + metadata.get(name));

        }
        input.close();
        return arquivos;

    }
}
