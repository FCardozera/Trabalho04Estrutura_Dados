public class NohABM {

    private int chave;
    private Veiculo veiculo;
    private NohABM esquerda;
    private NohABM direita;
    private NohABM pai;

    public NohABM(Integer chave, Veiculo valor) {
        this.chave = chave;
        this.veiculo = valor;
    }

    public NohABM getEsquerda() {
        return esquerda;
    }

    public NohABM getDireita() {
        return direita;
    }

    public NohABM getPai() {
        return pai;
    }

    public int getChave() {
        return chave;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public void setEsquerda(NohABM esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(NohABM direita) {
        this.direita = direita;
    }

    public void setPai(NohABM pai) {
        this.pai = pai;
    }

    
}