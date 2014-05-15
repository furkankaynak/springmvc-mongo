package com.challenge.mvc.services;

import util.AbstractTest;
import com.challenge.mvc.services.impl.ReCaptchaService;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReCaptchaServiceTest extends AbstractTest {

    private ReCaptchaService reCaptchaService;
    private ReCaptchaImpl reCaptcha;
    private ReCaptchaResponse reCaptchaResponse;

    @Before
    public void setup() {
        reCaptchaService = spy(new ReCaptchaService());
        reCaptcha = mock(ReCaptchaImpl.class);
        reCaptchaResponse = mock(ReCaptchaResponse.class);

        reCaptchaService.setReCaptcha(reCaptcha);
    }

    @Test
    public void controlCaptchaTestOk() {
        doReturn(reCaptchaResponse).when(reCaptcha).checkAnswer(anyString(), anyString(), anyString());
        doReturn(true).when(reCaptchaResponse).isValid();

        boolean isValid = reCaptchaService.controlCaptcha("a", "b", "c");

        verify(reCaptcha, times(1)).checkAnswer(eq("a"), eq("b"), eq("c"));
        verify(reCaptchaResponse, times(1)).isValid();
        assertEquals(true, isValid);
    }

    @Test
    public void controlCaptchaTestFail() {
        doReturn(reCaptchaResponse).when(reCaptcha).checkAnswer(anyString(), anyString(), anyString());
        doReturn(false).when(reCaptchaResponse).isValid();

        boolean isValid = reCaptchaService.controlCaptcha("a", "b", "c");

        verify(reCaptcha, times(1)).checkAnswer(eq("a"), eq("b"), eq("c"));
        verify(reCaptchaResponse, times(1)).isValid();
        assertEquals(false, isValid);
    }
}
