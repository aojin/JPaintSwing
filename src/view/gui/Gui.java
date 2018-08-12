package view.gui;

import view.EventName;
import view.interfaces.IDialogChoice;
import view.interfaces.IEventCallback;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import javax.swing.*;

// Gui is the container class for the contentPane Panel made concrete in GuiWindow

// this class introduces the ActionListener and ActionEvent per Button.

public class Gui implements IUiModule { // The command Invoker

    private final IGuiWindow gui;

    public Gui(IGuiWindow gui) {
        this.gui = gui;
    }

	@Override // the equivalent of "on button pushed", .run is activated per the callback

    // the client using the IEventCallback interface
	public void addEvent(EventName eventName, IEventCallback callback) { // events generated from view get pushed to controller by .run()
		JButton button = gui.getButton(eventName);
		System.out.println(eventName);

		//callback is a call to the lambda in JController...
            button.addActionListener((ActionEvent) -> {
                if (eventName.equals(EventName.DELETE)){
                    System.out.println("\n\nClicked delete...");
                }
                if (eventName.equals(EventName.UNDO)){
                    System.out.println("\n\nClicked undo...");
                }
                if (eventName.equals(EventName.REDO)){
                    System.out.println("\n\nClicked redo...");
                }
                callback.run();
            });
        } // the button is the invoker. The invoker calls the execution of encapsulated invocation.


    @Override
    public <T> T getDialogResponse(IDialogChoice dialogSettings) { // view gets data from the model.
        Object selectedValue = JOptionPane.showInputDialog(null,
                dialogSettings.getDialogText(), dialogSettings.getDialogTitle(),
                JOptionPane.PLAIN_MESSAGE,
                null,
                dialogSettings.getDialogOptions(),
                dialogSettings.getCurrentSelection());
        return selectedValue == null
                ? (T)dialogSettings.getCurrentSelection()
                : (T)selectedValue;
    }

}
