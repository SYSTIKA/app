package br.com.pgxp.systika.view;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.com.pgxp.systika.business.ArquivosBC;
import br.com.pgxp.systika.domain.Arquivos;

@ViewController
@PreviousView("./arquivos_list.jsf")
public class ArquivosEditMB extends AbstractEditPageBean<Arquivos, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ArquivosBC arquivosBC;
	
	@Override
	@Transactional
	public String delete() {
		this.arquivosBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.arquivosBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.arquivosBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.arquivosBC.load(getId()));
	}

}