package org.grizz.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UserCounterTest {
    private UserCounter userCounter;

    @Before
    public void initUserCounter() {
        userCounter = new UserCounter();
    }

    @Test
    public void shouldReturnNullUpdatedWhenNotUsed() {
        assertThat(userCounter.getUpdated()).isNull();
    }

    @Test
    public void shouldReturnZeroCountWhenNotUsed() {
        assertThat(userCounter.getCount()).isEqualTo(0);
    }

    @Test
    public void shouldReturnLastCounterSet() {
        final int firstValue = 100;
        final int secondValue = 200;

        userCounter.setCount(firstValue);
        userCounter.setCount(secondValue);

        assertThat(userCounter.getCount()).isEqualTo(secondValue);
    }
}