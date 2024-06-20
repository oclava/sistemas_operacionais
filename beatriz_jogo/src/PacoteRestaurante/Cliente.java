package PacoteRestaurante;

// Declaração do pacote onde a classe Cliente está contida

public class Cliente implements Runnable {
    // Declaração da classe Cliente que implementa a interface Runnable,
    // permitindo que os objetos desta classe possam ser executados por uma thread
    
    private final int mesa;
    private final Restaurante restaurante;

    // Atributos privados e finais que armazenam o número da mesa e a referência ao objeto Restaurante

    public Cliente(int mesa, Restaurante restaurante) {
        this.mesa = mesa;
        this.restaurante = restaurante;
    }

    // Construtor da classe Cliente que inicializa os atributos mesa e restaurante

    @Override
    public void run() {
        try {
            // Método run que será executado quando a thread associada ao objeto Cliente for iniciada
            restaurante.atendimentoCliente(mesa);
            // Simula o atendimento ao cliente na mesa especificada
            restaurante.preparoPedido(mesa);
            // Simula o preparo do pedido para a mesa especificada
            restaurante.entregaPedido(mesa);
            // Simula a entrega do pedido na mesa especificada
            restaurante.pagamentoConta(mesa);
            // Simula o pagamento da conta na mesa especificada
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Captura e trata a exceção InterruptedException, que pode ser lançada
            // se a thread for interrompida enquanto está esperando ou dormindo
        }
    }
}
