package br.com.pgxp.systika.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

import br.com.pgxp.systika.domain.Arquivos;
import br.com.pgxp.systika.persistence.ArquivosDAO;

@BusinessController
public class ArquivosBC extends DelegateCrud<Arquivos, Long, ArquivosDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
