import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Vetor_Mapa implements Map<Integer, Veiculo> {
    private Veiculo[] vetorVeiculos;
    private int tamanho;

    public Vetor_Mapa() {
        vetorVeiculos = new Veiculo[100000];
        tamanho = 0;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean containsKey(Object chave) {
        int chassi = (int) chave;
        return (get(chassi) != null);
    }

    public boolean containsValue(Object value) {
        Veiculo veiculo = (Veiculo) value;
        for (int i = 0; i < tamanho; i++) {
            if (vetorVeiculos[i].equals(veiculo)) {
                return true;
            }
        }
        return false;
    }

    public Veiculo get(Object chave) {
        int chassi = (int) chave;
        for (int i = 0; i < size(); i++) {
            if (vetorVeiculos[i].getChassi() == chassi) {
                return vetorVeiculos[i];
            }
        }

        return null;
    }

    public Veiculo put(Integer chave, Veiculo value) {
        if (chave != null && value != null) {
            if (containsKey(chave)) {
                Veiculo v = get(chave);
                v = value;
                return v;
            }

            if (size() == vetorVeiculos.length) {
                vetorVeiculos = Arrays.copyOf(vetorVeiculos, vetorVeiculos.length * 2);
            }

            int insertIndex = 0;
            while (insertIndex < size() && vetorVeiculos[insertIndex].getChassi() < chave) {
                insertIndex++;
            }

            for (int i = size(); i > insertIndex; i--) {
                vetorVeiculos[i] = vetorVeiculos[i - 1];
            }
            vetorVeiculos[insertIndex] = value;
            tamanho++;
        }
        return null;
    }

    public Veiculo remove(Object chave) {
        Integer chassi = (Integer) chave;
        if (!containsKey(chassi)) {
            return null;
        }
        Veiculo v = get(chassi);

        int removeIndex = 0;
        while (removeIndex < size() && vetorVeiculos[removeIndex].getChassi() != chassi) {
            removeIndex++;
        }

        for (int i = removeIndex; i < size() - 1; i++) {
            vetorVeiculos[i] = vetorVeiculos[i + 1];
        }

        vetorVeiculos[size() - 1] = null;
        tamanho--;
        return v;
    }

    public void putAll(Map<? extends Integer, ? extends Veiculo> m) {
        for (Entry<? extends Integer, ? extends Veiculo> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        vetorVeiculos = new Veiculo[100000];
        tamanho = 0;
    }

    public Set<Integer> keySet() {
        Set<Integer> keySet = new HashSet<>();
        for (int i = 0; i < tamanho; i++) {
            keySet.add(vetorVeiculos[i].getChassi());
        }
        return keySet;
    }

    public Collection<Veiculo> values() {
        List<Veiculo> valuesList = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            valuesList.add(vetorVeiculos[i]);
        }
        return valuesList;
    }

    public Set<Entry<Integer, Veiculo>> entrySet() {
        Set<Entry<Integer, Veiculo>> entrySet = new HashSet<>();
        for (int i = 0; i < tamanho; i++) {
            Veiculo veiculo = vetorVeiculos[i];
            Entry<Integer, Veiculo> entry = new Entry<Integer, Veiculo>() {
                @Override
                public Integer getKey() {
                    return veiculo.getChassi();
                }

                @Override
                public Veiculo getValue() {
                    return veiculo;
                }

                @Override
                public Veiculo setValue(Veiculo value) {
                    throw new UnsupportedOperationException("setValue() não é suportado");
                }
            };
            entrySet.add(entry);
        }
        return entrySet;
    }

    public int countMarca(String marca) {
        int count = 0;
        for (int i = 0; i < tamanho; i++) {
            if (vetorVeiculos[i].getMarca().equalsIgnoreCase(marca)) {
                count++;
            }
        }
        return count;
    }

    public void removeVeiculosMenorOuIgualA(int chassi) {
        for(Veiculo veiculo : values()){
            if(veiculo.getChassi() <= chassi){
                remove(veiculo.getChassi());
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size(); i++) {
            sb.append(vetorVeiculos[i].toString());
            if (i < size() - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}