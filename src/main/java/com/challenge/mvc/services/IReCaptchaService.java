package com.challenge.mvc.services;

import net.tanesha.recaptcha.ReCaptchaImpl;

/**
 * Created by furkan on 5/15/14.
 */
public interface IReCaptchaService {
    public boolean controlCaptcha(final String remoteAddr, final String challenge, final String capthcaResponse);
}
