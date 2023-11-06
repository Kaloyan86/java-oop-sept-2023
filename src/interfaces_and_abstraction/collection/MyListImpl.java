package interfaces_and_abstraction.collection;

public class MyListImpl extends Collection implements MyList {

    @Override
    public String remove() {
        return super.removeFirst();
    }

    @Override
    public int add(String item) {
        super.addFirst(item);
        return 0;
    }

    @Override
    public int getUsed() {
        return super.getItems().size();
    }
}
