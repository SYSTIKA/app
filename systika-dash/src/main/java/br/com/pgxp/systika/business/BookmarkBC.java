package br.com.pgxp.systika.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import br.com.pgxp.systika.domain.Bookmark;
import br.com.pgxp.systika.persistence.BookmarkDAO;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import org.xml.sax.SAXException;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {

    private static final long serialVersionUID = 1L;

    @Startup
    @Transactional
    public void load() {
        if (findAll().isEmpty()) {
            insert(new Bookmark("Demoiselle Portal", "http://www.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Demoiselle SourceForge", "http://sf.net/projects/demoiselle"));
            insert(new Bookmark("Twitter", "http://twitter.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Blog", "http://blog.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Wiki", "http://wiki.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Bug Tracking", "http://tracker.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Forum", "http://forum.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("SVN", "http://svn.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Maven", "http://repository.frameworkdemoiselle.gov.br"));
            insert(new Bookmark("Downloads", "http://download.frameworkdemoiselle.gov.br"));
        }
    }

  

}

