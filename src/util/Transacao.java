package util;

public interface Transacao {

	public void executar() throws Exception;

	public void atualizarView();

}
