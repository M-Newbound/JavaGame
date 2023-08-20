package com.controller.world;

import javax.swing.JFrame;
import com.controller.ui.UserInterface;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestUserInterface {

	
    @Test
    public void testChangeFrameState() {
        UserInterface userInterface = new UserInterface();
        JFrame newFrame = new JFrame();

        userInterface.changeFrameState(newFrame);

        assertEquals(newFrame.getContentPane(), userInterface.getFrame().getContentPane());
    }

}
