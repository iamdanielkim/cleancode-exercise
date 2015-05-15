package ex1.composedmethod;

/**
 * Created by iamdanielkim on 15. 5. 15..
 */
public class List {
    public void add(Object element) {
        if (!readOnly) {
            int newSize = size + 1;
            if (newSize > elements.length) {
                Object[] newElements =
                        new Object[elements.length + 10];
                for (int i = 0; i < size; i++)
                    newElements[i] = elements[i];
                elements = newElements;
            }
            elements[size++] = element;
        }
    }
}
