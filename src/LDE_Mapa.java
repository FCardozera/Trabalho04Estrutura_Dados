import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LDE_Mapa implements Map<Integer, Veiculo> {
    private Noh inicio;
    private Noh fim;

    public LDE_Mapa() {
        this.inicio = null;
        this.fim = null;
    }

    public boolean remove(Veiculo info) {
        Noh aux = inicio;

        while (aux != null && aux.getInfo() != info) {
            aux = aux.getProximo();
        }

        if (aux == null) {
            return false;
        }

        if (aux == inicio) {
            inicio = aux.getProximo();
            if (inicio != null) {
                inicio.setAnterior(null);
            } else {
                fim = null;
            }
        } else if (aux.getProximo() == null) {
            aux.getAnterior().setProximo(null);
            fim = aux.getAnterior();
        } else {
            aux.getAnterior().setProximo(aux.getProximo());
            aux.getProximo().setAnterior(aux.getAnterior());
        }
        return true;
    }

    public String imprimeInicio() {
        String retorno = "";

        if (inicio == null && fim == null) {
            retorno = "Lista Vazia!";
            return retorno;
        } else {
            for (Noh i = inicio; i != null; i = i.getProximo()) {
                if (i != null) {
                    retorno += "[" + i.getInfo().toString() + "] ";
                }
            }
            return retorno;
        }
    }

    public String imprimeFim() {
        String retorno = "";

        if (inicio == null && fim == null) {
            retorno = "Lista Vazia!";
            return retorno;
        } else {
            for (Noh i = fim; i != null; i = i.getAnterior()) {
                if (i != null) {
                    retorno += "[" + i.getInfo().toString() + "] ";
                }
            }
            return retorno;
        }
    }

    public int size() {
        int tamanho = 0;
        if (inicio == null) {
            return tamanho;
        } else {
            for (Noh i = inicio; i != null; i = i.getProximo()) {
                if (i != null) {
                    tamanho++;
                }
            }
            return tamanho;
        }
    }

    public boolean isEmpty() {
        if (inicio == null) {
            return true;
        }
        return false;
    }

    public boolean containsKey(Object key) {
        if (key instanceof Integer) {
            Integer chave = (Integer) key;
            Noh aux = inicio;
            while (aux != null) {
                if (aux.getChave() == chave) {
                    return true;
                }
                aux = aux.getProximo();
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        if (value instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) value;
            Noh aux = inicio;
            while (aux != null) {
                if (aux.getInfo().equals(veiculo)) {
                    return true;
                }
                aux = aux.getProximo();
            }
        }
        return false;
    }

    public Veiculo get(Object key) {
        if (key instanceof Integer) {
            Integer chave = (Integer) key;
            Noh aux = inicio;
            while (aux != null) {
                if (aux.getChave() == chave) {
                    return aux.getInfo();
                }
                aux = aux.getProximo();
            }
        }
        return null;
    }

    public Veiculo put(Integer chave, Veiculo value) {
        Noh novo = new Noh(value);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else if (chave <= inicio.getInfo().getChassi()) {
            novo.setProximo(inicio);
            inicio.setAnterior(novo);
            inicio = novo;
        } else if (chave >= fim.getInfo().getChassi()) {
            novo.setAnterior(fim);
            fim.setProximo(novo);
            fim = novo;
        } else {
            Noh aux = inicio.getProximo();
            while (aux != null && chave > aux.getInfo().getChassi()) {
                aux = aux.getProximo();
            }
            novo.setProximo(aux);
            novo.setAnterior(aux.getAnterior());
            aux.getAnterior().setProximo(novo);
            aux.setAnterior(novo);
        }

        return null;
    }

    public Veiculo remove(Object key) {
        if (key instanceof Integer) {
            Integer chave = (Integer) key;
            Noh aux = inicio;
            while (aux != null) {
                if (aux.getChave() == chave) {
                    Veiculo valorRemovido = aux.getInfo();
                    if (aux == inicio) {
                        inicio = aux.getProximo();
                        if (inicio != null) {
                            inicio.setAnterior(null);
                        } else {
                            fim = null;
                        }
                    } else if (aux == fim) {
                        aux.getAnterior().setProximo(null);
                        fim = aux.getAnterior();
                    } else {
                        aux.getAnterior().setProximo(aux.getProximo());
                        aux.getProximo().setAnterior(aux.getAnterior());
                    }
                    return valorRemovido;
                }
                aux = aux.getProximo();
            }
        }
        return null;
    }

    public void putAll(Map<? extends Integer, ? extends Veiculo> m) {
        for (Entry<? extends Integer, ? extends Veiculo> entry : m.entrySet()) {
            Integer chave = entry.getKey();
            Veiculo valor = entry.getValue();
            put(chave, valor);
        }
    }

    public void clear() {
        inicio = null;
        fim = null;
    }

    public Set<Integer> keySet() {
        Set<Integer> chaves = new HashSet<>();
        Noh aux = inicio;
        while (aux != null) {
            chaves.add(aux.getChave());
            aux = aux.getProximo();
        }
        return chaves;
    }

    public Collection<Veiculo> values() {
        List<Veiculo> valores = new ArrayList<>();
        Noh aux = inicio;
        while (aux != null) {
            valores.add(aux.getInfo());
            aux = aux.getProximo();
        }
        return valores;
    }

    public Set<Entry<Integer, Veiculo>> entrySet() {
        Set<Entry<Integer, Veiculo>> entradas = new HashSet<>();
        Noh aux = inicio;
        while (aux != null) {
            Entry<Integer, Veiculo> entrada = new AbstractMap.SimpleEntry<>(aux.getChave(), aux.getInfo());
            entradas.add(entrada);
            aux = aux.getProximo();
        }
        return entradas;
    }

    private int buscaBinaria(int chassi) {
        int inicio = 0;
        int fim = size() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            Noh aux = getNodeAtIndex(meio);

            if (aux.getInfo().getChassi() == chassi) {
                return meio;
            } else if (aux.getInfo().getChassi() < chassi) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return -1;
    }

    public int countMarca(String marca) {
        int count = 0;
        Noh aux = inicio;

        while (aux != null) {
            if (aux.getInfo().getMarca().equals(marca)) {
                count++;
            }
            aux = aux.getProximo();
        }

        return count;
    }

    public void removeVeiculosMenorOuIgualA(int chassi) {
        int index = buscaBinaria(chassi);

        if (index != -1) {
            for (int i = index; i >= 0; i--) {
                Noh node = getNodeAtIndex(i);
                if (node.getInfo().getChassi() <= chassi) {
                    remove(node.getInfo());
                } else {
                    break;
                }
            }
        }
    }

    private Noh getNodeAtIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Índice inválido.");
        }

        Noh aux = inicio;
        int currentIndex = 0;

        while (currentIndex < index) {
            aux = aux.getProximo();
            currentIndex++;
        }

        return aux;
    }

}
