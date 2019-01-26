package com.lunatech.imdbq.utils;

import java.util.Objects;

public class BinaryHeap {

    public static class Node implements Comparable<Node> {
        public String tconst;
        public Integer dist;

        public Node(String tconst, int newDist) {
            this.tconst = tconst;
            this.dist = newDist;
        }

        public Node(String tconst) {
            this(tconst, Integer.MAX_VALUE );
        }

        @Override
        public int compareTo(Node o) {
            return dist.compareTo(o.dist);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(tconst, node.tconst);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tconst);
        }
    }


    Node[] heap;
    int size;
    public BinaryHeap(int n) {
        heap = new Node[n];
    }
    // build heap in O(n)
    public BinaryHeap(Node[] values) {
        heap = values.clone();
        size = values.length;
        for (int pos = size / 2 - 1; pos >= 0; pos--)
            down(pos);
    }
    public Node removeMin() {
        if (size == 0) return null;
        Node removed = heap[0];
        heap[0] = heap[--size];
        down(0);
        return removed;
    }
    public void add(Node value) {
        heap[size] = value;
        up(size++);
    }
    void up(int pos) {
        while (pos > 0) {
            int parent = (pos - 1) / 2;
            if (heap[pos].compareTo(heap[parent]) >= 0)
                break;
            swap(pos, parent);
            pos = parent;
        }
    }
    void down(int pos) {
        while (true) {
            int child = 2 * pos + 1;
            if (child >= size)
                break;
            if (child + 1 < size && heap[child + 1].compareTo(heap[child]) < 0)
                ++child;
            if (heap[pos].compareTo(heap[child]) <= 0)
                break;
            swap(pos, child);
            pos = child;
        }
    }
    void swap(int i, int j) {
        Node t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

}
