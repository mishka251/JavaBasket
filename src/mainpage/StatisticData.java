package mainpage;
import java.util.Date;

public class StatisticData {
    int filledFields;
    int errors;
    Date orderTime;

    StatisticData(int filledFields, int errors, Date orderTime) {
        this.filledFields = filledFields;
        this.errors = errors;
        this.orderTime = orderTime;
    }
}
