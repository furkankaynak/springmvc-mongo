package com.challenge.mvc.services.impl;

import com.challenge.mvc.services.IReCaptchaService;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 * Created by furkan on 5/15/14.
 *
 * This class implementation of IReCaptchaService.
 * Class must implements with IReCaptchaService
 *
 * @see com.challenge.mvc.services.IReCaptchaService
 */
public class ReCaptchaService implements IReCaptchaService {

    private ReCaptchaImpl reCaptcha;

    /**
     * This method be control captcha is valid or not valid.
     *
     * @param remoteAddr the user's ip adress on site.
     * @param challenge user text for the capthca.
     * @param capthcaResponse real captcha text as encrypted
     * @return true if challenge and captcha response is pair else false
     */
    @Override
    public boolean controlCaptcha(String remoteAddr, String challenge, String capthcaResponse) {
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, capthcaResponse);
        return reCaptchaResponse.isValid();
    }

    public void setReCaptcha(ReCaptchaImpl reCaptcha) {
        this.reCaptcha = reCaptcha;
    }
}
