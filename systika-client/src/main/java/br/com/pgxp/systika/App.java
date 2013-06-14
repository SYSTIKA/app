package br.com.pgxp.systika;

import br.com.pgxp.systika.dao.ArquivoJpaController;
import br.com.pgxp.systika.dao.MetadadosJpaController;
import br.com.pgxp.systika.dao.exceptions.PreexistingEntityException;
import br.com.pgxp.systika.domain.Arquivo;
import br.com.pgxp.systika.domain.Metadados;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
    
   private static int NUM_OF_TASKS = 48;

   public App() {}

//   public void run() {
//      long begTest = new java.util.Date().getTime();
//
//      List< Future > futuresList = new ArrayList< Future >();
//      int nrOfProcessors = Runtime.getRuntime().availableProcessors();
//      ExecutorService eservice = Executors.newFixedThreadPool(nrOfProcessors);
//
//      for(int index = 0; index < NUM_OF_TASKS; index++)
//         futuresList.add(eservice.submit(new Task(index)));
//
//         Object taskResult;
//         for(Future future:futuresList) {
//            try {
//               taskResult = future.get();
//               System.out.println("result "+taskResult);
//         }
//         catch (InterruptedException e) {}
//         catch (ExecutionException e) {}
//      }
//      Double secs = new Double((new java.util.Date().getTime() - begTest)*0.001);
//      System.out.println("run time " + secs + " secs");
//    }


    static ArquivoJpaController ajc = new ArquivoJpaController();
    static MetadadosJpaController mjc = new MetadadosJpaController();

    public static void main(final String[] args) throws IOException,
            SAXException, TikaException, Exception {
        System.out.println("------------ Parsing-----------------");

        List<File> fList = selecionaDir("/media/escritorio/backup/Users/Paulo");

        for (File file : fList) {
            metadata(file);
        }
    }

    public static void metadata(File file) throws FileNotFoundException, IOException, SAXException, TikaException, PreexistingEntityException, Exception {
        InputStream input = new FileInputStream(file);
        ContentHandler handler = new DefaultHandler();
        Arquivo arquivo = new Arquivo();

        Metadata metadata = new Metadata();

        Parser parser = new AutoDetectParser();

        ParseContext parseCtx = new ParseContext();

        parser.parse(input, handler, metadata, parseCtx);

        String[] metadataNames = metadata.names();

        arquivo.setLocal(file.getParent());
        arquivo.setNome(file.getAbsoluteFile().getName());
        arquivo.setTamanho(file.length());
        arquivo.setDatamodificado(file.lastModified());
        arquivo.setHashfile("" + file.hashCode());
        ajc.create(arquivo);

        for (String name : metadataNames) {
            if (metadata.get(name).length() <= 1023) {
                Metadados metadados = new Metadados();
                metadados.setMeta(name.trim() + ": " + metadata.get(name).trim());
                metadados.setIdarquivo(arquivo.getId());
                mjc.create(metadados);
            } else{
                System.out.println(file.getAbsoluteFile()+ ": " +name + ": " + metadata.get(name));
            }


        }


        input.close();
        //return arquivo;

    }

    private static List<File> selecionaDir(String path) throws TikaException, IOException, SAXException {

        List<File> lista = new ArrayList<File>();

        File diretorio = new File(path);

        for (File file : diretorio.listFiles()) {
            if (file.isFile()) {
                lista.add(file);
            }
            if (file.isDirectory()) {
                lista.addAll(selecionaDir(file.getCanonicalPath()));
            }

        }

        return lista;

    }
}
