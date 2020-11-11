package ui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

//The background music class
/*
References:
        "Hold on a Sec": get from http://www.paypal.me/BryanTeoh
*/
public class Music {
    private String name;           //name of the music
    private File file;
    private URL url = null;
    private URI uri = null;
    private AudioClip clip = null;

    public Music() {
        //this.name = name;
        file = new File("data/Hold on a Sec.wav");
        uri = file.toURI();
        try {
            url = uri.toURL();
            clip = Applet.newAudioClip(url);
        } catch (MalformedURLException e) {
            System.out.println("Something wrong when playing the music");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * MODIFIES:this
     * EFFECTS: looping with the given music
     */
    public void loop() {
        clip.loop();
    }

    /*
     * MODIFIES:this
     * EFFECTS: playing the given music once
     */
    public void play() {
        clip.play();
    }

    /*
     * MODIFIES:this
     * EFFECTS: stop playing the given music
     */
    public void stop() {
        clip.stop();
    }

}