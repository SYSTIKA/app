package br.com.pgxp.systika.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.com.pgxp.systika.business.ArquivosBC;
import br.com.pgxp.systika.domain.Arquivos;

@ViewController
@NextView("./arquivos_edit.jsf")
@PreviousView("./arquivos_list.jsf")
public class ArquivosListMB extends AbstractListPageBean<Arquivos, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ArquivosBC arquivosBC;
	
	@Override
	protected List<Arquivos> handleResultList() {
		return this.arquivosBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				arquivosBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}