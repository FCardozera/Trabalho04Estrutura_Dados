import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ABM implements Map<Integer, Veiculo> {
    private NohABM raiz;

    public ABM() {
        this.raiz = null;
    }

    public ABM(NohABM raiz) {
        this.raiz = raiz;
    }

    public NohABM getRaiz() {
        return raiz;
    }

    public void setRaiz(NohABM raiz) {
        this.raiz = raiz;
    }

    public void clear() {
        raiz = null;
    }

    public int size() {
        return size(raiz);
    }

    private int size(NohABM noh) {
        if (noh == null) {
            return 0;
        }

        return 1 + size(noh.getEsquerda()) + size(noh.getDireita());
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public Veiculo put(Integer key, Veiculo value) {
        raiz = put(raiz, key, value);
        return value;
    }

    private NohABM put(NohABM noh, Integer key, Veiculo value) {
        if (noh == null) {
            return new NohABM(key, value);
        }

        if (key < noh.getChave()) {
            noh.setEsquerda(put(noh.getEsquerda(), key, value));
        } else if (key > noh.getChave()) {
            noh.setDireita(put(noh.getDireita(), key, value));
        } else {
            noh.setVeiculo(value);
        }

        return noh;
    }

    public Veiculo remove(Object key) {
        Veiculo value = get(key);
        raiz = remove(raiz, (Integer) key);
        return value;
    }

    private NohABM remove(NohABM noh, Integer key) {
        if (noh == null) {
            return null;
        }

        if (key < noh.getChave()) {
            noh.setEsquerda(remove(noh.getEsquerda(), key));
        } else if (key > noh.getChave()) {
            noh.setDireita(remove(noh.getDireita(), key));
        } else {
            if (noh.getDireita() == null) {
                return noh.getEsquerda();
            }

            if (noh.getEsquerda() == null) {
                return noh.getDireita();
            }

            NohABM t = noh;
            noh = min(t.getDireita());
            noh.setDireita(removeMin(t.getDireita()));
            noh.setEsquerda(t.getEsquerda());
        }
        return noh;
    }

    private NohABM min(NohABM noh) {
        if (noh.getEsquerda() == null) {
            return noh;
        }
        return min(noh.getEsquerda());
    }

    private NohABM removeMin(NohABM noh) {
        if (noh.getEsquerda() == null) {
            return noh.getDireita();
        }
        noh.setEsquerda(removeMin(noh.getEsquerda()));
        return noh;
    }

    public Veiculo get(Object key) {
        return get(raiz, (Integer) key);
    }

    private Veiculo get(NohABM noh, Integer key) {
        if (noh == null) {
            return null;
        }
        if (noh.getChave() == key) {
            return noh.getVeiculo();
        }
        if (key < noh.getChave()) {
            return get(noh.getEsquerda(), key);
        }
        return get(noh.getDireita(), key);
    }

    public void putAll(Map<? extends Integer, ? extends Veiculo> m) {
        for (Entry<? extends Integer, ? extends Veiculo> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Set<Integer> keySet() {
        Set<Integer> keys = new HashSet<Integer>();
        keySet(raiz, keys);
        return keys;
    }

    private void keySet(NohABM noh, Set<Integer> keys) {
        if (noh == null) {
            return;
        }
        keys.add(noh.getChave());
        keySet(noh.getEsquerda(), keys);
        keySet(noh.getDireita(), keys);
    }

    public Collection<Veiculo> values() {
        ArrayList<Veiculo> values = new ArrayList<Veiculo>();
        values(raiz, values);
        return values;
    }

    private void values(NohABM noh, ArrayList<Veiculo> values) {
        if (noh == null) {
            return;
        }
        values.add(noh.getVeiculo());
        values(noh.getEsquerda(), values);
        values(noh.getDireita(), values);
    }

    public Set<Entry<Integer, Veiculo>> entrySet() {
        Set<Entry<Integer, Veiculo>> entrySet = new HashSet<Entry<Integer, Veiculo>>();
        entrySet(raiz, entrySet);
        return entrySet;
    }

    private void entrySet(NohABM node, Set<Entry<Integer, Veiculo>> entrySet) {
        if (node == null) {
            return;
        }
        Entry<Integer, Veiculo> entry = new AbstractMap.SimpleEntry<>(node.getChave(), node.getVeiculo());
        entrySet.add(entry);
        entrySet(node.getEsquerda(), entrySet);
        entrySet(node.getDireita(), entrySet);
    }

    public boolean containsKey(Object key) {
        return containsKey(raiz, (Integer) key);
    }

    private boolean containsKey(NohABM noh, Integer key) {
        if (noh == null) {
            return false;
        }
        if (noh.getChave() == key) {
            return true;
        }
        if (key < noh.getChave()) {
            return containsKey(noh.getEsquerda(), key);
        }
        return containsKey(noh.getDireita(), key);
    }

    public boolean containsValue(Object value) {
        return containsValue(raiz, (Veiculo) value);
    }

    private boolean containsValue(NohABM noh, Veiculo value) {
        if (noh == null) {
            return false;
        }
        if (noh.getVeiculo().equals(value)) {
            return true;
        }
        return containsValue(noh.getEsquerda(), value) || containsValue(noh.getDireita(), value);
    }

    public int countMarca(String marca) {
        int count = 0;

        count += countMarca(marca, raiz);

        return count;
    }

    private int countMarca(String marca, NohABM noh) {
        if (noh == null) {
            return 0;
        }

        if (noh.getVeiculo().getMarca().equals(marca)) {
            return 1 + countMarca(marca, noh.getEsquerda()) + countMarca(marca, noh.getDireita());
        }

        return countMarca(marca, noh.getEsquerda()) + countMarca(marca, noh.getDireita());
    }

    public void removeVeiculosMenorOuIgualA(int chassi) {
        raiz = removeVeiculosMenorOuIgualA(chassi, raiz);
    }

    private NohABM removeVeiculosMenorOuIgualA(int chassi, NohABM noh) {
        if (noh == null) {
            return null;
        }

        if (noh.getChave() <= chassi) {
            return removeVeiculosMenorOuIgualA(chassi, noh.getDireita());
        }

        noh.setEsquerda(removeVeiculosMenorOuIgualA(chassi, noh.getEsquerda()));
        noh.setDireita(removeVeiculosMenorOuIgualA(chassi, noh.getDireita()));

        return noh;
    }

    public String toString() {
        return toString(raiz);
    }

    private String toString(NohABM noh) {
        if (noh == null) {
            return "";
        }

        StringBuilder resultado = new StringBuilder();

        resultado.append(toString(noh.getEsquerda()));
        resultado.append(noh.toString());
        resultado.append(toString(noh.getDireita()));

        return resultado.toString();
    }
}
