package reflection_and_annotation;

public class AnnotationDemo {

    public static void main(String[] args) {

        Employee employee = new Employee("Kaloyan", 1);
        System.out.println(employee.toString());

        Company companyAnnotation = employee.getClass().getAnnotation(Company.class);

        Company1 company1 = new Company1();
        company1.employeeMap.put(companyAnnotation.name(), employee);

        System.out.printf("Company name: %s\n", companyAnnotation.name());
        System.out.printf("Company city: %s", companyAnnotation.city());
    }
}
