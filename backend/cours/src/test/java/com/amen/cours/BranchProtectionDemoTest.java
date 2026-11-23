package com.amen.cours;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

class BranchProtectionDemoTest {

    @Test
    void demonstrateFailedCI() {
        fail("DEMO: CI volontairement rouge pour tester la protection de master");
    }
}
