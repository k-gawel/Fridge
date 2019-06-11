package org.california.model.transfer.response;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceUserStatsTest {

    @Test
    public void equalsTest() {

        PlaceUserStats stats1 = new PlaceUserStats(1L, 2L, 3L, 4L);
        PlaceUserStats stats2 = new PlaceUserStats(1L, 2L, 3L, 4L);

        assertEquals(stats1, stats2);

    }

}