package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip; // We use 'Clip' to import audio files
    URL audioAddress[] = new URL[30]; // We use this URL array to find the location of multiple audio files

    // Every value in the audioAddress[] array represents a different audio file and location, or "URL"

    public Sound()
    {
        audioAddress[0] = getClass().getResource("/sound/MysticalJam.wav");
        audioAddress[1] = getClass().getResource("/sound/PowerUp.wav");
        audioAddress[2] = getClass().getResource("/sound/Coin.wav");
        audioAddress[3] = getClass().getResource("/sound/Retro Arcade LoFi.wav");
        audioAddress[4] = getClass().getResource("/sound/Unlock.wav");
        audioAddress[5] = getClass().getResource("/sound/HarvestEffect.wav");
        audioAddress[6] = getClass().getResource("/sound/SwimEffect.wav");
    }

    // We will need functions to determine what audio to play, loop, and stop

    public void setFile(int i)
    {
        // This setFile function follows the "format" used in Java to open audio files

        try
        {
            // We use 'AudioInputStream' to fetch the index 'i' of our audioAddress array
            AudioInputStream fetchAudio = AudioSystem.getAudioInputStream(audioAddress[i]);
            clip = AudioSystem.getClip(); // 'Clip' would represent the audio file being used or accessed
            clip.open(fetchAudio);

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void play()
    {
        if (clip  != null)
        {
            clip.start(); // Begin playing the audio file being opened
        }
    }

    public void loop()
    {
        if (clip != null)
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the audio file being played
        }
    }

    public void stop()
    {
        if (clip != null)
        {
            clip.stop(); // Stop the audio file being played
        }
    }
}
