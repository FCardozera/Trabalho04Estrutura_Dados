public class Main {
    public static void main(String[] args) {
        long tempoInicio = 0;
        long tempoFim = 0;
        long duracaoInsercao = 0;
        long duracaoImprimir = 0;
        final int VEICULOS_TOTAL = 100000;

        // TESTE VETOR MAPA
        Vetor_Mapa vetor = new Vetor_Mapa();

        tempoInicio = System.nanoTime();
        for (int i = 0; i < VEICULOS_TOTAL; i++) {
            Veiculo veiculo = new Veiculo();
            vetor.put(veiculo.getChassi(), veiculo);
        }
        tempoFim = System.nanoTime();
        duracaoInsercao = tempoFim - tempoInicio;

        long tempoAdicionarVetor = duracaoInsercao;

        System.out.println("Vetor veículos cadastrados:");

        tempoInicio = System.nanoTime();
        System.out.println(vetor);
        tempoFim = System.nanoTime();
        duracaoImprimir = tempoFim - tempoInicio;

        long tempoImprimirVetor = duracaoImprimir;

        tempoInicio = System.nanoTime();
        int quantidadeVetor = vetor.countMarca("Ford");
        tempoFim = System.nanoTime();

        long tempoContarMarcaFord = tempoFim - tempoInicio;

        tempoInicio = System.nanoTime();
        vetor.removeVeiculosMenorOuIgualA(202050000);
        tempoFim = System.nanoTime();

        long tempoExcluirChassiMenorIgualQue202050000Vetor_Mapa = tempoFim - tempoInicio;

        // TESTE LISTA DUPLAMENTE ENCADEADA MAPA
        LDE_Mapa lde = new LDE_Mapa();

        tempoInicio = System.nanoTime();
        for (int i = 0; i < VEICULOS_TOTAL; i++) {
            Veiculo veiculo = new Veiculo();
            lde.put(veiculo.getChassi(), veiculo);
        }
        tempoFim = System.nanoTime();
        duracaoInsercao = tempoFim - tempoInicio;

        long tempoAdicionarLde = duracaoInsercao;

        System.out.println("Lista Duplamente Encadeada veículos cadastrados:");

        tempoInicio = System.nanoTime();
        System.out.println(lde.imprimeInicio());
        tempoFim = System.nanoTime();
        duracaoImprimir = tempoFim - tempoInicio;

        long tempoImprimirLde = duracaoImprimir;

        tempoInicio = System.nanoTime();
        int quantidadeLde = lde.countMarca("Ford");
        tempoFim = System.nanoTime();

        long tempoTotalContarMarcaFordLde = tempoFim - tempoInicio;

        tempoInicio = System.nanoTime();
        lde.removeVeiculosMenorOuIgualA(202050000);
        tempoFim = System.nanoTime();

        long tempoTotalExcluirChassiMenorIgualQue202050000LDE_Mapa = tempoFim - tempoInicio;

        // TESTE ÁRVORE BINÁRIA MAPA
        ABM abm = new ABM();

        tempoInicio = System.nanoTime();
        for (int i = 0; i < VEICULOS_TOTAL; i++) {
            Veiculo veiculo = new Veiculo();
            abm.put(veiculo.getChassi(), veiculo);
        }
        tempoFim = System.nanoTime();
        duracaoInsercao = tempoFim - tempoInicio;

        long tempoAdicionarVeiculos = duracaoInsercao;

        System.out.println("Árvore Binária veículos cadastrados:");

        tempoInicio = System.nanoTime();
        System.out.println(abm);
        tempoFim = System.nanoTime();
        duracaoImprimir = tempoFim - tempoInicio;

        long tempoImprimirVeiculos = duracaoImprimir;

        tempoInicio = System.nanoTime();
        int quantidadeAb = abm.countMarca("Ford");
        tempoFim = System.nanoTime();

        long tempoContarMarcaFordAb = tempoFim - tempoInicio;

        tempoInicio = System.nanoTime();
        abm.removeVeiculosMenorOuIgualA(202050000);
        tempoFim = System.nanoTime();

        long tempoExcluirChassiMenorIgualQue202050000ABM = tempoFim - tempoInicio;

        System.out.println();
        System.out.println("Vetor");
        System.out.println("Tempo para adicionar todos os veículos: " + tempoAdicionarVetor + " nanosegundos");
        System.out.println("Tempo para imprimir todos os veículos: " + tempoImprimirVetor + " nanosegundos");
        System.out.println("Tempo para contar veículos da marca Ford: " + tempoContarMarcaFord + " nanosegundos");
        System.out.println("Quantidade de veículos da marca Ford: " + quantidadeVetor);
        System.out.println("Tempo para excluir veículos com chassi menor que 202050000: "
                + tempoExcluirChassiMenorIgualQue202050000Vetor_Mapa + " nanosegundos");
        System.out.println();

        System.out.println();
        System.out.println("Lista Duplamente Encadeada");
        System.out.println("Tempo para adicionar todos os veículos: " + tempoAdicionarLde + " nanosegundos");
        System.out.println("Tempo para imprimir todos os veículos: " + tempoImprimirLde + " nanosegundos");
        System.out.println("Tempo para contar veículos da marca Ford: " + tempoTotalContarMarcaFordLde + " nanosegundos");
        System.out.println("Quantidade de veículos da marca Ford: " + quantidadeLde);
        System.out.println("Tempo para excluir veículos com chassi menor que 202050000: "
                + tempoTotalExcluirChassiMenorIgualQue202050000LDE_Mapa + " nanosegundos");
        System.out.println();

        System.out.println();
        System.out.println("Árvore Binária");
        System.out.println("Tempo para adicionar todos os veículos: " + tempoAdicionarVeiculos + " nanosegundos");
        System.out.println("Tempo para imprimir todos os veículos: " + tempoImprimirVeiculos + " nanosegundos");
        System.out.println("Tempo para contar veículos da marca Ford: " + tempoContarMarcaFordAb + " nanosegundos");
        System.out.println("Quantidade de veículos da marca Ford: " + quantidadeAb);
        System.out.println("Tempo para excluir veículos com chassi menor que 202050000: "
                + tempoExcluirChassiMenorIgualQue202050000ABM + " nanosegundos");
        System.out.println();
    }
}