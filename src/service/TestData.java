package service;

public class TestData {

    private static TestData INSTANCE;

    private TestData() {
    }

    public static TestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestData();
        }
        return INSTANCE;
    }

    public static void importFakeCustomers() {
        service.CustomerService.addCustomer("toilet@girldeer.net", "Jon", "Doe");
        service.CustomerService.addCustomer("apples@oranges.net", "Froot", "Loop");
        service.CustomerService.addCustomer("irene@bohemia.com", "Irene", "Adler");
        service.CustomerService.addCustomer("cptjack@badwolf.com", "Jack", "Harkness");
        service.CustomerService.addCustomer("greensleeves@headless.com", "Ann", "Boleyn");
    }


}
