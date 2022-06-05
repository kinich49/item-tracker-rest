package mx.kinich49.itemtracker.validators;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public abstract class AbstractConditionProvider<P extends ValidatorParameter> implements ConditionProvider<P> {

    protected final ThreadLocal<Queue<Pair<Condition<? extends ConditionParameter>, ? extends ConditionParameter>>>
            threadLocal = ThreadLocal.withInitial(ArrayDeque::new);

    @Override
    public Pair<Condition<? extends ConditionParameter>, ? extends ConditionParameter> getNextCondition() {
        return threadLocal.get().poll();
    }

    @Override
    public boolean hasNextCondition() {
        return !Objects.isNull(threadLocal.get()) &&
                !Objects.isNull(threadLocal.get().peek());
    }

    protected void addCondition(Condition<? extends ConditionParameter> condition,
                                ConditionParameter conditionParameter) {
        var queue = threadLocal.get();
        queue.add(Pair.of(condition, conditionParameter));
    }
}
