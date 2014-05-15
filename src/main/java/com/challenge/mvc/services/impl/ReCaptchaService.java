package com.challenge.mvc.services.impl;

import com.challenge.mvc.services.IReCaptchaService;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 * Created by furkan on 5/15/14.
 */
public class ReCaptchaService implements IReCaptchaService {

    private ReCaptchaImpl reCaptcha;

    @Override
    public boolean controlCaptcha(String remoteAddr, String challenge, String capthcaResponse) {
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, capthcaResponse);
        return reCaptchaResponse.isValid();
    }

    public void setReCaptcha(ReCaptchaImpl reCaptcha) {
        this.reCaptcha = reCaptcha;
    }
}
