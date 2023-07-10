public class Noh {
    private Veiculo info;
    private int chave;
    private Noh anterior;
    private Noh proximo;

    public Noh (Veiculo info) {
        this.info = info;
        this.chave = info.getChassi();
        this.anterior = null;
        this.proximo = null;
    }

    public int getChave() {
        return chave;
    }

    public Veiculo getInfo() {
        return info;
    }

    public Noh getProximo() {
        return proximo;
    }

    public Noh getAnterior() {
        return anterior;
    }

    public void setAnterior(Noh anterior) {
        this.anterior = anterior;
    }

    public void setInfo(Veiculo info) {
        this.info = info;
    }

    public void setProximo(Noh proximo) {
        this.proximo = proximo;
    }
}
