package ru.raiffeisen.pfrxml;

import ru.raiffeisen.pfrxml.model.Pack;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.raiffeisen.pfrxml.model.AbstractBaseEntity.START_SEQ;
import static ru.raiffeisen.pfrxml.UserTestData.user;
import static ru.raiffeisen.pfrxml.UserTestData.admin;

public class PackTestData {
        public static final TestMatcher<Pack> PACK_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Pack.class, "user", "dataFiles");

        public static final int NOT_FOUND = 10;
        public static final int PACK1_ID = START_SEQ + 3;
        public static final int ADMIN_PACK_ID = START_SEQ + 2;

        public static final Pack adminPack = new Pack(ADMIN_PACK_ID, "223121903.001", of(2020, Month.DECEMBER, 19, 15, 17), false);
        public static final Pack pack1 = new Pack(PACK1_ID, "223122001.001", of(2020, Month.DECEMBER, 20, 16, 02), true);
        public static final Pack pack2 = new Pack(PACK1_ID + 1, "223122102.001", of(2020, Month.DECEMBER, 22, 10, 34), false);

        public static final List<Pack> userPacks = List.of(pack2, pack1);
        public static final List<Pack> packs = List.of(pack2, pack1, adminPack);

        static {
                pack1.setUser(user);
                pack2.setUser(user);
                adminPack.setUser(admin);

        }

        public static Pack getNewPack() {
            return new Pack(null, "223122203.001", of(2020, Month.DECEMBER, 22, 17, 40), false);
        }

        public static Pack getUpdated() {
            return new Pack(PACK1_ID, "223122203.111", pack1.getLoaded().plus(2, ChronoUnit.MINUTES), false);
        }
    }
