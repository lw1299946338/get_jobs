package com.getjobs.worker.boss;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BossBlacklistTest {

    @Test
    void matchesHeadhunterPositionKeyword() {
        assertEquals("猎头顾问", Boss.findMatchedTerm(Set.of("猎头顾问"), "猎头顾问"));
        assertEquals("猎头顾问", Boss.findMatchedTerm(Set.of("猎头顾问"), "资深猎头顾问"));
    }

    @Test
    void doesNotMatchOtherHrPositions() {
        assertNull(Boss.findMatchedTerm(Set.of("猎头顾问"), "招聘经理"));
        assertNull(Boss.findMatchedTerm(Set.of("猎头顾问"), "技术总监"));
    }

    @Test
    void ignoresBlankKeywordsAndMissingPosition() {
        assertNull(Boss.findMatchedTerm(Arrays.asList("", "  ", null), "猎头顾问"));
        assertNull(Boss.findMatchedTerm(Set.of("猎头顾问"), ""));
        assertNull(Boss.findMatchedTerm(Set.of("猎头顾问"), null));
        assertNull(Boss.findMatchedTerm(null, "猎头顾问"));
    }

    @Test
    void trimsConfiguredKeywordBeforeMatching() {
        assertEquals("猎头顾问", Boss.findMatchedTerm(Set.of(" 猎头顾问 "), "资深猎头顾问"));
    }
}
