package com.bohniman.incomingportal.captcha;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.BackgroundProducer;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.noise.NoiseProducer;
import nl.captcha.noise.StraightLineNoiseProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

public class CaptchaGenerator implements InitializingBean {

    private BackgroundProducer backgroundProducer;
    private TextProducer textProducer;
    private WordRenderer wordRenderer;
    private NoiseProducer noiseProducer;

    public Captcha createCaptcha(int width, int height) {

        // List<Color> colors = new ArrayList<Color>();
        // colors.add(Color.black);
        // colors.add(Color.red);

        // // List<Font> fonts = new ArrayList<Font>();
        // // fonts.add(new Font("Comic Sans MS", Font.PLAIN, 40));

        // return new Captcha.Builder(width,
        // height).addBackground(backgroundProducer).addText(textProducer, wordRenderer)
        // .addNoise(noiseProducer)
        // .addBackground(new GradiatedBackgroundProducer(Color.LIGHT_GRAY,
        // Color.LIGHT_GRAY)).addBorder().build();

        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.black);
        colors.add(Color.red);

        // List<Font> fonts = new ArrayList<Font>();
        // fonts.add(new Font("Comic Sans MS", Font.PLAIN, 40));
        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("DejaVu Sans", Font.PLAIN, 40));

        return new Captcha.Builder(170, 50).addText(new DefaultWordRenderer(colors, fonts))
                .addNoise(new StraightLineNoiseProducer()).addNoise().addBackground(new GradiatedBackgroundProducer())
                .addBorder().build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.backgroundProducer == null) {
            this.backgroundProducer = new TransparentBackgroundProducer();
        }
        if (this.textProducer == null) {
            this.textProducer = new DefaultTextProducer();
        }
        if (this.wordRenderer == null) {
            this.wordRenderer = new DefaultWordRenderer();
        }
        if (this.noiseProducer == null) {
            this.noiseProducer = new CurvedLineNoiseProducer();
        }
    }
}
