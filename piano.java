import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class piano{
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        makeFrame();
    }

    static JTextField field;
    static JLabel text;
    static JButton button;
    static JFrame frame = new JFrame();
    static JProgressBar progressBar;
    public static void makeFrame(){

        progressBar = new JProgressBar(SwingConstants.HORIZONTAL ,0,100);
        progressBar.setBounds(100, 320, 400, 50);
        progressBar.setSize(200, 50);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        frame.add(progressBar);

        frame.setTitle("LEARN PIANO");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel text3 = new JLabel();
        text3.setSize(400, 100);
        text3.setText("PRACTICE NOTES");
        text3.setBounds(100, 50, 400, 50);
        Font fo3 = new Font("Serif", Font.BOLD, 30);
        text3.setFont(fo3);
        frame.add(text3);

        button = new JButton("GENERATE");
        button.setSize(300, 35);
        button.setBounds(200, 130, 120, 50);
        
        JLabel text2 = new JLabel();
        text2.setSize(400, 100);
        text2.setText("Play the following notes: ");
        text2.setBounds(100, 200, 400, 50);
        Font fo2 = new Font("Serif", Font.ITALIC, 20);
        text2.setFont(fo2);
        frame.add(text2);
        field = new JTextField(16);
        field.setSize(300, 35);
        field.setBounds(100, 130, 90, 50);
        frame.add(button);
        frame.add(field);



        text = new JLabel();
        text.setSize(400, 100);
        text.setText("NONE");
        text.setBounds(100, 250, 400, 50);
        Font fo = new Font("Serif", Font.BOLD, 30);
        text.setFont(fo);


        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String keys = field.getText();
                int num = Integer.parseInt(keys);

                try { 
                    if(progressBar.getValue() == 100){
                        progressBar.setValue(0);
                    }
                    beep();
                    Thread.sleep(1000);
                    text.setText(randKeys(num));
                    progressBar.setValue(progressBar.getValue()+5);
                    
                    
                } catch (InterruptedException | UnsupportedAudioFileException | IOException| LineUnavailableException e1) {
                    e1.printStackTrace();
                } 
                                             
            }  
        });

        frame.add(text);
        frame.setLayout(null);
        frame.setVisible(true); 
    }

    

    public static String randKeys(int num){
        HashMap<Integer, String> map= new HashMap<>();
        map.put(0, " A ");
        map.put(1, " B ");
        map.put(2, " C ");
        map.put(3, " D ");
        map.put(4, " E ");
        map.put(5, " F ");
        map.put(6, " G ");
        
        StringBuilder string = new StringBuilder();
        for(int i=0; i< num; i++){
            double f = Math.random()/Math.nextDown(1.0);
            double x = 0*(1.0 - f) + 7*f;
            int number = (int)x; 
            string.append(map.get(number));
        }
        return string.toString();
    }



    public static void beep() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException{
        String filePath = "C:\\Users\\hariom\\OneDrive\\Desktop\\CS\\funprojects\\beep.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        Clip clip;
        
        clip = AudioSystem.getClip();
        clip.stop();
        clip.close();
        clip.setMicrosecondPosition(0);         
  
        clip.open(audioInputStream);
        clip.start();
    }

}
