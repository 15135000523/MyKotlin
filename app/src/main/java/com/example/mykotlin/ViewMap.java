package com.example.mykotlin;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Objects;

public class ViewMap<K, V> extends HashMap {

    static class ViewNode<K, V> {
        final int hash;
        final K key;
        V value;
        ViewNode<K, V> next;
        ViewNode<K, V> pre;

        public ViewNode(int hash, K key, V value, ViewNode<K, V> next, ViewNode<K, V> pre) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

    }


}
