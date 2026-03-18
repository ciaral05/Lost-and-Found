package lostandfoundapp;

public interface CRUDOperations<T> {

    void create(T item);

    T read(int id);

    void update(int id, T item);

    void delete(int id);
}
