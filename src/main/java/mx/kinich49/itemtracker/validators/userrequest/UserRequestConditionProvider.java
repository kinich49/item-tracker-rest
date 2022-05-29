package mx.kinich49.itemtracker.validators.userrequest;

import mx.kinich49.itemtracker.validators.AbstractConditionProvider;
import mx.kinich49.itemtracker.validators.NonNullRequestCondition;
import mx.kinich49.itemtracker.validators.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConditionProvider extends AbstractConditionProvider<UserRequestParameter> {

    private final NonNullRequestCondition nonNullRequestCondition;
    private final UsernameCondition usernameCondition;
    private final PasswordCondition passwordCondition;
    private final UniqueUsernameCondition uniqueUsernameCondition;

    @Autowired
    public UserRequestConditionProvider(NonNullRequestCondition nonNullRequestCondition,
                                        UsernameCondition usernameCondition,
                                        PasswordCondition passwordCondition,
                                        UniqueUsernameCondition uniqueUsernameCondition) {
        this.nonNullRequestCondition = nonNullRequestCondition;
        this.usernameCondition = usernameCondition;
        this.passwordCondition = passwordCondition;
        this.uniqueUsernameCondition = uniqueUsernameCondition;
    }

    @Override
    public void buildConditions(UserRequestParameter parameter) {
        var requestParameter = new RequestParameter(parameter.getUserRequest());

        addCondition(nonNullRequestCondition, requestParameter);
        addCondition(usernameCondition, parameter);
        addCondition(passwordCondition, parameter);
        addCondition(uniqueUsernameCondition, parameter);
    }
}
