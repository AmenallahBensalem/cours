package com.amen.cours;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.amen.cours.restcontrollers.HealthController;

class HealthControllerTest {

    @Test
    void statusEstUp() {
        HealthController controller =
                new HealthController("test");

        assertEquals(
                "UP",
                controller.health().get("status")
        );
    }

    @Test
    void versionEstRenvoyee() {
        HealthController controller =
                new HealthController("v1.2.3");

        assertEquals(
                "v1.2.3",
                controller.health().get("version")
        );
    }
}
