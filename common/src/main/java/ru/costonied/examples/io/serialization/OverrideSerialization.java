package ru.costonied.examples.io.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

public class OverrideSerialization {
    private int variable;

    public OverrideSerialization(int variable) {
        this.variable = variable;
    }

    /**
     * Переопределяем сериализацию.
     * Для этого необходимо объявить методы:
     * private void writeObject(ObjectOutputStream out) throws IOException
     * private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     * Теперь сериализация/десериализация пойдет по нашему сценарию :)
     *
     * ВАЖНО: чтобы сработала эта функция при сериализации не нужно сериализовать объект явным вызовом этой функции
     *        т.е.: overrideSerializationObject.writeObject(out) выдаст нам NotActiveException
     *        чтобы функция сработала корректно нужно просто запускать обычную запись объекта в поток
     *        т.е.: out.writeObject(overrideSerializationObject)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        // При сериализации будет использоваться данный метод.
        // Функция defaultWriteObject() запускает стандартный процесс записи при сериализации
        // Т.е. в данном примере мы не навешиваем никакой своей логики, просто показываем как это можно сделать
        out.defaultWriteObject();
        // Т.е. при желании, после стандартной сериализации объекта мы можем запихнуть что-то ещё,
        // Например добавить строку out.writeObject("additional string")
        // А затем, переопределив readObject() можем вычитывать эту строку при десериализации и что-нибудь с ней делать
    }

    /**
     * ВАЖНО: чтобы сработала эта функция при десериализации не нужно десериализовать объект явным вызовом этой функции
     *        т.е.: overrideSerializationObject.readObject(out) выдаст нам NotActiveException
     *        чтобы функция сработала корректно нужно просто запускать обычную запись объекта в поток
     *        т.е.: in.readObject(overrideSerializationObject)
     * @param in объект ObjectInputStream
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    /**
     * Функцию readResolve() нужно определять, когда мы хотим прочитать какой-то свой объект
     * @return объект класса Object
     */
    private Object readResolve() throws ObjectStreamException {
        return new OverrideSerialization(0);
    }

    /**
     * Функцию writeReplace() нужно определять, когда мы хотим записать какой-то свой объект.
     * @return объект класса Object
     */
    private Object writeReplace() throws ObjectStreamException {
        System.out.println("writeReplace");
        return this;
    }

    public static void main(String[] args) {
    }
}
