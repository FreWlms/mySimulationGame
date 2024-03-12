package simpleui;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sim1.Constants;
import sim1.Simulation;

/**
 * LEGIT
 * 
 * A swing window (JFrame class) containing 1 swing Component
 * in which the simulation is displayed 
 *
 */
@SuppressWarnings("serial")
public class SimLifeWindow extends JFrame {

	/**
	 * The component in which the simulation is displayed
	 * 
	 * @invar | mov != null
	 */
	private final Movie mov;


    public static void create(String title, int width, int height, Simulation sim)
    {
        SwingUtilities.invokeLater(() -> new SimLifeWindow(title, width, height, sim));
    }

    private SimLifeWindow(String title, int width, int height, Simulation sim)
    {
        super(title);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        this.mov = new Movie(Constants.DEFAULT_FRAME_RATE, sim);
        
        this.add(this.mov);
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
        
        
        this.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                // NOP
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                SimLifeWindow.this.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                // NOP
            }
            
        }
        );
  
    }
    
    
    private void keyPressed(KeyEvent e)
    {
        if ( e.getKeyCode() == KeyEvent.VK_SPACE )
        {
            mov.getSim().nextGeneration();
        }
    }
    

    public Movie getMov() {
    	return mov;
    }	
}
