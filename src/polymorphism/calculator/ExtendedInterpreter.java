package polymorphism.calculator;

public class ExtendedInterpreter extends InputInterpreter {

    private MemoryStorageOperation memoryStorageOperation;

    public ExtendedInterpreter(CalculationEngine engine) {
        super(engine);
        this.memoryStorageOperation = new MemoryStorageOperation();
    }

    @Override
    public Operation getOperation(String operation) {
        switch (operation) {
            case "/":
                return new DivideOperation();
            case "mr":
                return new MemoryRecallOperation(memoryStorageOperation);
            case "ms":
                return this.memoryStorageOperation;
            default:
                return super.getOperation(operation);
        }
    }
}
