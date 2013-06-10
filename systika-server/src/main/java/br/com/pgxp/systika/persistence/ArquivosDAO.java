package br.com.pgxp.systika.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.com.pgxp.systika.domain.Arquivos;

@PersistenceController
public class ArquivosDAO extends JPACrud<Arquivos, Long> {

	private static final long serialVersionUID = 1L;
	

}
