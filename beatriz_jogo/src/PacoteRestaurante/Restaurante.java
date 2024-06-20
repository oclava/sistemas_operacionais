package PacoteRestaurante;

import java.util.concurrent.Semaphore;

public class Restaurante {
    private final int numMesas;
    private final Semaphore semaforoGarcons;
    private final Semaphore semaforoCozinheiros;
    private final Semaphore semaforoPedidos;

    // Atributos que representam o número de mesas, semáforos para controle de garçons,
    // cozinheiros e controle de pedidos (pagamento)

    public Restaurante(int numMesas, int numGarcons, int numCozinheiros) {
        this.numMesas = numMesas;
        semaforoGarcons = new Semaphore(numGarcons);
        semaforoCozinheiros = new Semaphore(numCozinheiros);
        semaforoPedidos = new Semaphore(1);
    }

    // Construtor da classe Restaurante que inicializa o número de mesas e os semáforos
    // para controle de acesso aos recursos (garçons, cozinheiros, pedidos)

    public void inicioJogo() {
        Thread[] threadsClientes = new Thread[numMesas];
        for (int i = 0; i < numMesas; i++) {
            threadsClientes[i] = new Thread(new Cliente(i, this));
            threadsClientes[i].start();
        }
        for (Thread thread : threadsClientes) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Jogo de restaurante finalizado!");
    }

    // Método que inicializa as threads para cada cliente (mesa)
    // e aguarda a conclusão de todas elas

    public void atendimentoCliente(int mesa) throws InterruptedException {
        semaforoGarcons.acquire();
        System.out.println("Garçom atendendo cliente na mesa " + mesa);
        Thread.sleep(2000); // Simula atendimento
        semaforoGarcons.release();
    }

    // Método que simula o atendimento ao cliente por um garçom, utilizando semáforo para controlar o acesso

    public void preparoPedido(int mesa) throws InterruptedException {
        semaforoCozinheiros.acquire();
        System.out.println("Cozinheiro preparando pedido da mesa " + mesa);
        Thread.sleep(3000); // Simula preparo do pedido
        semaforoCozinheiros.release();
    }

    // Método que simula o preparo do pedido por um cozinheiro, utilizando semáforo para controlar o acesso

    public void entregaPedido(int mesa) throws InterruptedException {
        semaforoGarcons.acquire();
        System.out.println("Garçom entregando pedido na mesa " + mesa);
        Thread.sleep(1000); // Simula entrega do pedido
        semaforoGarcons.release();
    }

    // Método que simula a entrega do pedido por um garçom, utilizando semáforo para controlar o acesso

    public void pagamentoConta(int mesa) throws InterruptedException {
        semaforoPedidos.acquire();
        System.out.println("Cliente pagando conta na mesa " + mesa);
        Thread.sleep(1500); // Simula pagamento da conta
        semaforoPedidos.release();
    }

    // Método que simula o pagamento da conta pelo cliente, utilizando semáforo para controlar o acesso

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante(5, 2, 3); // Exemplo com 5 mesas, 2 garçons, 3 cozinheiros
        restaurante.inicioJogo();
    }

    // Método principal que cria uma instância do Restaurante e inicia o "jogo" (execução das threads)
}
