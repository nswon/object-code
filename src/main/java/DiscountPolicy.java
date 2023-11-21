import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        return conditions.stream()
                .filter(each -> each.isSatisfiedBy(screening))
                .findFirst()
                .map(condition -> getDiscountAmount(screening))
                .orElse(Money.ZERO);
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
