package sol1.composedmethod;

public class List {
    public void add(Object element) {
        if (readOnly)
            return;
        if (shouldGrow())
            grow();
        addElement(element);
    }
    private boolean shouldGrow() {
        return (size + 1) > elements.length;
    }
    private void grow() {
        Object[] newElements = new Object[elements.length + 10];
        for (int i = 0; i < size; i++)
            newElements[i] = elements[i];
        elements = newElements;
    }
    private void addElement(Object element) {
        elements[size++] = element;
    }
}
