package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] data = {
                "13.07.2019 John 60 50",
                "15.07.2019 Andrew 3 200",
                "15.07.2019 Kate 10 100",

                "16.07.2019 Andrew 3 200",
                "16.07.2019 Kate 9 100",

                "10.08.2019 John 7 100",
                "08.08.2019 Kate 3 80",
                "11.08.2019 Andrew 8 100"
        };
        String[] names = {"John", "Andrew", "Kate"};
        SalaryInfo salaryInfo = new SalaryInfo();
        String resultS = salaryInfo.getSalaryInfo(names, data, "14.07.2019", "10.08.2019");
        System.out.println(resultS);
    }
}
