package com.github.ogapants.playercontrolview;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UtilTest {

    @Test
    public void testFormatTime() throws Exception {
        assertEquals("00:02", Util.formatTime((2) * 1000));
        assertEquals("04:56", Util.formatTime((4 * 60 + 56) * 1000));
        assertEquals("2:34:56", Util.formatTime((2 * 3600 + 34 * 60 + 56) * 1000));
        assertEquals("123:34:56", Util.formatTime((123 * 3600 + 34 * 60 + 56) * 1000));

        assertEquals("00:00", Util.formatTime(0));
        assertEquals("00:00", Util.formatTime(999));
        assertEquals("00:00", Util.formatTime(-1));
    }
}