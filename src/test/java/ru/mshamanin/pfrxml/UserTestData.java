package ru.mshamanin.pfrxml;

import ru.mshamanin.pfrxml.model.AbstractBaseEntity;
import ru.mshamanin.pfrxml.model.Role;
import ru.mshamanin.pfrxml.model.User;
import ru.mshamanin.pfrxml.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "registered", "meals", "password");

    public static TestMatcher<User> USER_WITH_MEALS_MATCHER =
            TestMatcher.usingAssertions(User.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "packs.user", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int ADMIN_ID = AbstractBaseEntity.START_SEQ;
    public static final int USER_ID = AbstractBaseEntity.START_SEQ + 1;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "ruauso2", "Ушакова", "Ольга", "Сергеевна", "+786133333333", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "admsam5", "Шаманин", "Михаил", "Владимирович", "+78312969500", "password", Role.ADMIN, Role.USER);

    static {
        user.setPacks(PackTestData.userPacks);
        admin.setPacks(List.of(PackTestData.adminPack));
    }

    public static User getNew() {
        return new User(null, "ruanew", "Шаманин", "Михаил", "Владимирович", "+78312969500", "password", false, LocalDateTime.now(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setLogin("UpdatedLogin");
        updated.setPhoneNumber("911");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
