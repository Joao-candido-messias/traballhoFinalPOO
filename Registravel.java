/**
 * Interface que define o contrato de registro para entidades do diario musical.
 *
 * <@Qualquer classe que deseje participar do fluxo de cadastro do sistema
 * deve implementar este contrato, garantindo que o metodo de registro
 * esteja sempre disponivel.</p>
 */
public interface Registravel {

    /**
     * Realiza o registro da entidade no sistema.
     *
     * @throws Exception se ocorrer qualquer erro durante o registro
     */
    void registrar() throws Exception;
}