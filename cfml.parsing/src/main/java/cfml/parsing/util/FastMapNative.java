package cfml.parsing.util;

import java.io.Serializable;
import java.util.*;

/**
 * A replacement for javolution.util.FastMap that supports case-insensitive keys. Keys are always strings.
 * This implementation uses standard Java utilities.
 */
public class FastMapNative<V> implements CaseSensitiveMap<String, V>, Serializable, Cloneable {
    static final long serialVersionUID = 1;

    public static final boolean CASE_SENSITIVE = true;
    public static final boolean CASE_INSENSITIVE = false;

    private final boolean caseSensitive;
    private final Map<String, V> map;

    public FastMapNative() {
        this(CASE_SENSITIVE);
    }

    public FastMapNative(boolean isCaseSensitive) {
        this.caseSensitive = isCaseSensitive;
        this.map = new LinkedHashMap<>();
    }

    public FastMapNative(FastMapNative<V> other) {
        this(other.caseSensitive);
        this.map.putAll(other.map);
    }

    public FastMapNative(Map<String, V> otherMap) {
        this(CASE_SENSITIVE);
        putAll(otherMap);
    }

    public FastMapNative(int initialCapacity) {
        this(CASE_SENSITIVE);
        // LinkedHashMap initial capacity
        // Not strictly necessary, but can be used if needed
    }

    @Override
    public Object clone() {
        return new FastMapNative<>(this);
    }

    @Override
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    // Helper to normalize keys for case-insensitive mode
    private String normalizeKey(String key) {
        return caseSensitive ? key : key.toLowerCase(Locale.ROOT);
    }

    // Map-like methods
    public V put(String key, V value) {
        return map.put(normalizeKey(key), value);
    }

    public V get(String key) {
        return map.get(normalizeKey(key));
    }

    public boolean containsKey(String key) {
        return map.containsKey(normalizeKey(key));
    }

    public V remove(String key) {
        return map.remove(normalizeKey(key));
    }

    @Override
    public V remove(Object key) {
        if (key instanceof String) {
            return map.remove(normalizeKey((String) key));
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends V> m) {
        for (Map.Entry<? extends String, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<V> values() {
        return map.values();
    }

    public Set<Map.Entry<String, V>> entrySet() {
        return map.entrySet();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    @Override
    public V get(Object key) {
        if (key instanceof String) {
            return map.get(normalizeKey((String) key));
        }
        return null;
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof String) {
            return map.containsKey(normalizeKey((String) key));
        }
        return false;
    }
}
